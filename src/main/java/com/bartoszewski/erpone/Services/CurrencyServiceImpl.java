package com.bartoszewski.erpone.Services;

import com.bartoszewski.erpone.Interfaces.CurrencyRepository;
import com.bartoszewski.erpone.Models.Currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Currency> create(Currency entity)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(currenciesRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Currency>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Page<Currency>>(currenciesRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Currency> readById(Long id)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(
		    currenciesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Currency> updateById(Long id, Currency entity)
	{
		// TODO Auto-generated method stub
		Currency currency = currenciesRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		currency.setCode(entity.getCode());
		currency.setName(entity.getName());
		return new ResponseEntity<>(currenciesRepository.save(currency), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		// TODO Auto-generated method stub
		if (currenciesRepository.getOne(id) != null)
		{
			currenciesRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}