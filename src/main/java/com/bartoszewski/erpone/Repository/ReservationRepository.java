package com.bartoszewski.erpone.Repository;

import com.bartoszewski.erpone.Entity.Reservation;

import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends BaseRepository<Reservation, Long>
{

}