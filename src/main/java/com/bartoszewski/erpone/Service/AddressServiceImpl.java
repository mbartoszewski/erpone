package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.Address;
import com.bartoszewski.erpone.Repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AddressServiceImpl implements AddressService {
	AddressRepository addressesRepository;

	@Autowired
	public AddressServiceImpl(AddressRepository addressesRepository) {
		this.addressesRepository = addressesRepository;
	}

	@Override
	public ResponseEntity<Address> create(Address entity, Authentication authentication) {
		return new ResponseEntity<>(addressesRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Address>> readAll(Pageable pageable) {
		return new ResponseEntity<>(addressesRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Address> readById(Long id) {

		return new ResponseEntity<>(
				addressesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Address> updateById(Long id, Address entity) {
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
	public ResponseEntity<?> deleteById(Long id) {
		if (addressesRepository.getOne(id) != null) {
			addressesRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}