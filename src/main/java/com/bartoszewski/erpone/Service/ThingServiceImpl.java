package com.bartoszewski.erpone.Service;

import java.time.LocalDate;
import java.util.List;

import com.bartoszewski.erpone.Entity.Thing;
import com.bartoszewski.erpone.Entity.Projections.SearchThingsByProperties;
import com.bartoszewski.erpone.Entity.Projections.ThingsValueByProperties;
import com.bartoszewski.erpone.Repository.ThingRepository;
import com.bartoszewski.erpone.Repository.UnitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ThingServiceImpl implements ThingService {
	private final ThingRepository thingsRepository;
	private UnitRepository unitsRepository;

	@Autowired
	public ThingServiceImpl(ThingRepository thingsRepository, UnitRepository unitsRepository) {
		this.thingsRepository = thingsRepository;
		this.unitsRepository = unitsRepository;
	}

	@Override
	public ResponseEntity<Thing> create(Thing entity, Authentication authentication) {
		// entity.setCode(entity.getCode().toUpperCase());
		return new ResponseEntity<>(thingsRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Thing>> readAll(Pageable pageable) {
		return new ResponseEntity<Page<Thing>>(thingsRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Thing> readById(Long id) {
		return new ResponseEntity<>(
				thingsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Thing> updateById(Long id, Thing entity) {
		Thing thing = thingsRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		thing.setCode(entity.getCode());
		thing.setActive(entity.getActive());
		thing.setName(entity.getName());
		thing.setUnit(unitsRepository.getOne(entity.getUnit().getId()));
		return new ResponseEntity<>(thingsRepository.save(thing), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		Thing thing = thingsRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		thing.setActive(0);
		return new ResponseEntity<>(thingsRepository.save(thing), HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Page<SearchThingsByProperties>> searchThingByPropertiesWithLike(Pageable pageable,
			String searchQuery) {
		return new ResponseEntity<>(thingsRepository.searchThingByPropertiesWithLike(pageable, searchQuery),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<SearchThingsByProperties>> searchThingByPropertiesWithMatchAgainst(Pageable pageable,
			String searchQuery) {
		return new ResponseEntity<>(thingsRepository.searchThingByPropertiesWithMatchAgainst(pageable, searchQuery),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<com.bartoszewski.erpone.Entity.Projections.AllThingsWarehouse>> getAllThingsWarehouse(
			Pageable pageable) {
		return new ResponseEntity<>(thingsRepository.getAllThingsWarehouse(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<ThingsValueByProperties>> getThingsValueByProperties(Pageable pageable,
			List<Long> categoriesId, List<Long> thingsId, List<Long> contractorsId, LocalDate dateFrom,
			LocalDate dateTo) {

		return new ResponseEntity<>(thingsRepository.getThingsValueByProperties(pageable, categoriesId, thingsId,
				contractorsId, dateFrom, dateTo), HttpStatus.OK);
	}
}