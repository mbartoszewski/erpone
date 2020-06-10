package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.ContactDetail;
import com.bartoszewski.erpone.Repository.ContactDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContactDetailServiceImpl implements ContactDetailService
{
	ContactDetailRepository contactDetailsRepository;

	@Autowired
	public ContactDetailServiceImpl(ContactDetailRepository contactDetailsRepository)
	{
		this.contactDetailsRepository = contactDetailsRepository;
	}

	@Override
	public ResponseEntity<ContactDetail> create(ContactDetail entity, Authentication authentication)
	{
		return new ResponseEntity<>(contactDetailsRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<ContactDetail>> readAll(Pageable pageable)
	{
		return new ResponseEntity<Page<ContactDetail>>(contactDetailsRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ContactDetail> readById(Long id)
	{
		return new ResponseEntity<>(
		    contactDetailsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
		    HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ContactDetail> updateById(Long id, ContactDetail entity)
	{
		ContactDetail contactDetail = contactDetailsRepository.findById(id)
		    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		contactDetail.setContractor(entity.getContractor());
		contactDetail.setEmail(entity.getEmail());
		contactDetail.setPhone(entity.getPhone());
		return new ResponseEntity<>(contactDetailsRepository.save(contactDetail), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id)
	{
		if (contactDetailsRepository.getOne(id) != null)
		{
			contactDetailsRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}