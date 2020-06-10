package com.bartoszewski.erpone.Repository;

import java.time.LocalDate;

import com.bartoszewski.erpone.Entity.Documents.DocumentDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentDetailsRepository extends BaseRepository<DocumentDetails, Long>
{
@Query("SELECT dd FROM DocumentDetails dd LEFT JOIN FETCH Documents doc ON doc.id = dd.document.id WHERE dd.thing.id = :thingId AND (doc.createdAt >= CONCAT(:startDate, 'T00:00:00') AND doc.createdAt <= CONCAT(:endDate, 'T23:59:59')) AND (doc.documentTypeEnum = 'Pw' OR doc.documentTypeEnum = 'Pz')")
public Page<DocumentDetails> findAllIncomeByThing(Pageable pageable, @Param("thingId")Long thingId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
@Query("SELECT dd FROM DocumentDetails dd LEFT JOIN FETCH Documents doc ON doc.id = dd.document.id WHERE dd.thing.id = :thingId AND (doc.createdAt >= CONCAT(:startDate, 'T00:00:00') AND doc.createdAt <= CONCAT(:endDate, 'T23:59:59')) AND (doc.documentTypeEnum = 'Wz' OR doc.documentTypeEnum = 'Wzz' OR doc.documentTypeEnum = 'Rw')")
public Page<DocumentDetails> findAllOutgoingsByThing(Pageable pageable, @Param("thingId")Long thingId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}