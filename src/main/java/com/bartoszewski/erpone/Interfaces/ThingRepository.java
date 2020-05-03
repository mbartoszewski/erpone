package com.bartoszewski.erpone.Interfaces;

import com.bartoszewski.erpone.Models.Thing;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThingRepository extends BaseRepository<Thing, Long>
{

	@Query("SELECT t FROM Things t WHERE code LIKE %:name% OR code LIKE %:description%")
	Page<Thing> findByNameNDescription(Pageable p, @Param("name") String name, @Param("description") String description);

	@Query("SELECT t FROM Things t WHERE warehouse_id LIKE :warehouse")
	Page<Thing> findAllByWarehouse(Pageable p, @Param("warehouse") Long warehouse);

	@Query("SELECT t FROM Things t WHERE warehouse_id = :warehouse AND thing_id = :thing")
	Thing findByWarehouseAndId(@Param("warehouse") Long warehouse, @Param("thing") Long thing);
}