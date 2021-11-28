package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.Budget;
import com.bartoszewski.erpone.Repository.BudgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BudgetServiceImpl implements BudgetService {

	BudgetRepository budgetRepository;

	@Autowired
	public BudgetServiceImpl(BudgetRepository budgetRepository) {
		this.budgetRepository = budgetRepository;
	}

	@Override
	public ResponseEntity<?> create(Budget entity, Authentication authentication) {
		return new ResponseEntity<>(budgetRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Budget>> readAll(Pageable pageable) {
		return new ResponseEntity<>(budgetRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Budget> readById(Long id) {
		return new ResponseEntity<>(
				budgetRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Budget> updateById(Long id, Budget entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		if (budgetRepository.getOne(id) != null) {
			budgetRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}

}
