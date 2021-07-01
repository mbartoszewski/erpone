package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.PaymentForm;
import com.bartoszewski.erpone.Repository.PaymentFormRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PaymentFormServiceImpl implements PaymentFormService {

	PaymentFormRepository paymentFormRepository;

	@Autowired
	public PaymentFormServiceImpl(PaymentFormRepository paymentFormRepository) {
		this.paymentFormRepository = paymentFormRepository;
	}

	@Override
	public ResponseEntity<?> create(PaymentForm entity, Authentication authentication) {
		return new ResponseEntity<>(paymentFormRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<PaymentForm>> readAll(Pageable pageable) {
		return new ResponseEntity<Page<PaymentForm>>(paymentFormRepository.findAll(pageable), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<PaymentForm> readById(Long id) {
		return new ResponseEntity<>(
				paymentFormRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PaymentForm> updateById(Long id, PaymentForm entity) {
		PaymentForm paymentForm = paymentFormRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return new ResponseEntity<>(paymentFormRepository.save(paymentForm), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		if (paymentFormRepository.getOne(id) != null) {
			paymentFormRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}
