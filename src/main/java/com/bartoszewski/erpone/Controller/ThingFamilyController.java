package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.ThingFamily;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.ThingFamilyService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/things/family")
public class ThingFamilyController extends BaseController<ThingFamily, Long> {

	ThingFamilyService thingFamilyService;

	public ThingFamilyController(BaseService<ThingFamily, Long> service, ThingFamilyService thingFamilyService) {
		super(service);
		this.thingFamilyService = thingFamilyService;
	}

}
