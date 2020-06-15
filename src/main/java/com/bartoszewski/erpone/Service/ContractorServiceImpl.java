package com.bartoszewski.erpone.Service;

import com.bartoszewski.erpone.Entity.Contractor;
import com.bartoszewski.erpone.Repository.ContractorRepository;
import com.bartoszewski.erpone.Repository.CurrencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContractorServiceImpl implements ContractorService {
	ContractorRepository contractorsRepository;
	CurrencyRepository currencyRepository;

	@Autowired
	public ContractorServiceImpl(ContractorRepository contractorsRepository, CurrencyRepository currencyRepository) {
		this.contractorsRepository = contractorsRepository;
		this.currencyRepository = currencyRepository;
	}

	@Override
	public ResponseEntity<Contractor> create(Contractor entity, Authentication authentication) {
		entity.setDefaultCurrency(currencyRepository.getOne(entity.getDefaultCurrency().getId()));
		return new ResponseEntity<>(contractorsRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Contractor>> readAll(Pageable pageable) {
		return new ResponseEntity<Page<Contractor>>(contractorsRepository.findAll(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Contractor> readById(Long id) {
		return new ResponseEntity<>(
				contractorsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Contractor> updateById(Long id, Contractor entity) {
		Contractor contractor = contractorsRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		contractor.setName(entity.getName());
		contractor.setNip(entity.getNip());
		contractor.setRegon(entity.getRegon());
		return new ResponseEntity<>(contractorsRepository.save(contractor), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		if (contractorsRepository.getOne(id) != null) {
			contractorsRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
	}
}