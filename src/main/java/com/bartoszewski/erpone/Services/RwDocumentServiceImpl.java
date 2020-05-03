package com.bartoszewski.erpone.Services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bartoszewski.erpone.Interfaces.DocumentDetailsRepository;
import com.bartoszewski.erpone.Interfaces.PriceRepository;
import com.bartoszewski.erpone.Interfaces.ThingRepository;
import com.bartoszewski.erpone.Interfaces.WarehouseRepository;
import com.bartoszewski.erpone.Interfaces.RwDocumentRepository;
import com.bartoszewski.erpone.Models.Documents.DocumentDetails;
import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.RwDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RwDocumentServiceImpl implements RwDocumentService
{
	RwDocumentRepository rwDocumentRepository;
	DocumentDetailsRepository documentsDetailsRepository;
	DocumentDetailsService documentsDetailsService;
	ThingRepository thingsRepository;
	PriceRepository pricesRepository;
	WarehouseRepository warehouseRepository;

	@Autowired
	public RwDocumentServiceImpl(RwDocumentRepository rwDocumentRepository,
	    DocumentDetailsRepository documentsDetailsRepository, DocumentDetailsService documentsDetailsService,
	    ThingRepository thingsRepository, PriceRepository pricesRepository, WarehouseRepository warehouseRepository)
	{
		this.rwDocumentRepository = rwDocumentRepository;
		this.documentsDetailsRepository = documentsDetailsRepository;
		this.documentsDetailsService = documentsDetailsService;
		this.thingsRepository = thingsRepository;
		this.pricesRepository = pricesRepository;
		this.warehouseRepository = warehouseRepository;
	}

	@Override
	public ResponseEntity<?> create(RwDocument entity)
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
		                                      : new ResponseEntity<>(rwDocumentRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<RwDocument>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Page<RwDocument>>(rwDocumentRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RwDocument> readById(Long id)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(
		    rwDocumentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RwDocument> updateById(Long id, RwDocument entity)
	{
		// TODO Auto-generated method stub
		RwDocument rwDocument = rwDocumentRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		return new ResponseEntity<>(rwDocumentRepository.save(rwDocument), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		// TODO Auto-generated method stub
		if (rwDocumentRepository.getOne(id) != null)
		{
			rwDocumentRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}