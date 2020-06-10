package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.Reservation;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController extends BaseController<Reservation, Long>
{
	ReservationService reservationsService;

	@Autowired
	public ReservationController(BaseService<Reservation, Long> service, ReservationService reservationsService)
	{
		super(service);
		this.reservationsService = reservationsService;
	}

}