package com.bartoszewski.erpone.Controller;

import java.time.LocalDate;

import com.bartoszewski.erpone.Entity.Documents.Documents;
import com.bartoszewski.erpone.Entity.Documents.DocumentsProjection;
import com.bartoszewski.erpone.Entity.Documents.DocumentsWithDetailsProjection;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.DocumentsService;

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
@RequestMapping("/documents")
public class DocumentsController extends BaseController<Documents, Long> {
	DocumentsService documentsService;

	@Autowired
	public DocumentsController(BaseService<Documents, Long> service, DocumentsService documentsService) {
		super(service);
		this.documentsService = documentsService;
	}

	@GetMapping("")
	public ResponseEntity<Page<Documents>> findAllByType(Pageable pageable,
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@RequestParam("thing") Long thing, @RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "status", required = false) String status) {
		return documentsService.findAllByType(pageable, thing, type, status, startDate, endDate);
	}

	@GetMapping("/orders/purchase")
	public ResponseEntity<Page<Documents>> findPurchaseOrderByDetails(Pageable pageable,
			@RequestParam(value = "startTargetDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(value = "endTargetDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@RequestParam(value = "thing", required = false) Long thing,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "contractor", required = false) String contractor) {
		return documentsService.findPurchaseOrderByDetails(pageable, thing, type, status, startDate, endDate,
				contractor);
	}

	@GetMapping("/orders/production")
	public ResponseEntity<Page<Documents>> findProductionOrderByDetails(Pageable pageable,
			@RequestParam(value = "startTargetDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(value = "endTargetDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "recipe", required = false) Long recipe) {
		return documentsService.findProductionOrderByDetails(pageable, status, startDate, endDate, recipe);
	}

	@GetMapping("/all")
	public ResponseEntity<Page<DocumentsProjection>> getAllDocuments(Pageable pageable) {
		return documentsService.getDocuments(pageable);
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<Page<DocumentsWithDetailsProjection>> getDocumentDetailsById(Pageable pageable,
			@PathVariable(value = "id") Long id) {
		return documentsService.getDocumentDetailsById(pageable, id);
	}
}