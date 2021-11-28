package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.Budget;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.BudgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
