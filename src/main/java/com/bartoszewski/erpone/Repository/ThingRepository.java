package com.bartoszewski.erpone.Repository;

import java.time.LocalDate;
import java.util.List;

import com.bartoszewski.erpone.Entity.Thing;
import com.bartoszewski.erpone.Entity.Projections.AllThingsWarehouse;
import com.bartoszewski.erpone.Entity.Projections.SearchThingsByProperties;
import com.bartoszewski.erpone.Entity.Projections.ThingsListToLoad;
import com.bartoszewski.erpone.Entity.Projections.ThingsValueByProperties;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThingRepository extends BaseRepository<Thing, Long> {
	@Query(value = "SELECT t from Thing t")
	public Page<AllThingsWarehouse> getAllThingsWarehouse(Pageable pageable);

	@Query(value = "SELECT * FROM Thing WHERE MATCH(code, name) AGAINST (:searchQuery)", nativeQuery = true)
	public Page<SearchThingsByProperties> searchThingByPropertiesWithMatchAgainst(Pageable pageable,
			@Param("searchQuery") String searchQuery);

	@Query(value = "SELECT t FROM Thing as t WHERE CONCAT(code, ' ', name) LIKE %:searchQuery%")
	public Page<SearchThingsByProperties> searchThingByPropertiesWithLike(Pageable pageable,
			@Param("searchQuery") String searchQuery);

	@Query(value = "SELECT dd FROM DocumentDetails as dd WHERE dd.balance > 0 AND ((:dateFrom IS NULL OR dd.document.createdAt >= CONCAT(:dateFrom, 'T00:00:00')) AND (:dateTo IS NULL OR dd.document.createdAt <= CONCAT(:dateTo, 'T23:59:59'))) AND (:thingsId IS NULL OR dd.thing.id IN :thingsId) AND (:groupId IS NULL OR dd.thing.thingGroup.id IN :groupId) AND (:familyId IS NULL OR dd.thing.thingFamily.id IN :familyId) AND (:contractorsId IS NULL OR dd.document.contractor.id IN :contractorsId)")
	public Page<ThingsValueByProperties> getThingsValueByProperties(Pageable pageable,
			@Param("groupId") List<Long> groupId, @Param("familyId") List<Long> familyId,
			@Param("thingsId") List<Long> thingsId, @Param("contractorsId") List<Long> contractorsId,
			@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate endDate);

	@Query(value = "SELECT t from Thing t")
	public Page<ThingsListToLoad> getThingsListToLoad(Pageable pageable);
}