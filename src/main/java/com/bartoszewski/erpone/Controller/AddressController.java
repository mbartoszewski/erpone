package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.Address;
import com.bartoszewski.erpone.Service.AddressService;
import com.bartoszewski.erpone.Service.BaseService;

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
		this.addressService = addressService;
	}

}