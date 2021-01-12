package com.bartoszewski.erpone.Service;

import java.time.LocalDate;

import com.bartoszewski.erpone.Entity.Price;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PriceService extends BaseService<Price, Long> {
	ResponseEntity<Page<Price>> getPriceByThingId(Pageable pageable, Long thing, LocalDate startDate,
			LocalDate endDate);
}