package com.bartoszewski.erpone.Controller;

import java.time.LocalDate;
import java.util.List;

import com.bartoszewski.erpone.Entity.Documents.DocumentValueProjection;
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
	public ResponseEntity<Page<DocumentsProjection>> getAllDocuments(Pageable pageable,
			@RequestParam(value = "targetDateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate targetDateFrom,
			@RequestParam(value = "targetDateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate targetDateTo,
			@RequestParam(value = "type", required = false) List<String> type,
			@RequestParam(value = "status", required = false) List<String> status,
			@RequestParam(value = "contractor", required = false) String contractor) {
		return documentsService.getDocuments(pageable, type, status, targetDateFrom, targetDateTo, contractor);
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<DocumentsWithDetailsProjection> getDocumentDetailsById(@PathVariable(value = "id") Long id) {
		return documentsService.getDocumentDetailsById(id);
	}

	@GetMapping("/value")
	public ResponseEntity<Page<DocumentValueProjection>> getDocumentValue(Pageable pageable,
			@RequestParam(value = "targetDateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate targetDateFrom,
			@RequestParam(value = "targetDateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate targetDateTo,
			@RequestParam(value = "dateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateFrom,
			@RequestParam(value = "dateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateTo,
			@RequestParam(value = "type", required = false) List<String> type,
			@RequestParam(value = "status", required = false) List<String> status,
			@RequestParam(value = "contractor", required = false) List<Long> contractor) {

		return documentsService.getDocumentValue(pageable, type, status, contractor, targetDateFrom, targetDateTo,
				dateFrom, dateTo);
	}
}