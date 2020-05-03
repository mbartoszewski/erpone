package com.bartoszewski.erpone.Services;

import com.bartoszewski.erpone.Interfaces.ThingRepository;
import com.bartoszewski.erpone.Interfaces.UnitRepository;
import com.bartoszewski.erpone.Interfaces.WarehouseRepository;
import com.bartoszewski.erpone.Models.Thing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Thing> create(Thing entity)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(thingsRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Thing>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Page<Thing>>(thingsRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Thing> readById(Long id)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(
		    thingsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Thing> updateById(Long id, Thing entity)
	{
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		if (warehouseRepository.getOne(id) != null)
		{
			warehouseRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}

	@Override
	public Page<Thing> findAllByWarehouse(Pageable p, Long warehouse)
	{
		// TODO Auto-generated method stub
		return thingsRepository.findAllByWarehouse(p, warehouse);
	}

	@Override
	public Thing findByWarehouseAndId(Long warehouse, Long thing)
	{
		// TODO Auto-generated method stub
		return thingsRepository.findByWarehouseAndId(warehouse, thing);
	}
}