package com.bartoszewski.erpone.Entity.Documents;

import java.time.LocalDateTime;

import com.bartoszewski.erpone.Enum.DocumentTypeEnum;
import com.bartoszewski.erpone.Enum.DocumentStatusEnum;

public interface DocumentsDetailsWithBalanceProjection {
	Long getId();

	Double getQuantity();

	Double getBalance();

	DocumentDetailsThing getThing();

	Document getDocument();

	interface Document {
		Long getId();

		DocumentStatusEnum getDocumentStatusEnum();

		DocumentTypeEnum getDocumentTypeEnum();

		LocalDateTime getCreatedAt();
	}

	interface DocumentDetailsThing {
		Long getId();

		// String getCode();
	}
}