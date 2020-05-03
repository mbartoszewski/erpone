package com.bartoszewski.erpone.Services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bartoszewski.erpone.Interfaces.DocumentDetailsRepository;
import com.bartoszewski.erpone.Interfaces.PriceRepository;
import com.bartoszewski.erpone.Interfaces.ThingRepository;
import com.bartoszewski.erpone.Interfaces.WarehouseRepository;
import com.bartoszewski.erpone.Interfaces.WzDocumentRepository;
import com.bartoszewski.erpone.Models.Documents.DocumentDetails;
import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.WzDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WzDocumentServiceImpl implements WzDocumentService
{
	WzDocumentRepository wzDocumentRepository;
	DocumentDetailsRepository documentsDetailsRepository;
	DocumentDetailsService documentsDetailsService;
	ThingRepository thingsRepository;
	PriceRepository pricesRepository;
	WarehouseRepository warehouseRepository;

	@Autowired
	public WzDocumentServiceImpl(WzDocumentRepository wzDocumentRepository,
	    DocumentDetailsRepository documentsDetailsRepository, DocumentDetailsService documentsDetailsService,
	    ThingRepository thingsRepository, PriceRepository pricesRepository, WarehouseRepository warehouseRepository)
	{
		this.wzDocumentRepository = wzDocumentRepository;
		this.documentsDetailsRepository = documentsDetailsRepository;
		this.documentsDetailsService = documentsDetailsService;
		this.thingsRepository = thingsRepository;
		this.pricesRepository = pricesRepository;
		this.warehouseRepository = warehouseRepository;
	}

	@Override
	public ResponseEntity<?> create(WzDocument entity)
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
		                                      : new ResponseEntity<>(wzDocumentRepository.save(entity),
		                                          HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Page<WzDocument>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Page<WzDocument>>(wzDocumentRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<WzDocument> readById(Long id)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(
		    wzDocumentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<WzDocument> updateById(Long id, WzDocument entity)
	{
		// TODO Auto-generated method stub
		WzDocument wzDocument = wzDocumentRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		return new ResponseEntity<>(wzDocumentRepository.save(wzDocument), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		// TODO Auto-generated method stub
		if (wzDocumentRepository.getOne(id) != null)
		{
			wzDocumentRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}