package com.bartoszewski.erpone.Service;

import java.time.LocalDate;

import com.bartoszewski.erpone.Entity.Documents.Documents;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface DocumentsService extends BaseService<Documents, Long> {
	ResponseEntity<Page<Documents>> findAllByType(Pageable pageable, Long thing, String type, String status,
			LocalDate startDate, LocalDate endDate);

	ResponseEntity<Page<Documents>> findPurchaseOrderByDetails(Pageable pageable, Long thing, String type,
			String status, LocalDate startTargetDate, LocalDate endTargetDate, String contractor);

	ResponseEntity<Page<Documents>> findProductionOrderByDetails(Pageable pageable, String status,
			LocalDate startTargetDate, LocalDate endTargetDate, Long recipe);
}