package com.bartoszewski.erpone.Controllers;

import com.bartoszewski.erpone.Models.ForeignCode;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.ForeignCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foreigncodes")
public class ForeignCodeController extends BaseController<ForeignCode, Long>
{
	ForeignCodeService foreignCodesService;

	@Autowired
	public ForeignCodeController(BaseService<ForeignCode, Long> service, ForeignCodeService foreignCodesService)
	{
		super(service);
		// TODO Auto-generated constructor stub
		this.foreignCodesService = foreignCodesService;
	}

}