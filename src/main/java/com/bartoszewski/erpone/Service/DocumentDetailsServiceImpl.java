package com.bartoszewski.erpone.Service;

import java.time.LocalDate;

import com.bartoszewski.erpone.Entity.Documents.DocumentDetails;
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
public class DocumentDetailsServiceImpl implements DocumentDetailsService
{
	DocumentDetailsRepository documentsDetailsRepository;
	ThingRepository thingsRepository;

	@Autowired
	public DocumentDetailsServiceImpl(DocumentDetailsRepository documentsDetailsRepository,
	    ThingRepository thingsRepository)
	{
		this.documentsDetailsRepository = documentsDetailsRepository;
		this.thingsRepository = thingsRepository;
	}

	@Override
	public ResponseEntity<DocumentDetails> create(DocumentDetails entity, Authentication authentication)
	{
		return new ResponseEntity<>(documentsDetailsRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<DocumentDetails>> readAll(Pageable pageable)
	{
		return new ResponseEntity<Page<DocumentDetails>>(documentsDetailsRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DocumentDetails> readById(Long id)
	{
		return new ResponseEntity<>(documentsDetailsRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DocumentDetails> updateById(Long id, DocumentDetails entity)
	{
		DocumentDetails documentsDetail = documentsDetailsRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		documentsDetail.setQuantity(entity.getQuantity());
		return new ResponseEntity<>(documentsDetailsRepository.save(documentsDetail), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		if (documentsDetailsRepository.getOne(id) != null)
		{
			documentsDetailsRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Page<DocumentDetails>> findAllIncomeByThing(Pageable pageable, Long thingId, LocalDate startDate, LocalDate endDate) {
		return new ResponseEntity<>(documentsDetailsRepository.findAllIncomeByThing(pageable, thingId,  startDate, endDate), HttpStatus.OK);
	}
	@Override
	public ResponseEntity<Page<DocumentDetails>> findAllOutgoingsByThing(Pageable pageable, Long thingId, LocalDate startDate, LocalDate endDate) {
		return new ResponseEntity<>(documentsDetailsRepository.findAllOutgoingsByThing(pageable, thingId,  startDate, endDate), HttpStatus.OK);
	}
}