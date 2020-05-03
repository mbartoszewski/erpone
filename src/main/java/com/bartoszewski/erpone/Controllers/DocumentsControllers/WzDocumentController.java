package com.bartoszewski.erpone.Controllers.DocumentsControllers;

import com.bartoszewski.erpone.Controllers.BaseController;
import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.WzDocument;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.WzDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents/warehouse/wz")
public class WzDocumentController extends BaseController<WzDocument, Long>
{
	WzDocumentService wzDocumentService;

	@Autowired
	public WzDocumentController(BaseService<WzDocument, Long> service, WzDocumentService wzDocumentService)
	{
		super(service);
		// TODO Auto-generated constructor stub
		this.wzDocumentService = wzDocumentService;
	}

}