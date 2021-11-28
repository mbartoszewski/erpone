package com.bartoszewski.erpone.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.bartoszewski.erpone.Entity.Documents.DocumentValueProjection;
import com.bartoszewski.erpone.Entity.Documents.Documents;
import com.bartoszewski.erpone.Entity.Documents.DocumentsProjection;
import com.bartoszewski.erpone.Entity.Documents.DocumentsWithDetailsProjection;
import com.bartoszewski.erpone.Enum.DocumentTypeEnum;
import com.bartoszewski.erpone.Enum.DocumentStatusEnum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsRepository extends BaseRepository<Documents, Long> {
	@Query("SELECT DISTINCT d FROM Documents d LEFT JOIN DocumentDetails dd ON dd.document.id = d.id WHERE dd.thing.id = :thing AND ((:dateFrom IS NULL OR d.createdAt >= CONCAT(:dateFrom, 'T00:00:00')) AND (:dateTo IS NULL OR d.createdAt <= CONCAT(:dateTo, 'T23:59:59'))) AND (COALESCE(:type) IS NULL OR d.documentTypeEnum IN :type) AND (COALESCE(:status) IS NULL OR d.documentStatusEnum IN :status)")
	public Page<Documents> findAllByType(Pageable pageable, @Param("thing") Long thing,
			@Param("type") List<DocumentTypeEnum> type, @Param("status") List<DocumentStatusEnum> status,
			@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);

	@Query("SELECT COUNT(d) FROM Documents d WHERE d.createdAt >= CONCAT(:year, '-01-01T00:00:00') AND d.createdAt <= CONCAT(:year, '-12-31T23:59:59') AND d.documentTypeEnum = :documentTypeEnum")
	Long countByYear(int year, DocumentTypeEnum documentTypeEnum);

	@Query("SELECT d FROM Documents d WHERE ((:targetDateFrom IS NULL OR d.targetDateTime >= CONCAT(:targetDateFrom, 'T00:00:00')) AND (:targetDateTo IS NULL OR d.targetDateTime <= CONCAT(:targetDateTo, 'T23:59:59'))) AND (COALESCE(:type) IS NULL OR d.documentTypeEnum IN :type) AND (COALESCE(:status) IS NULL OR d.documentStatusEnum = :status) AND (:contractor IS NULL OR d.contractor.name LIKE %:contractor%) ORDER BY d.createdAt DESC ")
	Page<DocumentsProjection> getDocuments(Pageable pageable, @Param("type") List<DocumentTypeEnum> documentTypeEnum,
			@Param("status") List<DocumentStatusEnum> status, @Param("targetDateFrom") LocalDate targetDateFrom,
			@Param("targetDateTo") LocalDate targetDateTo, @Param("contractor") String contractor);

	@Query("SELECT d FROM Documents d WHERE d.id = :id")
	DocumentsWithDetailsProjection getDocumentDetailsById(Long id);

	@Query("SELECT d FROM Documents d WHERE ((:dateFrom IS NULL OR d.createdAt >= :dateFrom) AND (:dateTo IS NULL OR d.createdAt <= :dateTo)) AND ((:targetDateFrom IS NULL OR d.targetDateTime >= CONCAT(:targetDateFrom, 'T00:00:00')) AND (:targetDateTo IS NULL OR d.targetDateTime <= CONCAT(:targetDateTo, 'T23:59:59'))) AND (COALESCE(:type) IS NULL OR d.documentTypeEnum IN :type) AND (COALESCE(:status) IS NULL OR d.documentStatusEnum IN :status) AND (COALESCE(:contractor) IS NULL OR d.contractor.id IN :contractor)")
	Page<DocumentValueProjection> getDocumentValue(Pageable pageable,
			@Param("type") List<DocumentTypeEnum> documentTypeEnum, @Param("status") List<DocumentStatusEnum> status,
			@Param("contractor") List<Long> contractor, @Param("targetDateFrom") LocalDate targetDateFrom,
			@Param("targetDateTo") LocalDate targetDateTo, @Param("dateFrom") LocalDateTime dateFrom,
			@Param("dateTo") LocalDateTime dateTo);
}

/*
 * @Query("SELECT DISTINCT d FROM Documents d LEFT JOIN DocumentDetails dd ON dd.document.id = d.id WHERE (:thing IS NULL OR dd.thing.id = :thing) AND ((:targetDateFrom IS NULL OR d.targetDateTime >= CONCAT(:targetDateFrom, 'T00:00:00')) AND (:targetDateTo IS NULL OR d.targetDateTime <= CONCAT(:targetDateTo, 'T23:59:59'))) AND (:type IS NULL OR d.documentTypeEnum = :type) AND (:status IS NULL OR d.statusTypeEnum = :status) AND (:contractor IS NULL OR d.contractor.name LIKE %:contractor%)"
 * ) public Page<Documents> findPurchaseOrderByDetails(Pageable
 * pageable, @Param("thing") Long thing,
 * 
 * @Param("type") DocumentTypeEnum type, @Param("status") StatusTypeEnum status,
 * 
 * @Param("targetDateFrom") LocalDate targetDateFrom, @Param("targetDateTo")
 * LocalDate targetDateTo,
 * 
 * @Param("contractor") String contractor);
 * 
 * @Query("SELECT d FROM Documents d LEFT JOIN ProductionOrderDocumentDetails podd ON podd.document.id = d.id WHERE (:status IS NULL OR d.statusTypeEnum = :status) AND ((:targetDateFrom IS NULL OR d.targetDateTime >= CONCAT(:targetDateFrom, 'T00:00:00')) AND (:targetDateTo IS NULL OR d.targetDateTime <= CONCAT(:targetDateTo, 'T23:59:59'))) AND (:recipe IS NULL OR podd.recipe.id = :recipe) AND d.documentTypeEnum = 'zp'"
 * ) public Page<Documents> findProductionOrderByDetails(Pageable
 * pageable, @Param("status") StatusTypeEnum status,
 * 
 * @Param("targetDateFrom") LocalDate targetDateFrom, @Param("targetDateTo")
 * LocalDate targetDateTo,
 * 
 * @Param("recipe") Long recipe);
 */