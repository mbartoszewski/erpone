package com.bartoszewski.erpone.Entity.Projections;

import java.time.LocalDateTime;

import com.bartoszewski.erpone.Enum.DocumentStatusEnum;

public interface ThingsValueByProperties {
	Long getId();

	Double getDetailPrice();

	Double getBalance();

	Document getDocument();

	Thing getThing();

	interface Group {
		Long getId();

		String getName();
	}

	interface Thing {
		Long getId();

		String getCode();

		Group getThingGroup();
	}

	interface Contractor {
		Long getId();

		String getName();
	}

	interface Document {
		Long getId();

		DocumentStatusEnum getDocumentStatusEnum();

		Contractor getContractor();

		LocalDateTime getCreatedAt();

	}
}
