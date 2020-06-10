package com.bartoszewski.erpone.Repository;

import com.bartoszewski.erpone.Entity.Thing;

import org.springframework.stereotype.Repository;

@Repository
public interface ThingRepository extends BaseRepository<Thing, Long>
{
}