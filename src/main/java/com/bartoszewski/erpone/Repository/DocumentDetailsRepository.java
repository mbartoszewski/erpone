package com.bartoszewski.erpone.Repository;

import java.time.LocalDate;
import java.util.List;

import com.bartoszewski.erpone.Entity.Documents.DocumentsDetailsProjection;
import com.bartoszewski.erpone.Entity.Documents.DocumentDetails;
import com.bartoszewski.erpone.Enum.DocumentTypeEnum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentDetailsRepository extends BaseRepository<DocumentDetails, Long> {
	@Query("SELECT dd FROM DocumentDetails dd LEFT JOIN Documents doc ON doc.id = dd.document.id WHERE dd.thing.id = :thing AND ((:startDate IS NULL OR doc.createdAt >= CONCAT(:startDate, 'T00:00:00')) AND (:endDate IS NULL OR doc.createdAt <= CONCAT(:endDate, 'T23:59:59'))) AND (doc.documentTypeEnum = 'pw' OR doc.documentTypeEnum = 'pz')")
	public Page<DocumentDetails> findAllIncomeByThing(Pageable pageable, @Param("thing") Long thing,
			@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	@Query("SELECT dd FROM DocumentDetails dd LEFT JOIN Documents doc ON doc.id = dd.document.id WHERE dd.thing.id = :thing AND ((:startDate IS NULL OR doc.createdAt >= CONCAT(:startDate, 'T00:00:00')) AND (:endDate IS NULL OR doc.createdAt <= CONCAT(:endDate, 'T23:59:59'))) AND (doc.documentTypeEnum = 'wz' OR doc.documentTypeEnum = 'wzz' OR doc.documentTypeEnum = 'rw')")
	public Page<DocumentDetails> findAllOutgoingsByThing(Pageable pageable, @Param("thing") Long thing,
			@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	@Query("SELECT dd FROM DocumentDetails dd LEFT JOIN Documents doc ON doc.id = dd.document.id WHERE dd.thing.id = :thing AND ((:startDate IS NULL OR doc.createdAt >= CONCAT(:startDate, 'T00:00:00')) AND (:endDate IS NULL OR doc.createdAt <= CONCAT(:endDate, 'T23:59:59'))) AND (:type IS NULL OR doc.documentTypeEnum = :type)")
	public Page<DocumentDetails> findAllOperationsByThingAndType(Pageable pageable, @Param("thing") Long thing,
			@Param("type") DocumentTypeEnum type, @Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

	@Query("SELECT dd FROM DocumentDetails dd WHERE dd.thing.id = :thing AND dd.document.documentTypeEnum IN :documentTypeEnum ORDER BY dd.document.createdAt DESC")
	public Page<DocumentsDetailsProjection> findbydDocumentsDetailsProjections(Pageable pageable,
			@Param("thing") Long thing, @Param("documentTypeEnum") List<DocumentTypeEnum> documentTypeEnum);
}