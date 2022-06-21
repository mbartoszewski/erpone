package com.bartoszewski.erpone.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.bartoszewski.erpone.Entity.Budget;

public interface BudgetService extends BaseService<Budget, Long> {
	ResponseEntity<Page<Budget>> getBudgetsList(Pageable pageable, List<String> type, Integer active,
			List<Integer> year);
}
