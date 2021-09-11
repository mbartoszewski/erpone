package com.bartoszewski.erpone.Service;

import java.time.LocalDate;
import java.util.List;

import com.bartoszewski.erpone.Entity.Thing;
import com.bartoszewski.erpone.Entity.Projections.AllThingsWarehouse;
import com.bartoszewski.erpone.Entity.Projections.SearchThingsByProperties;
import com.bartoszewski.erpone.Entity.Projections.ThingsValueByProperties;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ThingService extends BaseService<Thing, Long> {
	public ResponseEntity<Page<AllThingsWarehouse>> getAllThingsWarehouse(Pageable pageable);

	public ResponseEntity<Page<SearchThingsByProperties>> searchThingByPropertiesWithLike(Pageable pageable,
			String searchString);

	public ResponseEntity<Page<SearchThingsByProperties>> searchThingByPropertiesWithMatchAgainst(Pageable pageable,
			String searchString);

	public ResponseEntity<Page<ThingsValueByProperties>> getThingsValueByProperties(Pageable pageable,
			List<Long> categoriesId, List<Long> thingsId, List<Long> contractorsId, LocalDate dateFrom,
			LocalDate dateTo);
}