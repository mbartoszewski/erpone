package com.bartoszewski.erpone.Controllers;

import com.bartoszewski.erpone.Models.Currency;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.CurrencyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currencies")
public class CurrencyController extends BaseController<Currency, Long>
{
	CurrencyService currenciesService;

	@Autowired
	public CurrencyController(BaseService<Currency, Long> service, CurrencyService currenciesService)
	{
		super(service);
		// TODO Auto-generated constructor stub
		this.currenciesService = currenciesService;
	}

}