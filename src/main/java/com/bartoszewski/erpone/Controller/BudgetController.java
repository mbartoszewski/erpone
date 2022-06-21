package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.Budget;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.BudgetService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/budget")
public class BudgetController extends BaseController<Budget, Long> {

	BudgetService budgetService;

	@Autowired
	public BudgetController(BaseService<Budget, Long> service, BudgetService budgetService) {
		super(service);
		this.budgetService = budgetService;
	}

	@GetMapping("/all")
	public ResponseEntity<Page<Budget>> getBudgetsList(Pageable pageable,
			@RequestParam(value = "active", required = false) Integer active,
			@RequestParam(value = "type", required = false) List<String> type,
			@RequestParam(value = "year", required = false) List<Integer> year) {

		return budgetService.getBudgetsList(pageable, type, active, year);
	}
}
