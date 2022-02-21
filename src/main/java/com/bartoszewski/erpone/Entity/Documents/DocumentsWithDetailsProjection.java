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

	DocumentStatusEnum getDocumentStatusEnum();

	String getDescription();

	String getDocNumber();

	LocalDateTime getTargetDateTime();

	LocalDateTime getCreatedAt();

	List<RelatedDocuments> getRelatedDocuments();

	Currency getDocumentCurrency();

	PaymentForm getPaymentForm();

	interface PaymentForm {
		Long getId();

		String getForm();
	}

	PaymentTerm getPaymentTerm();

	interface PaymentTerm {
		Long getId();

		int getTerm();
	}

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
		Long getId();

		Double getQuantity();

		Thing getThing();

		Double getDetailPrice();

		interface Thing {
			Long getId();

			String getCode();

			String getName();

			Unit getUnit();

			int getActive();

			interface Unit {
				String getCode();
			}
		}

	}

}
