package com.bartoszewski.erpone.Service;

import java.time.LocalDate;
import java.util.List;

import com.bartoszewski.erpone.Entity.Documents.Documents;
import com.bartoszewski.erpone.Entity.Documents.DocumentsProjection;
import com.bartoszewski.erpone.Entity.Documents.DocumentsWithDetailsProjection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface DocumentsService extends BaseService<Documents, Long> {
	ResponseEntity<Page<Documents>> findAllByType(Pageable pageable, Long thing, String type, String status,
			LocalDate startDate, LocalDate endDate);

	ResponseEntity<Page<DocumentsProjection>> getDocuments(Pageable pageable, List<String> type, String status,
			LocalDate startDate, LocalDate endDate, String contractor);

	ResponseEntity<DocumentsWithDetailsProjection> getDocumentDetailsById(Long id);
}