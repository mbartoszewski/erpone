package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.ForeignCode;
import com.bartoszewski.erpone.Repository.ForeignCodeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ForeignCodeServiceImpl implements ForeignCodeService
{
	ForeignCodeRepository foreignCodesRepository;

	@Autowired
	public ForeignCodeServiceImpl(ForeignCodeRepository foreignCodesRepository)
	{
		this.foreignCodesRepository = foreignCodesRepository;
	}

	@Override
	public ResponseEntity<ForeignCode> create(ForeignCode entity, Authentication authentication)
	{
		return new ResponseEntity<>(foreignCodesRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<ForeignCode>> readAll(Pageable pageable)
	{
		return new ResponseEntity<Page<ForeignCode>>(foreignCodesRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ForeignCode> readById(Long id)
	{
		return new ResponseEntity<>(
		    foreignCodesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ForeignCode> updateById(Long id, ForeignCode entity)
	{
		ForeignCode foreignCode = foreignCodesRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return new ResponseEntity<>(foreignCodesRepository.save(foreignCode), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		if (foreignCodesRepository.getOne(id) != null)
		{
			foreignCodesRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}