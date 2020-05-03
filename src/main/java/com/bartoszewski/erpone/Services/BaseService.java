package com.bartoszewski.erpone.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BaseService<T, ID>
{
	public ResponseEntity<?> create(T entity);

	public ResponseEntity<Page<T>> readAll(Pageable pageable);

	public ResponseEntity<T> readById(Long id);

	public ResponseEntity<T> updateById(Long id, T entity);

	public ResponseEntity<?> deleteById(Long id);
}