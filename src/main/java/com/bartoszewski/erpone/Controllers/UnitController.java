package com.bartoszewski.erpone.Controllers;

import com.bartoszewski.erpone.Models.Unit;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.UnitService;

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
		// TODO Auto-generated constructor stub
		this.unitsService = unitsService;
	}

}