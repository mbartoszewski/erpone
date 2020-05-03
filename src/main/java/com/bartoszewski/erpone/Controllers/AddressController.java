package com.bartoszewski.erpone.Controllers;

import com.bartoszewski.erpone.Models.Address;
import com.bartoszewski.erpone.Services.AddressService;
import com.bartoszewski.erpone.Services.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value =
{
        "/contractors/address"
})
public class AddressController extends BaseController<Address, Long>
{
	AddressService addressService;

	@Autowired
	public AddressController(BaseService<Address, Long> service, AddressService addressService)
	{
		super(service);
		// TODO Auto-generated constructor stub
		this.addressService = addressService;
	}

}