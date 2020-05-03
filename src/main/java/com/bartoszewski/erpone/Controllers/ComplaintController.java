package com.bartoszewski.erpone.Controllers;

import com.bartoszewski.erpone.Models.Complaint;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.ComplaintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value =
{
        "/complaints"
})
public class ComplaintController extends BaseController<Complaint, Long>
{
	ComplaintService complaintsService;

	@Autowired
	public ComplaintController(BaseService<Complaint, Long> service, ComplaintService complaintsService)
	{
		super(service);
		// TODO Auto-generated constructor stub
		this.complaintsService = complaintsService;
	}

}