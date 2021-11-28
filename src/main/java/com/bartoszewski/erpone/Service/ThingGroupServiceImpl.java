package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.ThingGroup;
import com.bartoszewski.erpone.Repository.ThingGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ThingGroupServiceImpl implements ThingGroupService {

	ThingGroupRepository thingGroupRepository;

	@Autowired
	public ThingGroupServiceImpl(ThingGroupRepository thingGroupRepository) {
		this.thingGroupRepository = thingGroupRepository;
	}

	@Override
	public ResponseEntity<?> create(ThingGroup entity, Authentication authentication) {
		return new ResponseEntity<>(thingGroupRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<ThingGroup>> readAll(Pageable pageable) {
		return new ResponseEntity<Page<ThingGroup>>(thingGroupRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ThingGroup> readById(Long id) {
		return new ResponseEntity<>(
				thingGroupRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ThingGroup> updateById(Long id, ThingGroup entity) {
		return null;
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		if (thingGroupRepository.getOne(id) != null) {
			thingGroupRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}
