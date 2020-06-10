package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.Thing;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.ThingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value =
{
        "/things"
})
public class ThingController extends BaseController<Thing, Long>
{
	ThingService thingsService;

	@Autowired
	public ThingController(BaseService<Thing, Long> service, ThingService thingsService)
	{
		super(service);
		this.thingsService = thingsService;
	}
}