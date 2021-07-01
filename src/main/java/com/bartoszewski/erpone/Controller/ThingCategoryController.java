package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.ThingCategory;
import com.bartoszewski.erpone.Service.BaseService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/things/categories")
public class ThingCategoryController extends BaseController<ThingCategory, Long> {

	public ThingCategoryController(BaseService<ThingCategory, Long> service) {
		super(service);
	}

}
