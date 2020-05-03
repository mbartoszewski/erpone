package com.bartoszewski.erpone.Controllers.DocumentsControllers;

import com.bartoszewski.erpone.Controllers.BaseController;
import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.RwDocument;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.RwDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents/warehouse/rw")
public class RwDocumentController extends BaseController<RwDocument, Long>
{
	RwDocumentService rwDocumentService;

	@Autowired
	public RwDocumentController(BaseService<RwDocument, Long> service, RwDocumentService rwDocumentService)
	{
		super(service);
		// TODO Auto-generated constructor stub
		this.rwDocumentService = rwDocumentService;
	}

}