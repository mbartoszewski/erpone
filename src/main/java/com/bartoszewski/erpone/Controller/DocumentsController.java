package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.Documents.Documents;
import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.DocumentsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents")
public class DocumentsController extends BaseController<Documents, Long> {
	DocumentsService documentsService;

	@Autowired
	public DocumentsController(BaseService<Documents, Long> service, DocumentsService documentsService) {
		super(service);
		this.documentsService = documentsService;
	}
}