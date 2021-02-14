package com.bartoszewski.erpone.Entity.Documents;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.bartoszewski.erpone.Entity.Price;
import com.bartoszewski.erpone.Entity.Recipe;
import com.bartoszewski.erpone.Entity.Thing;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "document" }, allowSetters = true)
public class DocumentDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@NotNull
	@Column(name = "Quantity")
	private Double quantity;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "Thing_Id")
	private Thing thing;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "Price_Id")
	private Price price;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Document_Id")
	private Documents document;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Recipe_Id")
	private Recipe recipe;

	public DocumentDetails() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Thing getThing() {
		return thing;
	}

	public void setThing(Thing thing) {
		this.thing = thing;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Documents getDocument() {
		return document;
	}

	public void setDocument(Documents document) {
		this.document = document;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

}