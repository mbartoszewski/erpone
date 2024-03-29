package com.bartoszewski.erpone.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.bartoszewski.erpone.Entity.Documents.DocumentDetails;
import com.bartoszewski.erpone.Entity.Documents.DocumentsDetailsPriceProjection;
import com.bartoszewski.erpone.Entity.Documents.DocumentsDetailsProjection;
import com.bartoszewski.erpone.Entity.Documents.DocumentsDetailsWithBalanceProjection;
import com.bartoszewski.erpone.Enum.DocumentStatusEnum;
import com.bartoszewski.erpone.Enum.DocumentTypeEnum;
import com.bartoszewski.erpone.Repository.DocumentDetailsRepository;
import com.bartoszewski.erpone.Repository.ThingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DocumentDetailsServiceImpl implements DocumentDetailsService {
	DocumentDetailsRepository documentDetailsRepository;
	ThingRepository thingsRepository;

	@Autowired
	public DocumentDetailsServiceImpl(DocumentDetailsRepository documentDetailsRepository,
			ThingRepository thingsRepository) {
		this.documentDetailsRepository = documentDetailsRepository;
		this.thingsRepository = thingsRepository;
	}

	@Override
	public ResponseEntity<DocumentDetails> create(DocumentDetails entity, Authentication authentication) {
		entity.setBalance(entity.getQuantity());
		return new ResponseEntity<>(documentDetailsRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<DocumentDetails>> readAll(Pageable pageable) {
		return new ResponseEntity<Page<DocumentDetails>>(documentDetailsRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DocumentDetails> readById(Long id) {
		return new ResponseEntity<>(documentDetailsRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DocumentDetails> updateById(Long id, DocumentDetails entity) {
		DocumentDetails documentDetail = documentDetailsRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		documentDetail.setQuantity(entity.getQuantity());
		return new ResponseEntity<>(documentDetailsRepository.save(documentDetail), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		if (documentDetailsRepository.getOne(id) != null) {
			documentDetailsRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Page<DocumentsDetailsPriceProjection>> findAllOperationsByThingAndType(Pageable pageable,
			Long thing, String type, LocalDate startDate, LocalDate endDate) {
		DocumentTypeEnum typeEnum = type != null ? DocumentTypeEnum.valueOf(type) : null;
		return new ResponseEntity<>(documentDetailsRepository.findAllOperationsByThingAndType(pageable, thing, typeEnum,
				startDate, endDate), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<DocumentsDetailsProjection>> findLastByProjection(Pageable pageable, Long thing,
			List<String> type) {

		List<DocumentTypeEnum> typeEnum = type != null
				? type.stream().map((t) -> DocumentTypeEnum.valueOf(t)).collect(Collectors.toList())
				: null;

		return new ResponseEntity<>(
				documentDetailsRepository.findLastByDocumentsDetailsProjections(pageable, thing, typeEnum),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<DocumentsDetailsWithBalanceProjection>> findDocumentsDetailsWithBalanceProjection(
			Pageable pageable, Long thing, List<String> documentTypeEnum, List<String> documentStatusEnum) {
		List<DocumentTypeEnum> typeEnum = documentTypeEnum != null
				? documentTypeEnum.stream().map((t) -> DocumentTypeEnum.valueOf(t)).collect(Collectors.toList())
				: null;
		List<DocumentStatusEnum> statusEnum = documentStatusEnum != null
				? documentStatusEnum.stream().map((t) -> DocumentStatusEnum.valueOf(t)).collect(Collectors.toList())
				: null;
		return new ResponseEntity<>(documentDetailsRepository.findDocumentsDetailsWithBalanceProjection(pageable, thing,
				typeEnum, statusEnum), HttpStatus.OK);
	}
}