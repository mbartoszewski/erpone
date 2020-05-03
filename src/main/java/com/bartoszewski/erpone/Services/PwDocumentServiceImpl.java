package com.bartoszewski.erpone.Services;

import java.util.Iterator;

import com.bartoszewski.erpone.Interfaces.DocumentDetailsRepository;
import com.bartoszewski.erpone.Interfaces.PriceRepository;
import com.bartoszewski.erpone.Interfaces.PwDocumentRepository;
import com.bartoszewski.erpone.Interfaces.ThingRepository;
import com.bartoszewski.erpone.Interfaces.WarehouseRepository;
import com.bartoszewski.erpone.Models.Documents.DocumentDetails;
import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.PwDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PwDocumentServiceImpl implements PwDocumentService
{
	PwDocumentRepository pwDocumentRepository;
	DocumentDetailsRepository documentsDetailsRepository;
	DocumentDetailsService documentsDetailsService;
	ThingRepository thingsRepository;
	PriceRepository pricesRepository;
	WarehouseRepository warehouseRepository;

	@Autowired
	public PwDocumentServiceImpl(PwDocumentRepository pwDocumentRepository,
	    DocumentDetailsRepository documentsDetailsRepository, DocumentDetailsService documentsDetailsService,
	    ThingRepository thingsRepository, PriceRepository pricesRepository, WarehouseRepository warehouseRepository)
	{
		this.pwDocumentRepository = pwDocumentRepository;
		this.documentsDetailsRepository = documentsDetailsRepository;
		this.documentsDetailsService = documentsDetailsService;
		this.thingsRepository = thingsRepository;
		this.pricesRepository = pricesRepository;
		this.warehouseRepository = warehouseRepository;
	}

	@Override
	public ResponseEntity<?> create(PwDocument entity)
	{
		for (Iterator<DocumentDetails> documentsDetails = entity.getDocumentsDetails().iterator(); documentsDetails
		    .hasNext();)
		{
			documentsDetails.next().getThing()
			    .setQuantity(documentsDetails.next().getThing().getQuantity() + documentsDetails.next().getQuantity());
			documentsDetails.next().getThing().addDocumentsDetails(documentsDetails.next());

		}
		return new ResponseEntity<>(pwDocumentRepository.save(entity), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Page<PwDocument>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Page<PwDocument>>(pwDocumentRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PwDocument> readById(Long id)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(
		    pwDocumentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PwDocument> updateById(Long id, PwDocument entity)
	{
		// TODO Auto-generated method stub
		PwDocument pwDocument = pwDocumentRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		return new ResponseEntity<>(pwDocumentRepository.save(pwDocument), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		// TODO Auto-generated method stub
		if (pwDocumentRepository.getOne(id) != null)
		{
			pwDocumentRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}