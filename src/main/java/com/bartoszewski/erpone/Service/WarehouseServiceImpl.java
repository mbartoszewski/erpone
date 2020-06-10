package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.Warehouse;
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
public class WarehouseServiceImpl implements WarehouseService {
	WarehouseRepository warehouseRepository;

	@Autowired
	public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
		this.warehouseRepository = warehouseRepository;
	}

	@Override
	public ResponseEntity<Warehouse> create(Warehouse entity, Authentication authentication) {
		return new ResponseEntity<>(warehouseRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Warehouse>> readAll(Pageable pageable) {
		return new ResponseEntity<Page<Warehouse>>(warehouseRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Warehouse> readById(Long id) {
		return new ResponseEntity<>(
				warehouseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Warehouse> updateById(Long id, Warehouse entity) {
		Warehouse warehouse = warehouseRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		warehouse.setCode(entity.getCode());
		warehouse.setName(entity.getName());
		return new ResponseEntity<>(warehouseRepository.save(warehouse), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		if (warehouseRepository.getOne(id) != null) {
			warehouseRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}