package com.bartoszewski.erpone.Controllers;

import com.bartoszewski.erpone.Models.Reservation;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.ReservationService;

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
		// TODO Auto-generated constructor stub
		this.reservationsService = reservationsService;
	}

}