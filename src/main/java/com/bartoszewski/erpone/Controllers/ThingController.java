package com.bartoszewski.erpone.Controllers;

import com.bartoszewski.erpone.Models.Thing;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.ThingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value =
{
        "/warehouses/things"
})
public class ThingController extends BaseController<Thing, Long>
{
	ThingService thingsService;

	@Autowired
	public ThingController(BaseService<Thing, Long> service, ThingService thingsService)
	{
		super(service);
		// TODO Auto-generated constructor stub
		this.thingsService = thingsService;
	}

	@GetMapping(value = "/test")
	private Thing test()
	{
		return thingsService.findByWarehouseAndId(2L, 2L);
	}
}