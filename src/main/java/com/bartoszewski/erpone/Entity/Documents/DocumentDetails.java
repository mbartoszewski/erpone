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

import com.bartoszewski.erpone.Entity.Price;
import com.bartoszewski.erpone.Entity.Thing;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.lang.NonNull;

@Entity
@JsonIgnoreProperties(value = { "document", "price"}, allowSetters = true)
public class DocumentDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@Column(name = "Quantity", nullable = false)
	private Double quantity;

	@ManyToOne
	@NonNull
	@JoinColumn(name = "Thing_Id")
	@JsonProperty("thing")
	private Thing thing;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Price_Id")
	@JsonProperty("price")
	private Price price;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty("document")
	@JoinColumn(name = "Document_Id")
	private Documents document;

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

	
}