package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.PaymentTerm;
import com.bartoszewski.erpone.Repository.PaymentTermRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PaymentTermServiceImpl implements PaymentTermService {

	PaymentTermRepository paymentTermRepository;

	@Autowired
	public PaymentTermServiceImpl(PaymentTermRepository paymentTermRepository) {
		this.paymentTermRepository = paymentTermRepository;
	}

	@Override
	public ResponseEntity<?> create(PaymentTerm entity, Authentication authentication) {
		return new ResponseEntity<>(paymentTermRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<PaymentTerm>> readAll(Pageable pageable) {
		return new ResponseEntity<Page<PaymentTerm>>(paymentTermRepository.findAll(pageable), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<PaymentTerm> readById(Long id) {
		return new ResponseEntity<>(
				paymentTermRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PaymentTerm> updateById(Long id, PaymentTerm entity) {
		PaymentTerm paymentTerm = paymentTermRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return new ResponseEntity<>(paymentTermRepository.save(paymentTerm), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		if (paymentTermRepository.getOne(id) != null) {
			paymentTermRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}
