package com.bartoszewski.erpone.Service;

import java.time.LocalDate;
import java.util.List;

import com.bartoszewski.erpone.Entity.Documents.DocumentDetails;
import com.bartoszewski.erpone.Entity.Documents.DocumentsDetailsProjection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface DocumentDetailsService extends BaseService<DocumentDetails, Long> {
	public ResponseEntity<Page<DocumentDetails>> findAllIncomeByThing(Pageable pageable, Long thing,
			LocalDate startDate, LocalDate endDate);

	public ResponseEntity<Page<DocumentDetails>> findAllOutgoingsByThing(Pageable pageable, Long thing,
			LocalDate startDate, LocalDate endDate);

	public ResponseEntity<Page<DocumentDetails>> findAllOperationsByThingAndType(Pageable pageable, Long thing,
			String type, LocalDate startDate, LocalDate endDate);

	ResponseEntity<Page<DocumentsDetailsProjection>> findByProjection(Pageable pageable, Long thing, List<String> type);
}