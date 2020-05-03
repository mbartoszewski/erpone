package com.bartoszewski.erpone.Services;

import com.bartoszewski.erpone.Interfaces.PoDocumentRepository;
import com.bartoszewski.erpone.Interfaces.ThingRepository;
import com.bartoszewski.erpone.Models.Documents.PurchaseDocuments.PoDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PoDocumentServiceImpl implements PoDocumentService
{
	PoDocumentRepository poDocumentRepository;
	ThingRepository thingsRepository;

	@Autowired
	public PoDocumentServiceImpl(PoDocumentRepository poDocumentRepository, ThingRepository thingsRepository)
	{
		this.poDocumentRepository = poDocumentRepository;
		this.thingsRepository = thingsRepository;
	}

	@Override
	public ResponseEntity<?> create(PoDocument entity)
	{
		return new ResponseEntity<>(poDocumentRepository.save(entity), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Page<PoDocument>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Page<PoDocument>>(poDocumentRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PoDocument> readById(Long id)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(
		    poDocumentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PoDocument> updateById(Long id, PoDocument entity)
	{
		// TODO Auto-generated method stub
		PoDocument poDocument = poDocumentRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		poDocument.setContractor(entity.getContractor());
		poDocument.setDescription(entity.getDescription());
		poDocument.setDocumentsDetails(entity.getDocumentsDetails());
		poDocument.setStatusTypeEnum(entity.getStatusTypeEnum());
		poDocument.setTargetDateTime(entity.getTargetDateTime());
		return new ResponseEntity<>(poDocumentRepository.save(poDocument), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		// TODO Auto-generated method stub
		if (poDocumentRepository.getOne(id) != null)
		{
			poDocumentRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}