package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.Currency;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.CurrencyService;

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
		this.currenciesService = currenciesService;
	}

}