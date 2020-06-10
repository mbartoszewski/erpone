package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.Unit;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.UnitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/units")
public class UnitController extends BaseController<Unit, Long>
{
	UnitService unitsService;

	@Autowired
	public UnitController(BaseService<Unit, Long> service, UnitService unitsService)
	{
		super(service);
		this.unitsService = unitsService;
	}

}