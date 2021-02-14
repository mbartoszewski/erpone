package com.bartoszewski.erpone.Entity.Documents;

import java.time.LocalDateTime;

import com.bartoszewski.erpone.Enum.DocumentTypeEnum;
import com.bartoszewski.erpone.Enum.StatusTypeEnum;

public interface DocumentsProjection {

	Contractor getContractor();

	Long getId();

	DocumentTypeEnum getDocumentTypeEnum();

	StatusTypeEnum getStatusTypeEnum();

	String getDescription();

	String getDocNumber();

	LocalDateTime getTargetDateTime();

	LocalDateTime getCreatedAt();

	interface Contractor {
		String getName();
	}

}
