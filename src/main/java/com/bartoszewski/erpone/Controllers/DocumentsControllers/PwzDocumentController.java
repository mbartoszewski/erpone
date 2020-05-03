package com.bartoszewski.erpone.Controllers.DocumentsControllers;

import com.bartoszewski.erpone.Controllers.BaseController;
import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.PwzDocument;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.PwzDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents/warehouse/pwz")
public class PwzDocumentController extends BaseController<PwzDocument, Long>
{
	PwzDocumentService pwzDocumentService;

	@Autowired
	public PwzDocumentController(BaseService<PwzDocument, Long> service, PwzDocumentService pwzDocumentService)
	{
		super(service);
		// TODO Auto-generated constructor stub
		this.pwzDocumentService = pwzDocumentService;
	}

}