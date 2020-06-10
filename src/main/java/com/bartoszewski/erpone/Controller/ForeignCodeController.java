package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.ForeignCode;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.ForeignCodeService;

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
		this.foreignCodesService = foreignCodesService;
	}

}