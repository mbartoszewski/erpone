package com.bartoszewski.erpone.Controllers;

import com.bartoszewski.erpone.Models.ContactDetail;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.ContactDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value =
{
        "/contractors/contact"
})
public class ContactDetailController extends BaseController<ContactDetail, Long>
{
	ContactDetailService contactDetailsService;

	@Autowired
	public ContactDetailController(BaseService<ContactDetail, Long> service, ContactDetailService contactDetailsService)
	{
		super(service);
		// TODO Auto-generated constructor stub
		this.contactDetailsService = contactDetailsService;
	}

}