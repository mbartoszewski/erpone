package com.bartoszewski.erpone.Services;

import com.bartoszewski.erpone.Interfaces.ReservationRepository;
import com.bartoszewski.erpone.Models.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservationServiceImpl implements ReservationService
{
	ReservationRepository reservationsRepository;

	@Autowired
	public ReservationServiceImpl(ReservationRepository reservationsRepository)
	{
		this.reservationsRepository = reservationsRepository;
	}

	@Override
	public ResponseEntity<Reservation> create(Reservation entity)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(reservationsRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Reservation>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Page<Reservation>>(reservationsRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Reservation> readById(Long id)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(
		    reservationsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Reservation> updateById(Long id, Reservation entity)
	{
		// TODO Auto-generated method stub
		Reservation reservation = reservationsRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		return new ResponseEntity<>(reservationsRepository.save(reservation), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		// TODO Auto-generated method stub
		if (reservationsRepository.getOne(id) != null)
		{
			reservationsRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}