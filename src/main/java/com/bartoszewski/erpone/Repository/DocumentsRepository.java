package com.bartoszewski.erpone.Repository;

import java.time.LocalDate;

import com.bartoszewski.erpone.Entity.Documents.Documents;
import com.bartoszewski.erpone.Entity.Documents.DocumentsProjection;
import com.bartoszewski.erpone.Entity.Documents.DocumentsWithDetailsProjection;
import com.bartoszewski.erpone.Enum.DocumentTypeEnum;
import com.bartoszewski.erpone.Enum.StatusTypeEnum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsRepository extends BaseRepository<Documents, Long> {
	@Query("SELECT DISTINCT d FROM Documents d LEFT JOIN DocumentDetails dd ON dd.document.id = d.id WHERE dd.thing.id = :thing AND ((:startDate IS NULL OR d.createdAt >= CONCAT(:startDate, 'T00:00:00')) AND (:endDate IS NULL OR d.createdAt <= CONCAT(:endDate, 'T23:59:59'))) AND (:type IS NULL OR d.documentTypeEnum = :type) AND (:status IS NULL OR d.statusTypeEnum = :status)")
	public Page<Documents> findAllByType(Pageable pageable, @Param("thing") Long thing,
			@Param("type") DocumentTypeEnum type, @Param("status") StatusTypeEnum status,
			@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	@Query("SELECT DISTINCT d FROM Documents d LEFT JOIN DocumentDetails dd ON dd.document.id = d.id WHERE (:thing IS NULL OR dd.thing.id = :thing) AND ((:startTargetDate IS NULL OR d.targetDateTime >= CONCAT(:startTargetDate, 'T00:00:00')) AND (:endTargetDate IS NULL OR d.targetDateTime <= CONCAT(:endTargetDate, 'T23:59:59'))) AND (:type IS NULL OR d.documentTypeEnum = :type) AND (:status IS NULL OR d.statusTypeEnum = :status) AND (:contractor IS NULL OR d.contractor.name LIKE %:contractor%)")
	public Page<Documents> findPurchaseOrderByDetails(Pageable pageable, @Param("thing") Long thing,
			@Param("type") DocumentTypeEnum type, @Param("status") StatusTypeEnum status,
			@Param("startTargetDate") LocalDate startTargetDate, @Param("endTargetDate") LocalDate endTargetDate,
			@Param("contractor") String contractor);

	@Query("SELECT d FROM Documents d LEFT JOIN ProductionOrderDocumentDetails podd ON podd.document.id = d.id WHERE (:status IS NULL OR d.statusTypeEnum = :status) AND ((:startTargetDate IS NULL OR d.targetDateTime >= CONCAT(:startTargetDate, 'T00:00:00')) AND (:endTargetDate IS NULL OR d.targetDateTime <= CONCAT(:endTargetDate, 'T23:59:59'))) AND (:recipe IS NULL OR podd.recipe.id = :recipe) AND d.documentTypeEnum = 'zp'")
	public Page<Documents> findProductionOrderByDetails(Pageable pageable, @Param("status") StatusTypeEnum status,
			@Param("startTargetDate") LocalDate startTargetDate, @Param("endTargetDate") LocalDate endTargetDate,
			@Param("recipe") Long recipe);

	@Query("SELECT COUNT(d) FROM Documents d WHERE d.createdAt >= CONCAT(:year, '-01-01T00:00:00') AND d.createdAt <= CONCAT(:year, '-12-31T23:59:59') AND d.documentTypeEnum = :documentTypeEnum")
	Long countByYear(int year, DocumentTypeEnum documentTypeEnum);

	@Query("SELECT d FROM Documents d ORDER BY d.createdAt DESC")
	Page<DocumentsProjection> getDocuments(Pageable pageable);

	@Query("SELECT d FROM Documents d WHERE d.id = :id")
	Page<DocumentsWithDetailsProjection> getDocumentDetailsById(Pageable pageable, Long id);
}