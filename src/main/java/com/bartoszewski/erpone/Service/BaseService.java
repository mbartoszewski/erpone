package com.bartoszewski.erpone.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface BaseService<T, ID>
{
	public ResponseEntity<?> create(T entity, Authentication authentication);

	public ResponseEntity<Page<T>> readAll(Pageable pageable);

	public ResponseEntity<T> readById(Long id);

	public ResponseEntity<T> updateById(Long id, T entity);

	public ResponseEntity<?> deleteById(Long id);
}