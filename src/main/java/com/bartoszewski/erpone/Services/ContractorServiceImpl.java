package com.bartoszewski.erpone.Services;

import com.bartoszewski.erpone.Interfaces.ContractorRepository;
import com.bartoszewski.erpone.Models.Contractor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContractorServiceImpl implements ContractorService
{
	ContractorRepository contractorsRepository;

	@Autowired
	public ContractorServiceImpl(ContractorRepository contractorsRepository)
	{
		this.contractorsRepository = contractorsRepository;
	}

	@Override
	public ResponseEntity<Contractor> create(Contractor entity)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(contractorsRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Contractor>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Page<Contractor>>(contractorsRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Contractor> readById(Long id)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(
		    contractorsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Contractor> updateById(Long id, Contractor entity)
	{
		// TODO Auto-generated method stub
		Contractor contractor = contractorsRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		contractor.setName(entity.getName());
		contractor.setNip(entity.getNip());
		contractor.setRegon(entity.getRegon());
		return new ResponseEntity<>(contractorsRepository.save(contractor), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		// TODO Auto-generated method stub
		if (contractorsRepository.getOne(id) != null)
		{
			contractorsRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}