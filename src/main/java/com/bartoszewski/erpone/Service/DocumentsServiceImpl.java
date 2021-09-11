package com.bartoszewski.erpone.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.bartoszewski.erpone.Entity.Contractor;
import com.bartoszewski.erpone.Entity.Thing;
import com.bartoszewski.erpone.Entity.Documents.DocumentDetails;
import com.bartoszewski.erpone.Entity.Documents.Documents;
import com.bartoszewski.erpone.Entity.Documents.DocumentsProjection;
import com.bartoszewski.erpone.Entity.Documents.DocumentsWithDetailsProjection;
import com.bartoszewski.erpone.Enum.DocumentTypeEnum;
import com.bartoszewski.erpone.Enum.DocumentStatusEnum;
import com.bartoszewski.erpone.Repository.ContractorRepository;
import com.bartoszewski.erpone.Repository.DocumentsRepository;
import com.bartoszewski.erpone.Repository.PaymentFormRepository;
import com.bartoszewski.erpone.Repository.PaymentTermRepository;
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
	PaymentFormRepository paymentFormRepository;
	PaymentTermRepository paymentTermRepository;

	@Autowired
	public DocumentsServiceImpl(DocumentsRepository documentsRepository, UserRepository userRepository,
			ThingRepository thingsRepository, ContractorRepository contractorRepository,
			PaymentFormRepository paymentFormRepository, PaymentTermRepository paymentTermRepository) {
		this.documentsRepository = documentsRepository;
		this.userRepository = userRepository;
		this.thingsRepository = thingsRepository;
		this.contractorRepository = contractorRepository;
		this.paymentFormRepository = paymentFormRepository;
		this.paymentTermRepository = paymentTermRepository;
	}

	@Override
	public ResponseEntity<?> create(Documents entity, Authentication authentication) {
		switch (entity.getDocumentTypeEnum()) {
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
		DocumentStatusEnum statusEnum = status != null ? DocumentStatusEnum.valueOf(status) : null;
		DocumentTypeEnum typeEnum = type != null ? DocumentTypeEnum.valueOf(type) : null;
		return new ResponseEntity<>(
				documentsRepository.findAllByType(pageable, thing, typeEnum, statusEnum, startDate, endDate),
				HttpStatus.OK);
	}

	private ResponseEntity<?> makeOutgoingOperation(Documents entity, Authentication authentication) {
		List<DocumentDetails> detailsWithoutStock = new ArrayList<>(0);
		for (Iterator<DocumentDetails> documentsDetails = entity.getDocumentDetails().iterator(); documentsDetails
				.hasNext();) {
			DocumentDetails documentDetail = documentsDetails.next();
			Thing thing = thingsRepository.getOne(documentDetail.getThing().getId());
			documentDetail.setThing(thing);
			if (documentDetail.getThing().getQuantity() >= documentDetail.getQuantity()) {
				documentDetail.getThing()
						.setQuantity(documentDetail.getThing().getQuantity() - documentDetail.getQuantity());
				documentDetail.setBalance(documentDetail.getQuantity());
				// documentDetail.getPrice().setCurrency(entity.getDocumentCurrency());
				// documentDetail.getPrice().setThing(thing);
				// documentDetail.getPrice().setDocumentsDetails(documentDetail);
			} else {
				documentsDetails.remove();
				detailsWithoutStock.add(documentDetail);
			}
		}
		Long c = documentsRepository.countByYear(LocalDate.now().getYear(), entity.getDocumentTypeEnum()) + 1;
		entity.setDocNumber(c);
		entity.setPaymentForm(paymentFormRepository.getOne(entity.getPaymentForm().getId()));
		entity.setPaymentTerm(paymentTermRepository.getOne(entity.getPaymentTerm().getId()));
		// entity.setUser(userRepository.findByEmail(authentication.getName()));
		return detailsWithoutStock.size() > 0 ? new ResponseEntity<>(detailsWithoutStock, HttpStatus.BAD_REQUEST)
				: new ResponseEntity<>(documentsRepository.save(entity), HttpStatus.OK);
	}

	private ResponseEntity<?> makeIncomeOperation(Documents entity, Authentication authentication) {
		for (Iterator<DocumentDetails> documentsDetails = entity.getDocumentDetails().iterator(); documentsDetails
				.hasNext();) {
			DocumentDetails documentDetail = documentsDetails.next();
			Thing thing = thingsRepository.getOne(documentDetail.getThing().getId());
			thing.setQuantity(thing.getQuantity() + documentDetail.getQuantity());
			documentDetail.setBalance(documentDetail.getQuantity());
			// documentDetail.getPrice().setCurrency(entity.getDocumentCurrency());
			// documentDetail.getPrice().setThing(thing);
			// documentDetail.getPrice().setDocumentsDetails(documentDetail);
		}
		Long c = documentsRepository.countByYear(LocalDate.now().getYear(), entity.getDocumentTypeEnum()) + 1;
		entity.setDocNumber(c);
		entity.setPaymentForm(paymentFormRepository.getOne(entity.getPaymentForm().getId()));
		entity.setPaymentTerm(paymentTermRepository.getOne(entity.getPaymentTerm().getId()));
		// entity.setUser(userRepository.findByEmail(authentication.getName()));
		return new ResponseEntity<>(documentsRepository.save(entity), HttpStatus.CREATED);
	}

	private ResponseEntity<?> makeOrder(Documents entity, Authentication authentication) {
		List<DocumentDetails> disabledDetails = new ArrayList<>(0);
		Contractor contractor = contractorRepository.getOne(entity.getContractor().getId());
		if (entity.getTargetDateTime() != null && entity.getContractor() != null) {
			for (Iterator<DocumentDetails> documentsDetails = entity.getDocumentDetails().iterator(); documentsDetails
					.hasNext();) {
				DocumentDetails documentDetail = documentsDetails.next();
				Thing thing = thingsRepository.getOne(documentDetail.getThing().getId());
				if (documentDetail.getThing().getActive() != 1) {
					documentsDetails.remove();
					disabledDetails.add(documentDetail);
				}
				documentDetail.getPrice().setCurrency(entity.getDocumentCurrency());
				documentDetail.getPrice().setThing(thing);
				documentDetail.getPrice().setDocumentsDetails(documentDetail);
			}
			Long c = documentsRepository.countByYear(LocalDate.now().getYear(), entity.getDocumentTypeEnum()) + 1;
			entity.setDocNumber(c);
			entity.setContractor(contractor);
			// entity.setUser(userRepository.findByEmail(authentication.getName()));
			return disabledDetails.size() > 0 ? new ResponseEntity<>(disabledDetails, HttpStatus.BAD_REQUEST)
					: new ResponseEntity<>(documentsRepository.save(entity), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	private ResponseEntity<?> makeProductionOrder(Documents entity, Authentication authentication) {

		return new ResponseEntity<>("status", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<DocumentsProjection>> getDocuments(Pageable pageable, List<String> type, String status,
			LocalDate startDate, LocalDate endDate, String contractor) {
		DocumentStatusEnum statusEnum = status != null ? DocumentStatusEnum.valueOf(status) : null;

		List<DocumentTypeEnum> typeEnum = type != null
				? type.stream().map((t) -> DocumentTypeEnum.valueOf(t)).collect(Collectors.toList())
				: null;
		return new ResponseEntity<>(
				documentsRepository.getDocuments(pageable, typeEnum, statusEnum, startDate, endDate, contractor),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DocumentsWithDetailsProjection> getDocumentDetailsById(Long id) {
		return new ResponseEntity<>(documentsRepository.getDocumentDetailsById(id), HttpStatus.OK);
	}
}