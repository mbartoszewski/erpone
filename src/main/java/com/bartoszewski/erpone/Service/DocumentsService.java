package com.bartoszewski.erpone.Service;

import java.time.LocalDate;
import java.util.List;

import com.bartoszewski.erpone.Entity.Documents.DocumentValueProjection;
import com.bartoszewski.erpone.Entity.Documents.Documents;
import com.bartoszewski.erpone.Entity.Documents.DocumentsProjection;
import com.bartoszewski.erpone.Entity.Documents.DocumentsWithDetailsProjection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface DocumentsService extends BaseService<Documents, Long> {
	ResponseEntity<Page<Documents>> findAllByType(Pageable pageable, Long thing, List<String> type, List<String> status,
			LocalDate dateFrom, LocalDate dateTo);

	ResponseEntity<Page<DocumentsProjection>> getDocuments(Pageable pageable, List<String> type, List<String> status,
			LocalDate targetDateFrom, LocalDate targetDateTo, String contractor);

	ResponseEntity<DocumentsWithDetailsProjection> getDocumentDetailsById(Long id);

	ResponseEntity<Page<DocumentValueProjection>> getDocumentValue(Pageable pageable, List<String> type,
			List<String> status, List<Long> contractor, LocalDate targetDateFrom, LocalDate targetDateTo,
			String dateFrom, String dateTo);
}