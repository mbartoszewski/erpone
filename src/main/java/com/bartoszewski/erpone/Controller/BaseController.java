package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class BaseController<T, ID>
{

	private BaseService<T, ID> service;

	@Autowired
	public BaseController(BaseService<T, ID> service)
	{
		this.service = service;
	}

	@PostMapping(value = "")
	public ResponseEntity<?> create(@RequestBody T entity, Authentication authentication)
	{
		return service.create(entity, authentication);
	}

	@GetMapping(value = "")
	public ResponseEntity<Page<T>> readAll(Pageable pageable)
	{
		return service.readAll(pageable);
	}

	@GetMapping(value = "/{id}")
	private ResponseEntity<T> readById(@PathVariable(value = "id") Long id)
	{
		return service.readById(id);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<T> updateById(@PathVariable(value = "id") Long id, @RequestBody T entity)
	{
		return service.updateById(id, entity);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id)
	{
		return service.deleteById(id);
	}
}