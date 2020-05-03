package com.bartoszewski.erpone.Services;

import com.bartoszewski.erpone.Interfaces.UnitRepository;
import com.bartoszewski.erpone.Models.Unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UnitServiceImpl implements UnitService
{
	UnitRepository unitsRepository;

	@Autowired
	public UnitServiceImpl(UnitRepository unitsRepository)
	{
		this.unitsRepository = unitsRepository;
	}

	@Override
	public ResponseEntity<Unit> create(Unit entity)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(unitsRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Unit>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Page<Unit>>(unitsRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Unit> readById(Long id)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Unit>(
		    unitsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Unit> updateById(Long id, Unit entity)
	{
		// TODO Auto-generated method stub
		Unit unit = unitsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		unit.setCode(entity.getCode());
		unit.setName(entity.getName());
		return new ResponseEntity<>(unitsRepository.save(unit), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		if (unitsRepository.getOne(id) != null)
		{
			unitsRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);

	}
}