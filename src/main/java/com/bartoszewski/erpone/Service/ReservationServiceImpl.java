package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.Reservation;
import com.bartoszewski.erpone.Repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
	public ResponseEntity<Reservation> create(Reservation entity, Authentication authentication)
	{
		return new ResponseEntity<>(reservationsRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Reservation>> readAll(Pageable pageable)
	{
		return new ResponseEntity<Page<Reservation>>(reservationsRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Reservation> readById(Long id)
	{
		return new ResponseEntity<>(
		    reservationsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Reservation> updateById(Long id, Reservation entity)
	{
		Reservation reservation = reservationsRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		return new ResponseEntity<>(reservationsRepository.save(reservation), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		if (reservationsRepository.getOne(id) != null)
		{
			reservationsRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}