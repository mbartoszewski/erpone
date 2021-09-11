package com.bartoszewski.erpone.Service;

import java.time.LocalDate;
import java.util.List;

import com.bartoszewski.erpone.Entity.Documents.DocumentDetails;
import com.bartoszewski.erpone.Entity.Documents.DocumentsDetailsPriceProjection;
import com.bartoszewski.erpone.Entity.Documents.DocumentsDetailsProjection;
import com.bartoszewski.erpone.Entity.Documents.DocumentsDetailsWithBalanceProjection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface DocumentDetailsService extends BaseService<DocumentDetails, Long> {

	public ResponseEntity<Page<DocumentsDetailsPriceProjection>> findAllOperationsByThingAndType(Pageable pageable,
			Long thing, String type, LocalDate startDate, LocalDate endDate);

	ResponseEntity<Page<DocumentsDetailsProjection>> findLastByProjection(Pageable pageable, Long thing,
			List<String> type);

	public ResponseEntity<Page<DocumentsDetailsWithBalanceProjection>> findDocumentsDetailsWithBalanceProjection(
			Pageable pageable, Long thing, List<String> documentTypeEnum, List<String> documentStatusEnum);
}