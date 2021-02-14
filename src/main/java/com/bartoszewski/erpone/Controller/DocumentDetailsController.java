package com.bartoszewski.erpone.Controller;

import java.time.LocalDate;
import java.util.List;

import com.bartoszewski.erpone.Entity.Documents.DocumentDetails;
import com.bartoszewski.erpone.Entity.Documents.DocumentsDetailsProjection;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.DocumentDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents/details")
public class DocumentDetailsController extends BaseController<DocumentDetails, Long> {
	DocumentDetailsService documentDetailsService;

	@Autowired
	public DocumentDetailsController(BaseService<DocumentDetails, Long> service,
			DocumentDetailsService documentDetailsService) {
		super(service);
		this.documentDetailsService = documentDetailsService;
	}

	@GetMapping("/purchase")
	public ResponseEntity<Page<DocumentDetails>> findAllIncomeByThing(Pageable pageable,
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@RequestParam("thing") Long thing) {
		return documentDetailsService.findAllIncomeByThing(pageable, thing, startDate, endDate);
	}

	@GetMapping("/sales")
	public ResponseEntity<Page<DocumentDetails>> findAllOutgoingsByThing(Pageable pageable,
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@RequestParam("thing") Long thing) {
		return documentDetailsService.findAllOutgoingsByThing(pageable, thing, startDate, endDate);
	}

	@GetMapping("")
	public ResponseEntity<Page<DocumentDetails>> findAllOperationsByThingAndType(Pageable pageable,
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@RequestParam("thing") Long thing, @RequestParam(value = "type", required = false) String type) {
		return documentDetailsService.findAllOperationsByThingAndType(pageable, thing, type, startDate, endDate);
	}

	@GetMapping("/last")
	public ResponseEntity<Page<DocumentsDetailsProjection>> test(Pageable pageable, @RequestParam("thing") Long thing,
			@RequestParam(value = "type", required = false) List<String> type) {
		return documentDetailsService.findByProjection(pageable, thing, type);
	}
}