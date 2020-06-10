package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.Currency;
import com.bartoszewski.erpone.Repository.CurrencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CurrencyServiceImpl implements CurrencyService
{
	CurrencyRepository currenciesRepository;

	@Autowired
	public CurrencyServiceImpl(CurrencyRepository currenciesRepository)
	{
		this.currenciesRepository = currenciesRepository;
	}

	@Override
	public ResponseEntity<Currency> create(Currency entity, Authentication authentication)
	{
		return new ResponseEntity<>(currenciesRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Currency>> readAll(Pageable pageable)
	{
		return new ResponseEntity<Page<Currency>>(currenciesRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Currency> readById(Long id)
	{
		return new ResponseEntity<>(
		    currenciesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Currency> updateById(Long id, Currency entity)
	{
		Currency currency = currenciesRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		currency.setCode(entity.getCode());
		currency.setName(entity.getName());
		return new ResponseEntity<>(currenciesRepository.save(currency), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		if (currenciesRepository.getOne(id) != null)
		{
			currenciesRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}