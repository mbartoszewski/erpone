package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.Contractor;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.ContractorService;

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
		this.contractorsService = contractorsService;
	}

}