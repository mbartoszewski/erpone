package com.bartoszewski.erpone.Entity.Projections;

public interface SearchThingsByProperties {
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

	Category getThingCategory();

	interface Category {
		Long getId();

		String getName();
	}

	Warehouse getWarehouse();

	interface Warehouse {
		Long getId();

		String getCode();
	}
}
