package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.ContactDetail;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.ContactDetailService;

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
		this.contactDetailsService = contactDetailsService;
	}

}