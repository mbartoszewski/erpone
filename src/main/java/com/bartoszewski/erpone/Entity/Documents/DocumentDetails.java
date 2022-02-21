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
import javax.persistence.OneToOne;
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

	@Column(name = "Quantity", nullable = false)
	private Double quantity;

	@Column(name = "Balance")
	private Double balance;

	@Column(name = "Price")
	private Double detailPrice;

	@ManyToOne

	@JoinColumn(name = "Thing_Id")
	private Thing thing;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getDetailPrice() {
		return detailPrice;
	}

	public void setDetailPrice(Double detailPrice) {
		this.detailPrice = detailPrice;
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