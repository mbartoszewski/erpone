package com.bartoszewski.erpone.Services;

import com.bartoszewski.erpone.Interfaces.PriceRepository;
import com.bartoszewski.erpone.Models.Price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PriceServiceImpl implements PriceService
{
	PriceRepository pricesRepository;

	@Autowired
	public PriceServiceImpl(PriceRepository pricesRepository)
	{
		this.pricesRepository = pricesRepository;
	}

	@Override
	public ResponseEntity<Price> create(Price entity)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(pricesRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Price>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Page<Price>>(pricesRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Price> readById(Long id)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(
		    pricesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Price> updateById(Long id, Price entity)
	{
		// TODO Auto-generated method stub
		Price price = pricesRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		price.setPrice(entity.getPrice());
		return new ResponseEntity<>(pricesRepository.save(price), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		// TODO Auto-generated method stub
		if (pricesRepository.getOne(id) != null)
		{
			pricesRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}