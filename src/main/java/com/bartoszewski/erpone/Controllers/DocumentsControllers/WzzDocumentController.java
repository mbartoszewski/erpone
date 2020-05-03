package com.bartoszewski.erpone.Controllers.DocumentsControllers;

import com.bartoszewski.erpone.Controllers.BaseController;
import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.WzzDocument;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.WzzDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents/warehouse/wzz")
public class WzzDocumentController extends BaseController<WzzDocument, Long>
{
	WzzDocumentService wzzDocumentService;

	@Autowired
	public WzzDocumentController(BaseService<WzzDocument, Long> service, WzzDocumentService wzzDocumentService)
	{
		super(service);
		// TODO Auto-generated constructor stub
		this.wzzDocumentService = wzzDocumentService;
	}

}