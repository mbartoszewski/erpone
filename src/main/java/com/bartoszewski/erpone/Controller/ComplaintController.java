package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.Complaint;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.ComplaintService;

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
		this.complaintsService = complaintsService;
	}

}