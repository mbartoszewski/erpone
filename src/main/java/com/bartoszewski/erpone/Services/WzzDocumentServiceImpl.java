package com.bartoszewski.erpone.Services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bartoszewski.erpone.Interfaces.DocumentDetailsRepository;
import com.bartoszewski.erpone.Interfaces.PriceRepository;
import com.bartoszewski.erpone.Interfaces.ThingRepository;
import com.bartoszewski.erpone.Interfaces.WarehouseRepository;
import com.bartoszewski.erpone.Interfaces.WzzDocumentRepository;
import com.bartoszewski.erpone.Models.Documents.DocumentDetails;
import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.WzzDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WzzDocumentServiceImpl implements WzzDocumentService
{
	WzzDocumentRepository wzzDocumentRepository;
	DocumentDetailsRepository documentsDetailsRepository;
	DocumentDetailsService documentsDetailsService;
	ThingRepository thingsRepository;
	PriceRepository pricesRepository;
	WarehouseRepository warehouseRepository;

	@Autowired
	public WzzDocumentServiceImpl(WzzDocumentRepository wzzDocumentRepository,
	    DocumentDetailsRepository documentsDetailsRepository, DocumentDetailsService documentsDetailsService,
	    ThingRepository thingsRepository, PriceRepository pricesRepository, WarehouseRepository warehouseRepository)
	{
		this.wzzDocumentRepository = wzzDocumentRepository;
		this.documentsDetailsRepository = documentsDetailsRepository;
		this.documentsDetailsService = documentsDetailsService;
		this.thingsRepository = thingsRepository;
		this.pricesRepository = pricesRepository;
		this.warehouseRepository = warehouseRepository;
	}

	@Override
	public ResponseEntity<?> create(WzzDocument entity)
	{
		List<DocumentDetails> detailsWithoutStock = new ArrayList<>();
		// TODO Auto-generated method stub
		for (Iterator<DocumentDetails> documentsDetails = entity.getDocumentsDetails().iterator(); documentsDetails
		    .hasNext();)
		{
			DocumentDetails documentDetail = documentsDetails.next();
			documentDetail.setThing(thingsRepository.getOne(documentDetail.getThing().getId()));
			if (documentDetail.getThing().getQuantity() >= documentDetail.getQuantity())
			{
				documentDetail.getThing()
				    .setQuantity(documentDetail.getThing().getQuantity() - documentDetail.getQuantity());
				documentDetail.getThing().addDocumentsDetails(documentDetail);
			}
			else
			{
				documentsDetails.remove();
				detailsWithoutStock.add(documentDetail);
			}
		}
		return detailsWithoutStock.size() > 0
		                                      ? new ResponseEntity<>(detailsWithoutStock, HttpStatus.BAD_REQUEST)
		                                      : new ResponseEntity<>(wzzDocumentRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<WzzDocument>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Page<WzzDocument>>(wzzDocumentRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<WzzDocument> readById(Long id)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(
		    wzzDocumentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<WzzDocument> updateById(Long id, WzzDocument entity)
	{
		// TODO Auto-generated method stub
		WzzDocument wzzDocument = wzzDocumentRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		return new ResponseEntity<>(wzzDocumentRepository.save(wzzDocument), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		// TODO Auto-generated method stub
		if (wzzDocumentRepository.getOne(id) != null)
		{
			wzzDocumentRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}