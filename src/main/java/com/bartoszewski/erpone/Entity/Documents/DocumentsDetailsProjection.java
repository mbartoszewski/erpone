package com.bartoszewski.erpone.Entity.Documents;

import com.bartoszewski.erpone.Enum.DocumentTypeEnum;
import com.bartoszewski.erpone.Enum.DocumentStatusEnum;

public interface DocumentsDetailsProjection {
	Double getQuantity();

	DocumentDetailsThing getThing();

	Document getDocument();

	interface Document {
		Long getId();

		DocumentStatusEnum getDocumentStatusEnum();

		DocumentTypeEnum getDocumentTypeEnum();

		String getDocNumber();

		Contractor getContractor();

		interface Contractor {
			String getName();
		}
	}

	interface DocumentDetailsThing {
		String getCode();

		Unit getUnit();

		interface Unit {
			String getCode();
		}
	}
}
