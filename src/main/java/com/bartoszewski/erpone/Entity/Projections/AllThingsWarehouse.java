package com.bartoszewski.erpone.Entity.Projections;

public interface AllThingsWarehouse {
	Long getId();

	String getCode();

	String getName();

	Double getQuantity();

	int getActive();

	Unit getUnit();

	interface Unit {
		Long getId();

		String getCode();
	}
}
