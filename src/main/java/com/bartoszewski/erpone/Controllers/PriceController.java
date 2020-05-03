package com.bartoszewski.erpone.Controllers;

import com.bartoszewski.erpone.Models.Price;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.PriceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value =
{
        "/prices"
})
public class PriceController extends BaseController<Price, Long>
{
	PriceService pricesService;

	@Autowired
	public PriceController(BaseService<Price, Long> service, PriceService pricesService)
	{
		super(service);
		// TODO Auto-generated constructor stub
		this.pricesService = pricesService;
	}

}