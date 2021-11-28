package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.ThingFamily;
import com.bartoszewski.erpone.Repository.ThingFamilyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ThingFamilyServiceImpl implements ThingFamilyService {

	ThingFamilyRepository thingFamilyRepository;

	@Autowired
	public ThingFamilyServiceImpl(ThingFamilyRepository thingFamilyRepository) {
		this.thingFamilyRepository = thingFamilyRepository;
	}

	@Override
	public ResponseEntity<?> create(ThingFamily entity, Authentication authentication) {
		return new ResponseEntity<>(thingFamilyRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<ThingFamily>> readAll(Pageable pageable) {
		return new ResponseEntity<Page<ThingFamily>>(thingFamilyRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ThingFamily> readById(Long id) {
		return new ResponseEntity<>(
				thingFamilyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ThingFamily> updateById(Long id, ThingFamily entity) {
		return null;
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		if (thingFamilyRepository.getOne(id) != null) {
			thingFamilyRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}
