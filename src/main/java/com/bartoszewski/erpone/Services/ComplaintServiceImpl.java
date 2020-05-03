package com.bartoszewski.erpone.Services;

import com.bartoszewski.erpone.Interfaces.ComplaintRepository;
import com.bartoszewski.erpone.Models.Complaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ComplaintServiceImpl implements ComplaintService
{
	ComplaintRepository complaintsRepository;

	@Autowired
	public ComplaintServiceImpl(ComplaintRepository complaintsRepository)
	{
		this.complaintsRepository = complaintsRepository;
	}

	@Override
	public ResponseEntity<Complaint> create(Complaint entity)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(complaintsRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Complaint>> readAll(Pageable pageable)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<Page<Complaint>>(complaintsRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Complaint> readById(Long id)
	{
		// TODO Auto-generated method stub
		return new ResponseEntity<>(
		    complaintsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Complaint> updateById(Long id, Complaint entity)
	{
		// TODO Auto-generated method stub
		Complaint complaint = complaintsRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		return new ResponseEntity<>(complaintsRepository.save(complaint), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		if (complaintsRepository.getOne(id) != null)
		{
			complaintsRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}