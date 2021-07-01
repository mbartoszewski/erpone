package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.PaymentTerm;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.PaymentTermService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "/payments/terms" })
public class PaymentTermController extends BaseController<PaymentTerm, Long> {
	PaymentTermService paymentTermService;

	@Autowired
	public PaymentTermController(BaseService<PaymentTerm, Long> service, PaymentTermService paymentTermService) {
		super(service);
		this.paymentTermService = paymentTermService;
	}
}
