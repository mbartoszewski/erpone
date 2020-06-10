package com.bartoszewski.erpone.Repository;

import com.bartoszewski.erpone.Entity.Complaint;

import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends BaseRepository<Complaint, Long>
{

}