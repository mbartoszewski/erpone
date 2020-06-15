package com.bartoszewski.erpone.Repository;

import java.time.LocalDate;

import com.bartoszewski.erpone.Entity.Documents.Documents;
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
}