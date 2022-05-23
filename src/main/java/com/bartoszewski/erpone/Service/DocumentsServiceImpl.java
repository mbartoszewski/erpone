package com.bartoszewski.erpone.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bartoszewski.erpone.Entity.Contractor;
import com.bartoszewski.erpone.Entity.Thing;
import com.bartoszewski.erpone.Entity.Documents.DocumentDetails;
import com.bartoszewski.erpone.Entity.Documents.DocumentValueProjection;
import com.bartoszewski.erpone.Entity.Documents.Documents;
import com.bartoszewski.erpone.Entity.Documents.DocumentsProjection;
import com.bartoszewski.erpone.Entity.Documents.DocumentsProjectionOrderShedule;
import com.bartoszewski.erpone.Entity.Documents.DocumentsWithDetailsProjection;
import com.bartoszewski.erpone.Enum.DocumentTypeEnum;
import com.bartoszewski.erpone.Enum.DocumentStatusEnum;
import com.bartoszewski.erpone.Repository.ContractorRepository;
import com.bartoszewski.erpone.Repository.DocumentsRepository;
import com.bartoszewski.erpone.Repository.PaymentFormRepository;
import com.bartoszewski.erpone.Repository.PaymentTermRepository;
import com.bartoszewski.erpone.Repository.ThingRepository;
import com.bartoszewski.erpone.Repository.UserRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;

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
		List<DocumentDetails> detailsWithoutStock = new ArrayList<>(0);
		double docValue = 0.0;
		for (Iterator<DocumentDetails> documentsDetails = entity.getDocumentDetails().iterator(); documentsDetails
				.hasNext();) {
			DocumentDetails documentDetail = documentsDetails.next();
			Thing thing = thingsRepository.getOne(documentDetail.getThing().getId());
			documentDetail.setThing(thing);
			docValue += (documentDetail.getQuantity() * documentDetail.getDetailPrice());
			if (documentDetail.getThing().getQuantity() >= documentDetail.getQuantity()) {
				thing.setQuantity(thing.getQuantity() - documentDetail.getQuantity());
				documentDetail.setBalance(documentDetail.getQuantity());
				// documentDetail.getPrice().setCurrency(entity.getDocumentCurrency());
				// documentDetail.getPrice().setThing(thing);
				// documentDetail.getPrice().setDocumentsDetails(documentDetail);
			} else {
				documentsDetails.remove();
				detailsWithoutStock.add(documentDetail);
			}
		}
		document.setDocValue(docValue);
		document.setContractor(entity.getContractor());
		document.setPaymentForm(entity.getPaymentForm());
		document.setPaymentTerm(entity.getPaymentTerm());
		document.setDocumentCurrency(entity.getDocumentCurrency());
		document.setDescription(entity.getDescription());
		document.setTargetDateTime(entity.getTargetDateTime());
		document.removeDocumentDetails();
		document.setDocumentDetails(entity.getDocumentDetails());
		return new ResponseEntity<>(documentsRepository.save(document), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		if (documentsRepository.getOne(id) != null) {
			documentsRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.GONE);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Page<Documents>> findAllByType(Pageable pageable, Long thing, List<String> type,
			List<String> status, LocalDate dateFrom, LocalDate dateTo) {
		List<DocumentStatusEnum> statusEnum = status != null
				? status.stream().map((s) -> DocumentStatusEnum.valueOf(s)).collect(Collectors.toList())
				: null;

		List<DocumentTypeEnum> typeEnum = type != null
				? type.stream().map((t) -> DocumentTypeEnum.valueOf(t)).collect(Collectors.toList())
				: null;
		return new ResponseEntity<>(
				documentsRepository.findAllByType(pageable, thing, typeEnum, statusEnum, dateFrom, dateTo),
				HttpStatus.OK);
	}

	private ResponseEntity<?> makeOutgoingOperation(Documents entity, Authentication authentication) {
		List<DocumentDetails> detailsWithoutStock = new ArrayList<>(0);
		double docValue = 0.0;
		for (Iterator<DocumentDetails> documentsDetails = entity.getDocumentDetails().iterator(); documentsDetails
				.hasNext();) {
			DocumentDetails documentDetail = documentsDetails.next();
			Thing thing = thingsRepository.getOne(documentDetail.getThing().getId());
			documentDetail.setThing(thing);
			docValue += (documentDetail.getQuantity() * documentDetail.getDetailPrice());
			if (documentDetail.getThing().getQuantity() >= documentDetail.getQuantity()) {
				thing.setQuantity(thing.getQuantity() - documentDetail.getQuantity());
				documentDetail.setBalance(documentDetail.getQuantity());
				// documentDetail.getPrice().setCurrency(entity.getDocumentCurrency());
				// documentDetail.getPrice().setThing(thing);
				// documentDetail.getPrice().setDocumentsDetails(documentDetail);
			} else {
				documentsDetails.remove();
				detailsWithoutStock.add(documentDetail);
			}
		}
		entity.setDocValue(docValue);
		Long c = documentsRepository.countByYear(LocalDate.now().getYear(), entity.getDocumentTypeEnum()) + 1;
		entity.settingDocNumber(c);
		entity.setPaymentForm(paymentFormRepository.getOne(entity.getPaymentForm().getId()));
		entity.setPaymentTerm(paymentTermRepository.getOne(entity.getPaymentTerm().getId()));
		// entity.setUser(userRepository.findByEmail(authentication.getName()));
		return detailsWithoutStock.size() > 0 ? new ResponseEntity<>(detailsWithoutStock, HttpStatus.BAD_REQUEST)
				: new ResponseEntity<>(documentsRepository.save(entity), HttpStatus.CREATED);
	}

	private ResponseEntity<?> makeIncomeOperation(Documents entity, Authentication authentication) {
		double docValue = 0.0;
		for (Iterator<DocumentDetails> documentsDetails = entity.getDocumentDetails().iterator(); documentsDetails
				.hasNext();) {
			DocumentDetails documentDetail = documentsDetails.next();
			Thing thing = thingsRepository.getOne(documentDetail.getThing().getId());
			thing.setQuantity(thing.getQuantity() + documentDetail.getQuantity());
			docValue += (documentDetail.getQuantity() * documentDetail.getDetailPrice());
			documentDetail.setBalance(documentDetail.getQuantity());
			// documentDetail.getPrice().setCurrency(entity.getDocumentCurrency());
			// documentDetail.getPrice().setThing(thing);
			// documentDetail.getPrice().setDocumentsDetails(documentDetail);
		}
		entity.setDocValue(docValue);
		Long c = documentsRepository.countByYear(LocalDate.now().getYear(), entity.getDocumentTypeEnum()) + 1;
		entity.settingDocNumber(c);
		entity.setPaymentForm(paymentFormRepository.getOne(entity.getPaymentForm().getId()));
		entity.setPaymentTerm(paymentTermRepository.getOne(entity.getPaymentTerm().getId()));
		// entity.setUser(userRepository.findByEmail(authentication.getName()));
		return new ResponseEntity<>(documentsRepository.save(entity), HttpStatus.CREATED);
	}

	private ResponseEntity<?> makeOrder(Documents entity, Authentication authentication) {
		List<DocumentDetails> disabledDetails = new ArrayList<>(0);
		Contractor contractor = contractorRepository.getOne(entity.getContractor().getId());
		if (entity.getTargetDateTime() != null && entity.getContractor() != null) {
			double docValue = 0.0;
			for (Iterator<DocumentDetails> documentsDetails = entity.getDocumentDetails().iterator(); documentsDetails
					.hasNext();) {
				DocumentDetails documentDetail = documentsDetails.next();
				Thing thing = thingsRepository.getOne(documentDetail.getThing().getId());
				docValue += (documentDetail.getQuantity() * documentDetail.getDetailPrice());
				if (thing.getActive() != 1) {
					documentsDetails.remove();
					disabledDetails.add(documentDetail);
				}

			}
			entity.setDocValue(docValue);
			Long c = documentsRepository.countByYear(LocalDate.now().getYear(), entity.getDocumentTypeEnum()) + 1;
			entity.settingDocNumber(c);
			entity.setPaymentForm(paymentFormRepository.getOne(entity.getPaymentForm().getId()));
			entity.setPaymentTerm(paymentTermRepository.getOne(entity.getPaymentTerm().getId()));
			entity.setContractor(contractor);
			// entity.setUser(userRepository.findByEmail(authentication.getName()));
			return disabledDetails.size() > 0 ? new ResponseEntity<>(disabledDetails, HttpStatus.BAD_REQUEST)
					: new ResponseEntity<>(documentsRepository.save(entity), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	private ResponseEntity<?> makeProductionOrder(Documents entity, Authentication authentication) {

		return new ResponseEntity<>("status", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Page<DocumentsProjection>> getDocuments(Pageable pageable, List<String> type,
			List<String> status, LocalDate dateFrom, LocalDate dateTo, String contractor) {

		List<DocumentStatusEnum> statusEnum = status != null
				? status.stream().map((s) -> DocumentStatusEnum.valueOf(s)).collect(Collectors.toList())
				: null;

		List<DocumentTypeEnum> typeEnum = type != null
				? type.stream().map((t) -> DocumentTypeEnum.valueOf(t)).collect(Collectors.toList())
				: null;
		return new ResponseEntity<>(
				documentsRepository.getDocuments(pageable, typeEnum, statusEnum, dateFrom, dateTo, contractor),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DocumentsWithDetailsProjection> getDocumentDetailsById(Long id) {
		return new ResponseEntity<>(documentsRepository.getDocumentDetailsById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<DocumentValueProjection>> getDocumentValue(Pageable pageable, List<String> type,
			List<String> status, List<Long> contractor, LocalDate targetDateFrom, LocalDate targetDateTo,
			String dateFrom, String dateTo) {

		ZonedDateTime dateFromztd = ZonedDateTime.parse(dateFrom);
		LocalDateTime dateFromLDT = dateFromztd.toLocalDateTime();

		ZonedDateTime dateToztd = ZonedDateTime.parse(dateTo);
		LocalDateTime dateToLDT = dateToztd.toLocalDateTime();
		List<DocumentStatusEnum> statusEnum = status != null
				? status.stream().map((s) -> DocumentStatusEnum.valueOf(s)).collect(Collectors.toList())
				: null;

		List<DocumentTypeEnum> typeEnum = type != null
				? type.stream().map((t) -> DocumentTypeEnum.valueOf(t)).collect(Collectors.toList())
				: null;
		return new ResponseEntity<>(documentsRepository.getDocumentValue(pageable, typeEnum, statusEnum, contractor,
				targetDateFrom, targetDateTo, dateFromLDT, dateToLDT), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, String>> getDocumentNumber(String documentType) {

		DocumentTypeEnum typeEnum = documentType != null
				? DocumentTypeEnum.valueOf(documentType.toLowerCase())
				: null;

		Long countByYear = documentsRepository.countByYear(LocalDate.now().getYear(), typeEnum) + 1;
		String documentNumber = documentType.toUpperCase() + "/" + countByYear.toString() + "/"
				+ LocalDate.now().getYear();

		Map<String, String> json = new HashMap<>();
		json.put("type", documentNumber);

		return new ResponseEntity<>(json, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<DocumentsProjectionOrderShedule>> getDocumentsShedule(Pageable pageable,
			List<String> type, List<String> status, LocalDate targetDateFrom, LocalDate targetDateTo,
			String contractor) {
		List<DocumentStatusEnum> statusEnum = status != null
				? status.stream().map((s) -> DocumentStatusEnum.valueOf(s)).collect(Collectors.toList())
				: null;

		List<DocumentTypeEnum> typeEnum = type != null
				? type.stream().map((t) -> DocumentTypeEnum.valueOf(t)).collect(Collectors.toList())
				: null;
		return new ResponseEntity<>(
				documentsRepository.getDocumentsShedule(pageable, typeEnum, statusEnum, targetDateFrom, targetDateTo,
						contractor),
				HttpStatus.OK);
	}
}