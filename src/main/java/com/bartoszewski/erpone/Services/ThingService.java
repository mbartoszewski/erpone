package com.bartoszewski.erpone.Services;

import com.bartoszewski.erpone.Models.Thing;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ThingService extends BaseService<Thing, Long>
{
	Page<Thing> findAllByWarehouse(Pageable p, Long warehouse);

	Thing findByWarehouseAndId(Long warehouse, Long thing);
}