package com.bartoszewski.erpone.Controllers.DocumentsControllers;

import com.bartoszewski.erpone.Controllers.BaseController;
import com.bartoszewski.erpone.Models.Documents.ProductionDocuments.ZpDocument;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.ZpDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents/production/zp")
public class ZpDocumentController extends BaseController<ZpDocument, Long>
{
	ZpDocumentService zpDocumentService;

	@Autowired
	public ZpDocumentController(BaseService<ZpDocument, Long> service, ZpDocumentService zpDocumentService)
	{
		super(service);
		// TODO Auto-generated constructor stub
		this.zpDocumentService = zpDocumentService;
	}
}