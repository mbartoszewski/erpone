package com.bartoszewski.erpone.Entity.Documents;

public interface DocumentValueProjection {
	Long getId();

	Double getDocValue();

	Contractor getContractor();

	interface Contractor {
		Long getId();

		String getName();
	}
}
