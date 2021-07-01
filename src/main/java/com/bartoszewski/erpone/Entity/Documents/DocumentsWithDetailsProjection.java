package com.bartoszewski.erpone.Entity.Documents;

import java.time.LocalDateTime;
import java.util.List;

import com.bartoszewski.erpone.Enum.DocumentTypeEnum;
import com.bartoszewski.erpone.Enum.DocumentStatusEnum;

public interface DocumentsWithDetailsProjection {

	Contractor getContractor();

	List<DocumentDetails> getDocumentDetails();

	Long getId();

	DocumentTypeEnum getDocumentTypeEnum();

	DocumentStatusEnum getStatusTypeEnum();

	String getDescription();

	String getDocNumber();

	LocalDateTime getTargetDateTime();

	LocalDateTime getCreatedAt();

	List<RelatedDocuments> getRelatedDocuments();

	Currency getDocumentCurrency();

	interface RelatedDocuments {
		Long getId();

		String getDocNumber();
	}

	interface Contractor {
		Long getId();

		String getName();

		String getNip();

		String getRegon();

		Address getAddress();

		interface Address {
			String getCity();

			String getStreet();

			String getCountry();

			String getNumber();

			String getPostalCode();
		}
	}

	interface Currency {
		Long getId();

		String getCode();
	}

	interface DocumentDetails {
		Double getQuantity();

		Thing getThing();

		Price getPrice();

		interface Price {
			Double getPrice();
		}

		interface Thing {
			Long getId();

			String getCode();

			String getName();

			Unit getUnit();

			interface Unit {
				String getCode();
			}
		}

	}

}
