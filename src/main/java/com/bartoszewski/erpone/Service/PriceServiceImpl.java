package com.bartoszewski.erpone.Service;

import java.time.LocalDate;

import com.bartoszewski.erpone.Entity.Price;
import com.bartoszewski.erpone.Entity.Documents.PriceWithDocumentTypeProjection;
import com.bartoszewski.erpone.Repository.PriceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PriceServiceImpl implements PriceService {
	PriceRepository pricesRepository;

	@Autowired
	public PriceServiceImpl(PriceRepository pricesRepository) {
		this.pricesRepository = pricesRepository;
	}

	@Override
	public ResponseEntity<Price> create(Price entity, Authentication authentication) {
		return new ResponseEntity<>(pricesRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Price>> readAll(Pageable pageable) {
		return new ResponseEntity<Page<Price>>(pricesRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Price> readById(Long id) {
		return new ResponseEntity<>(
				pricesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Price> updateById(Long id, Price entity) {
		Price price = pricesRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		price.setPrice(entity.getPrice());
		return new ResponseEntity<>(pricesRepository.save(price), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		if (pricesRepository.getOne(id) != null) {
			pricesRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Page<Price>> getPriceByThingId(Pageable pageable, Long thing, LocalDate startDate,
			LocalDate endDate) {

		return new ResponseEntity<Page<Price>>(pricesRepository.getPriceByThingId(pageable, thing, startDate, endDate),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<PriceWithDocumentTypeProjection>> getPriceWithDocumentType(Pageable pageable, Long thing,
			LocalDate startDate, LocalDate endDate) {
		return new ResponseEntity<Page<PriceWithDocumentTypeProjection>>(
				pricesRepository.getPriceWithDocumentType(pageable, thing, startDate, endDate), HttpStatus.OK);
	}

}