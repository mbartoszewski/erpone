package com.bartoszewski.erpone.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bartoszewski.erpone.Entity.Contractor;
import com.bartoszewski.erpone.Entity.Thing;
import com.bartoszewski.erpone.Entity.Documents.DocumentDetails;
import com.bartoszewski.erpone.Entity.Documents.Documents;
import com.bartoszewski.erpone.Enum.DocumentTypeEnum;
import com.bartoszewski.erpone.Enum.StatusTypeEnum;
import com.bartoszewski.erpone.Repository.ContractorRepository;
import com.bartoszewski.erpone.Repository.DocumentsRepository;
import com.bartoszewski.erpone.Repository.ThingRepository;
import com.bartoszewski.erpone.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DocumentsServiceImpl implements DocumentsService {
	DocumentsRepository documentsRepository;
	ThingRepository thingsRepository;
	UserRepository userRepository;
	ContractorRepository contractorRepository;

	@Autowired
	public DocumentsServiceImpl(DocumentsRepository documentsRepository, UserRepository userRepository,
			ThingRepository thingsRepository, ContractorRepository contractorRepository) {
		this.documentsRepository = documentsRepository;
		this.userRepository = userRepository;
		this.thingsRepository = thingsRepository;
		this.contractorRepository = contractorRepository;
	}

	@Override
	public ResponseEntity<?> create(Documents entity, Authentication authentication) {
		switch (entity.getDocumentTypeEnum()) {
			case zmw:
			case zm:
				return makeOrder(entity, authentication);
			case pw:
			case pz:
				return makeIncomeOperation(entity, authentication);
			case rw:
			case wz:
			case wzz:
				return makeOutgoingOperation(entity, authentication);
			case zp:
				return makeProductionOrder(entity, authentication);
			default:
				return null;
		}
	}

	@Override
	public ResponseEntity<Page<Documents>> readAll(Pageable pageable) {
		return new ResponseEntity<Page<Documents>>(documentsRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Documents> readById(Long id) {
		return new ResponseEntity<>(
				documentsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Documents> updateById(Long id, Documents entity) {
		Documents document = documentsRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return new ResponseEntity<>(documentsRepository.save(document), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		if (documentsRepository.getOne(id) != null) {
			documentsRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Page<Documents>> findAllByType(Pageable pageable, Long thing, String type, String status,
			LocalDate startDate, LocalDate endDate) {
		StatusTypeEnum statusEnum = status != null ? StatusTypeEnum.valueOf(status) : null;
		DocumentTypeEnum typeEnum = type != null ? DocumentTypeEnum.valueOf(type) : null;
		return new ResponseEntity<>(
				documentsRepository.findAllByType(pageable, thing, typeEnum, statusEnum, startDate, endDate),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Documents>> findPurchaseOrderByDetails(Pageable pageable, Long thing, String type,
			String status, LocalDate startTargetDate, LocalDate endTargetDate, String contractor) {
		StatusTypeEnum statusEnum = status != null ? StatusTypeEnum.valueOf(status) : null;
		DocumentTypeEnum typeEnum = type != null ? DocumentTypeEnum.valueOf(type) : null;
		return new ResponseEntity<>(documentsRepository.findPurchaseOrderByDetails(pageable, thing, typeEnum,
				statusEnum, startTargetDate, endTargetDate, contractor), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Documents>> findProductionOrderByDetails(Pageable pageable, String status,
			LocalDate startTargetDate, LocalDate endTargetDate, Long recipe) {
		StatusTypeEnum statusEnum = status != null ? StatusTypeEnum.valueOf(status) : null;
		return new ResponseEntity<>(documentsRepository.findProductionOrderByDetails(pageable, statusEnum,
				startTargetDate, endTargetDate, recipe), HttpStatus.OK);
	}

	private ResponseEntity<?> makeOutgoingOperation(Documents entity, Authentication authentication) {
		List<DocumentDetails> detailsWithoutStock = new ArrayList<>(0);
		for (Iterator<DocumentDetails> documentsDetails = entity.getDocumentDetails().iterator(); documentsDetails
				.hasNext();) {
			DocumentDetails documentDetail = documentsDetails.next();
			documentDetail.setThing(thingsRepository.getOne(documentDetail.getThing().getId()));
			if (documentDetail.getThing().getQuantity() >= documentDetail.getQuantity()) {
				documentDetail.getThing()
						.setQuantity(documentDetail.getThing().getQuantity() - documentDetail.getQuantity());
			} else {
				documentsDetails.remove();
				detailsWithoutStock.add(documentDetail);
			}
		}
		entity.setUser(userRepository.findByEmail(authentication.getName()));
		return detailsWithoutStock.size() > 0 ? new ResponseEntity<>(detailsWithoutStock, HttpStatus.BAD_REQUEST)
				: new ResponseEntity<>(documentsRepository.save(entity), HttpStatus.OK);
	}

	private ResponseEntity<?> makeIncomeOperation(Documents entity, Authentication authentication) {
		for (Iterator<DocumentDetails> documentsDetails = entity.getDocumentDetails().iterator(); documentsDetails
				.hasNext();) {
			DocumentDetails documentDetail = documentsDetails.next();
			Thing thing = thingsRepository.getOne(documentDetail.getThing().getId());
			thing.setQuantity(thing.getQuantity() + documentDetail.getQuantity());
		}
		entity.setUser(userRepository.findByEmail(authentication.getName()));
		return new ResponseEntity<>(documentsRepository.save(entity), HttpStatus.CREATED);
	}

	private ResponseEntity<?> makeOrder(Documents entity, Authentication authentication) {
		List<DocumentDetails> disabledDetails = new ArrayList<>(0);
		Contractor contractor = contractorRepository.getOne(entity.getOrderDocumentDetails().getContractor().getId());
		if (entity.getOrderDocumentDetails().getTargetDateTime() != null
				&& entity.getOrderDocumentDetails().getContractor() != null) {
			for (Iterator<DocumentDetails> documentsDetails = entity.getDocumentDetails().iterator(); documentsDetails
					.hasNext();) {
				DocumentDetails documentDetail = documentsDetails.next();
				if (documentDetail.getThing().getActive() != 1) {
					documentsDetails.remove();
					disabledDetails.add(documentDetail);
				}
				documentDetail.setThing(thingsRepository.getOne(documentDetail.getThing().getId()));
			}
			entity.getOrderDocumentDetails().setContractor(contractor);
			entity.setUser(userRepository.findByEmail(authentication.getName()));
			return disabledDetails.size() > 0 ? new ResponseEntity<>(disabledDetails, HttpStatus.BAD_REQUEST)
					: new ResponseEntity<>(documentsRepository.save(entity), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	private ResponseEntity<?> makeProductionOrder(Documents entity, Authentication authentication) {
		List<DocumentDetails> detailsWithoutStock = new ArrayList<>(0);

		return new ResponseEntity<>("status", HttpStatus.OK);
	}
}