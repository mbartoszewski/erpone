package com.bartoszewski.erpone.Services;

import com.bartoszewski.erpone.Interfaces.AddressRepository;
import com.bartoszewski.erpone.Models.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AddressServiceImpl implements AddressService
{
	AddressRepository addressesRepository;

	@Autowired
	public AddressServiceImpl(AddressRepository addressesRepository)
	{
		this.addressesRepository = addressesRepository;
	}

	@Override
	public ResponseEntity<Address> create(Address entity)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(addressesRepository.save(entity), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Page<Address>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(addressesRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Address> readById(Long id)
	{
		// TODO Auto-generated method stub

		return new ResponseEntity<>(
		    addressesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Address> updateById(Long id, Address entity)
	{
		Address address = addressesRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		address.setCity(entity.getCity());
		address.setCountry(entity.getCountry());
		address.setNumber(entity.getNumber());
		address.setPostalCode(entity.getPostalCode());
		address.setStreet(entity.getStreet());
		return new ResponseEntity<>(addressesRepository.save(address), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		// TODO Auto-generated method stub
		if (addressesRepository.getOne(id) != null)
		{
			addressesRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}