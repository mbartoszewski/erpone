package com.bartoszewski.erpone.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bartoszewski.erpone.Entity.Thing;
import com.bartoszewski.erpone.Entity.Documents.DocumentDetails;
import com.bartoszewski.erpone.Entity.Documents.Documents;
import com.bartoszewski.erpone.Repository.DocumentsRepository;
import com.bartoszewski.erpone.Repository.ThingRepository;
import com.bartoszewski.erpone.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class DocumentsServiceImpl implements DocumentsService {
	DocumentsRepository documentsRepository;
	ThingRepository thingsRepository;
	UserRepository userRepository;

	@Autowired
	public DocumentsServiceImpl(DocumentsRepository documentsRepository, UserRepository userRepository, ThingRepository thingsRepository) {
		this.documentsRepository = documentsRepository;
		this.userRepository = userRepository;
		this.thingsRepository = thingsRepository;
	}

	@Override
	public ResponseEntity<?> create(Documents entity, Authentication authentication) {
		switch(entity.getDocumentTypeEnum())
		{
			case Po:
				return null;
			case Pw:
			case Pz:
				return makeIncomeOperation(entity, authentication);
			case Rw:
			case Wz:
			case Wzz:
				return makeOutgoingOperation(entity, authentication);
			case Zp:
				return null;
			default:
				return null;	
		}
	}

	@Override
	public ResponseEntity<Page<Documents>> readAll(Pageable pageable) {
		return null;
	}

	@Override
	public ResponseEntity<Documents> readById(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<Documents> updateById(Long id, Documents entity) {
		return null;
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		return null;
	}

	private ResponseEntity<?> makeOutgoingOperation(Documents entity, Authentication authentication) {
		List<DocumentDetails> detailsWithoutStock = new ArrayList<>();
		for (Iterator<DocumentDetails> documentsDetails = entity.getDocumentsDetails().iterator(); documentsDetails
				.hasNext();) {
			DocumentDetails documentDetail = documentsDetails.next();
			documentDetail.setThing(thingsRepository.getOne(documentDetail.getThing().getId()));
			if (documentDetail.getThing().getQuantity() >= documentDetail.getQuantity()) {
				documentDetail.getThing()
						.setQuantity(documentDetail.getThing().getQuantity() - documentDetail.getQuantity());
				documentDetail.getThing().addDocumentsDetails(documentDetail);
			} else {
				documentsDetails.remove();
				detailsWithoutStock.add(documentDetail);
			}
		}
		entity.setUser(userRepository.findByEmail(authentication.getName()));
		return detailsWithoutStock.size() > 0 ? new ResponseEntity<>(detailsWithoutStock, HttpStatus.BAD_REQUEST)
				: new ResponseEntity<>(documentsRepository.save(entity), HttpStatus.OK);
	}
	
	private ResponseEntity<?> makeIncomeOperation(Documents entity, Authentication authentication) {
		for (Iterator<DocumentDetails> documentsDetails = entity.getDocumentsDetails().iterator(); documentsDetails
				.hasNext();) {
			DocumentDetails documentDetail = documentsDetails.next();
			Thing thing = thingsRepository.getOne(documentDetail.getThing().getId());
			thing.setQuantity(thing.getQuantity() + documentDetail.getQuantity());
			documentDetail.getThing().addDocumentsDetails(documentDetail);
		}
		entity.setUser(userRepository.findByEmail(authentication.getName()));
		return new ResponseEntity<>(documentsRepository.save(entity), HttpStatus.CREATED);
	}
}