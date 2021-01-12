package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.Thing;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ThingService extends BaseService<Thing, Long> {
	public ResponseEntity<Page<Thing>> searchThingByPropertiesWithLike(Pageable pageable, String searchString);

	public ResponseEntity<Page<Thing>> searchThingByPropertiesWithMatchAgainst(Pageable pageable, String searchString);

}