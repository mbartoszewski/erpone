package com.bartoszewski.erpone.Services;

import java.util.Iterator;

import com.bartoszewski.erpone.Interfaces.DocumentDetailsRepository;
import com.bartoszewski.erpone.Interfaces.PriceRepository;
import com.bartoszewski.erpone.Interfaces.PwzDocumentRepository;
import com.bartoszewski.erpone.Interfaces.ThingRepository;
import com.bartoszewski.erpone.Interfaces.WarehouseRepository;
import com.bartoszewski.erpone.Models.Documents.DocumentDetails;
import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.PwzDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PwzDocumentServiceImpl implements PwzDocumentService
{
	PwzDocumentRepository pwzDocumentRepository;
	DocumentDetailsRepository documentsDetailsRepository;
	DocumentDetailsService documentsDetailsService;
	ThingRepository thingsRepository;
	PriceRepository pricesRepository;
	WarehouseRepository warehouseRepository;

	@Autowired
	public PwzDocumentServiceImpl(PwzDocumentRepository pwzDocumentRepository,
	    DocumentDetailsRepository documentsDetailsRepository, DocumentDetailsService documentsDetailsService,
	    ThingRepository thingsRepository, PriceRepository pricesRepository, WarehouseRepository warehouseRepository)
	{
		this.pwzDocumentRepository = pwzDocumentRepository;
		this.documentsDetailsRepository = documentsDetailsRepository;
		this.documentsDetailsService = documentsDetailsService;
		this.thingsRepository = thingsRepository;
		this.pricesRepository = pricesRepository;
		this.warehouseRepository = warehouseRepository;
	}

	@Override
	public ResponseEntity<?> create(PwzDocument entity)
	{
		// TODO Auto-generated method stub
		for (Iterator<DocumentDetails> documentsDetails = entity.getDocumentsDetails().iterator(); documentsDetails
		    .hasNext();)
		{
			documentsDetails.next().getThing()
			    .setQuantity(documentsDetails.next().getThing().getQuantity() + documentsDetails.next().getQuantity());
			documentsDetails.next().getThing().addDocumentsDetails(documentsDetails.next());

		}
		return new ResponseEntity<>(pwzDocumentRepository.save(entity), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Page<PwzDocument>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Page<PwzDocument>>(pwzDocumentRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PwzDocument> readById(Long id)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(
		    pwzDocumentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PwzDocument> updateById(Long id, PwzDocument entity)
	{
		// TODO Auto-generated method stub
		PwzDocument pwzDocument = pwzDocumentRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		return new ResponseEntity<>(pwzDocumentRepository.save(pwzDocument), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		// TODO Auto-generated method stub
		if (pwzDocumentRepository.getOne(id) != null)
		{
			pwzDocumentRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}