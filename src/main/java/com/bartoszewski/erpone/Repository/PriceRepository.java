package com.bartoszewski.erpone.Repository;

import java.time.LocalDate;

import com.bartoszewski.erpone.Entity.Price;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends BaseRepository<Price, Long> {
	@Query("SELECT p FROM Price p WHERE thing.id = :thingId AND ((:startDate IS NULL OR date >= CONCAT(:startDate, 'T00:00:00')) AND (:endDate IS NULL OR date <= CONCAT(:endDate, 'T23:59:59'))) ORDER BY date ASC")
	public Page<Price> getPriceByThingId(Pageable pageable, @Param("thingId") Long thing,
			@Param("startDate") LocalDate startTargetDate, @Param("endDate") LocalDate endTargetDate);
}