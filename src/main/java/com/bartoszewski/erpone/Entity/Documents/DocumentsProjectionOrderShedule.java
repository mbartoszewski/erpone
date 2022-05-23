package com.bartoszewski.erpone.Entity.Documents;

import java.time.LocalDateTime;
import java.util.List;

import com.bartoszewski.erpone.Enum.DocumentTypeEnum;
import com.bartoszewski.erpone.Enum.DocumentStatusEnum;

public interface DocumentsProjectionOrderShedule {

	Contractor getContractor();

	Long getId();

	List<DocumentDetails> getDocumentDetails();

	DocumentTypeEnum getDocumentTypeEnum();

	DocumentStatusEnum getDocumentStatusEnum();

	String getDescription();

	String getDocNumber();

	LocalDateTime getTargetDateTime();

	interface Contractor {
		String getName();
	}

	interface DocumentDetails {
		Long getId();

		Double getQuantity();

		Thing getThing();

		interface Thing {
			Long getId();

			String getCode();

			Unit getUnit();

			interface Unit {
				String getCode();
			}
		}

	}
}
