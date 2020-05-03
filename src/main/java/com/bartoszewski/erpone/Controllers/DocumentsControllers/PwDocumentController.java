package com.bartoszewski.erpone.Controllers.DocumentsControllers;

import com.bartoszewski.erpone.Controllers.BaseController;
import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.PwDocument;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.PwDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents/warehouse/pw")
public class PwDocumentController extends BaseController<PwDocument, Long>
{
	PwDocumentService pwDocumentService;

	@Autowired
	public PwDocumentController(BaseService<PwDocument, Long> service, PwDocumentService pwDocumentService)
	{
		super(service);
		// TODO Auto-generated constructor stub
		this.pwDocumentService = pwDocumentService;
	}

}