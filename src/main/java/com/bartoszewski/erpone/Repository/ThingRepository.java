package com.bartoszewski.erpone.Repository;

import com.bartoszewski.erpone.Entity.Thing;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThingRepository extends BaseRepository<Thing, Long> {
	@Query(value = "SELECT * FROM Thing WHERE MATCH(code, name) AGAINST (:searchQuery'*' IN BOOLEAN MODE)", nativeQuery = true)
	public Page<Thing> searchThingByPropertiesWithMatchAgainst(Pageable pageable,
			@Param("searchQuery") String searchQuery);

	@Query(value = "SELECT * FROM Thing WHERE CONCAT(code, ' ', name) LIKE %:searchQuery%", nativeQuery = true)
	public Page<Thing> searchThingByPropertiesWithLike(Pageable pageable, @Param("searchQuery") String searchQuery);
}