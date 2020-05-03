package com.bartoszewski.erpone.Interfaces;

import com.bartoszewski.erpone.Models.Reservation;

import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends BaseRepository<Reservation, Long>
{

}