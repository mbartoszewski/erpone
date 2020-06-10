package com.bartoszewski.erpone.Controller;

import java.time.LocalDate;

import com.bartoszewski.erpone.Entity.Documents.DocumentDetails;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.DocumentDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents/details")
public class DocumentDetailsController extends BaseController<DocumentDetails, Long> {
	DocumentDetailsService documentDetailsService;

	@Autowired
	public DocumentDetailsController(BaseService<DocumentDetails, Long> service, DocumentDetailsService documentDetailsService) {
		super(service);
		this.documentDetailsService = documentDetailsService;
	}

	@GetMapping("/purchase/{thingId}")
	public ResponseEntity<Page<DocumentDetails>> findAllIncomeByThing(Pageable pageable, 
			@RequestParam("startDate") 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, 
			@RequestParam("endDate") 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@PathVariable("thingId") Long thingId) {
		return documentDetailsService.findAllIncomeByThing(pageable, thingId, startDate, endDate);
	}
	@GetMapping("/sales/{thingId}")
	public ResponseEntity<Page<DocumentDetails>> findAllOutgoingsByThing(Pageable pageable, 
			@RequestParam("startDate") 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, 
			@RequestParam("endDate") 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@PathVariable("thingId") Long thingId) {
		return documentDetailsService.findAllOutgoingsByThing(pageable, thingId, startDate, endDate);	
	}
}