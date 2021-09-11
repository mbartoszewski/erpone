package com.bartoszewski.erpone.Entity.Documents;

import java.time.LocalDateTime;

import com.bartoszewski.erpone.Enum.DocumentTypeEnum;
import com.bartoszewski.erpone.Enum.DocumentStatusEnum;

public interface PriceWithDocumentTypeProjection {
	Long getId();

	LocalDateTime getDate();

	Currency getCurrency();

	DocumentDetails getDocumentsDetails();

	interface Currency {
		Long getId();

		String getCode();
	}

	interface DocumentDetails {
		Long getId();

		Double getDetailPrice();

		Documents getDocument();

		interface Documents {
			Long getId();

			DocumentStatusEnum getDocumentStatusEnum();

			DocumentTypeEnum getDocumentTypeEnum();
		}
	}
}
