package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.Price;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.PriceService;

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
		this.pricesService = pricesService;
	}

}