package com.bartoszewski.erpone.Entity.Documents;

import com.bartoszewski.erpone.Enum.DocumentTypeEnum;
import com.bartoszewski.erpone.Enum.DocumentStatusEnum;

public interface DocumentsDetailsPriceProjection {
	Double getQuantity();

	Double getDetailPrice();

	DocumentDetailsThing getThing();

	Document getDocument();

	Double getBalance();

	interface Document {
		Long getId();

		DocumentStatusEnum getDocumentStatusEnum();

		DocumentTypeEnum getDocumentTypeEnum();
	}

	interface DocumentDetailsThing {
		String getCode();

		Unit getUnit();

		interface Unit {
			String getCode();
		}
	}
}
