package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.PaymentForm;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.PaymentFormService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "/payments/forms" })
public class PaymentFormController extends BaseController<PaymentForm, Long> {
	PaymentFormService paymentFormService;

	@Autowired
	public PaymentFormController(BaseService<PaymentForm, Long> service, PaymentFormService paymentFormService) {
		super(service);
		this.paymentFormService = paymentFormService;
	}
}
