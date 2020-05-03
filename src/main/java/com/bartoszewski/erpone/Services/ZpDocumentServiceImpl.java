package com.bartoszewski.erpone.Services;

import com.bartoszewski.erpone.Interfaces.ThingRepository;
import com.bartoszewski.erpone.Interfaces.ZpDocumentRepository;
import com.bartoszewski.erpone.Models.Documents.ProductionDocuments.ZpDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ZpDocumentServiceImpl implements ZpDocumentService
{
	ZpDocumentRepository zpDocumentRepository;
	ThingRepository thingsRepository;

	@Autowired
	public ZpDocumentServiceImpl(ZpDocumentRepository zpDocumentRepository, ThingRepository thingsRepository)
	{
		this.zpDocumentRepository = zpDocumentRepository;
		this.thingsRepository = thingsRepository;
	}

	@Override
	public ResponseEntity<?> create(ZpDocument entity)
	{
		return new ResponseEntity<>(zpDocumentRepository.save(entity), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Page<ZpDocument>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Page<ZpDocument>>(zpDocumentRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ZpDocument> readById(Long id)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(
		    zpDocumentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ZpDocument> updateById(Long id, ZpDocument entity)
	{
		// TODO Auto-generated method stub
		ZpDocument zpDocument = zpDocumentRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		zpDocument.setDescription(entity.getDescription());
		zpDocument.setDocumentsDetails(entity.getDocumentsDetails());
		zpDocument.setStatusTypeEnum(entity.getStatusTypeEnum());
		zpDocument.setTargetDateTime(entity.getTargetDateTime());
		return new ResponseEntity<>(zpDocumentRepository.save(zpDocument), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		// TODO Auto-generated method stub
		if (zpDocumentRepository.getOne(id) != null)
		{
			zpDocumentRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}