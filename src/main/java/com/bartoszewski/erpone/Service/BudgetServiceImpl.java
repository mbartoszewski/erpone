package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.Budget;
import com.bartoszewski.erpone.Enum.BudgetTypeEnum;
import com.bartoszewski.erpone.Repository.BudgetRepository;
import com.bartoszewski.erpone.Repository.UnitRepository;

import java.util.List;
import java.util.stream.Collectors;

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
	UnitRepository unitRepository;

	@Autowired
	public BudgetServiceImpl(BudgetRepository budgetRepository, UnitRepository unitRepository) {
		this.budgetRepository = budgetRepository;
		this.unitRepository = unitRepository;
	}

	@Override
	public ResponseEntity<?> create(Budget entity, Authentication authentication) {
		entity.setUnit(unitRepository.getOne(entity.getUnit().getId()));
		entity.addBudgeValue(entity.getBudgetValues());
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

	@Override
	public ResponseEntity<Page<Budget>> getBudgetsList(Pageable pageable, List<String> type, Integer active,
			List<Integer> year) {
		List<BudgetTypeEnum> typeEnum = type != null
				? type.stream().map((t) -> BudgetTypeEnum.valueOf(t)).collect(Collectors.toList())
				: null;
		return new ResponseEntity<>(budgetRepository.getBudgetsList(pageable, typeEnum, active, year), HttpStatus.OK);
	}

}
