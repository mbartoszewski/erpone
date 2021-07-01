package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.ThingCategory;
import com.bartoszewski.erpone.Repository.ThingCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ThingCategoryServiceImpl implements ThingCategoryService {

	ThingCategoryRepository thingCategoryRepository;

	@Autowired
	public ThingCategoryServiceImpl(ThingCategoryRepository thingCategoryRepository) {
		this.thingCategoryRepository = thingCategoryRepository;
	}

	@Override
	public ResponseEntity<?> create(ThingCategory entity, Authentication authentication) {
		return new ResponseEntity<>(thingCategoryRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<ThingCategory>> readAll(Pageable pageable) {
		return new ResponseEntity<Page<ThingCategory>>(thingCategoryRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ThingCategory> readById(Long id) {
		return new ResponseEntity<>(thingCategoryRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ThingCategory> updateById(Long id, ThingCategory entity) {
		return null;
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		if (thingCategoryRepository.getOne(id) != null) {
			thingCategoryRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}
