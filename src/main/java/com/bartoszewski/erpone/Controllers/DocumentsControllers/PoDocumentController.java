package com.bartoszewski.erpone.Controllers.DocumentsControllers;

import com.bartoszewski.erpone.Controllers.BaseController;
import com.bartoszewski.erpone.Models.Documents.PurchaseDocuments.PoDocument;
import com.bartoszewski.erpone.Services.BaseService;
import com.bartoszewski.erpone.Services.PoDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents/purchase/po")
public class PoDocumentController extends BaseController<PoDocument, Long>
{
	PoDocumentService poDocumentService;

	@Autowired
	public PoDocumentController(BaseService<PoDocument, Long> service, PoDocumentService poDocumentService)
	{
		super(service);
		// TODO Auto-generated constructor stub
		this.poDocumentService = poDocumentService;
	}
}