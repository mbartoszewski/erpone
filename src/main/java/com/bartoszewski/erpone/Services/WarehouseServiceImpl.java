package com.bartoszewski.erpone.Services;

import com.bartoszewski.erpone.Interfaces.WarehouseRepository;
import com.bartoszewski.erpone.Models.Warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WarehouseServiceImpl implements WarehouseService
{
	WarehouseRepository warehouseRepository;

	@Autowired
	public WarehouseServiceImpl(WarehouseRepository warehouseRepository)
	{
		this.warehouseRepository = warehouseRepository;
	}

	@Override
	public ResponseEntity<Warehouse> create(Warehouse entity)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(warehouseRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Warehouse>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Page<Warehouse>>(warehouseRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Warehouse> readById(Long id)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(
		    warehouseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Warehouse> updateById(Long id, Warehouse entity)
	{
		// TODO Auto-generated method stub
		Warehouse warehouse = warehouseRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		warehouse.setCode(entity.getCode());
		warehouse.setName(entity.getName());
		return new ResponseEntity<>(warehouseRepository.save(warehouse), HttpStatus.OK);
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
}