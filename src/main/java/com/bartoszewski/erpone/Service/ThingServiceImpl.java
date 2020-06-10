package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.Thing;
import com.bartoszewski.erpone.Repository.ThingRepository;
import com.bartoszewski.erpone.Repository.UnitRepository;
import com.bartoszewski.erpone.Repository.WarehouseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ThingServiceImpl implements ThingService
{
	private final ThingRepository thingsRepository;
	private WarehouseRepository warehouseRepository;
	private UnitRepository unitsRepository;

	@Autowired
	public ThingServiceImpl(ThingRepository thingsRepository, WarehouseRepository warehouseRepository,
	    UnitRepository unitsRepository)
	{
		this.thingsRepository = thingsRepository;
		this.warehouseRepository = warehouseRepository;
		this.unitsRepository = unitsRepository;
	}

	@Override
	public ResponseEntity<Thing> create(Thing entity, Authentication authentication)
	{
		return new ResponseEntity<>(thingsRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Thing>> readAll(Pageable pageable)
	{
		return new ResponseEntity<Page<Thing>>(thingsRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Thing> readById(Long id)
	{
		return new ResponseEntity<>(
		    thingsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Thing> updateById(Long id, Thing entity)
	{
		Thing thing = thingsRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		thing.setCode(entity.getCode());
		thing.setActive(entity.getActive());
		thing.setName(entity.getName());
		thing.setUnit(unitsRepository.getOne(entity.getUnit().getId()));
		return new ResponseEntity<>(thingsRepository.save(thing), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		if (warehouseRepository.getOne(id) != null)
		{
			warehouseRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}