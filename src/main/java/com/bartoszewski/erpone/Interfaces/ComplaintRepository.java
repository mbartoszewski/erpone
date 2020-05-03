package com.bartoszewski.erpone.Interfaces;

import com.bartoszewski.erpone.Models.Complaint;

import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends BaseRepository<Complaint, Long>
{

}