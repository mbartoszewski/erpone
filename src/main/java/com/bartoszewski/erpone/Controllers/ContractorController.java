package com.bartoszewski.erpone.Controllers;

import com.bartoszewski.erpone.Models.Contractor;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.ContractorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contractors")
public class ContractorController extends BaseController<Contractor, Long>
{
	ContractorService contractorsService;

	@Autowired
	public ContractorController(BaseService<Contractor, Long> service, ContractorService contractorsService)
	{
		super(service);
		// TODO Auto-generated constructor stub\
		this.contractorsService = contractorsService;
	}

}