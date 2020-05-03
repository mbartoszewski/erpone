package com.bartoszewski.erpone.Services;

import com.bartoszewski.erpone.Interfaces.DocumentDetailsRepository;
import com.bartoszewski.erpone.Interfaces.ThingRepository;
import com.bartoszewski.erpone.Models.Documents.DocumentDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<DocumentDetails> create(DocumentDetails entity)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(documentsDetailsRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<DocumentDetails>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Page<DocumentDetails>>(documentsDetailsRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DocumentDetails> readById(Long id)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(documentsDetailsRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DocumentDetails> updateById(Long id, DocumentDetails entity)
	{
		// TODO Auto-generated method stub
		DocumentDetails documentsDetail = documentsDetailsRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		documentsDetail.setQuantity(entity.getQuantity());
		return new ResponseEntity<>(documentsDetailsRepository.save(documentsDetail), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		// TODO Auto-generated method stub
		if (documentsDetailsRepository.getOne(id) != null)
		{
			documentsDetailsRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}