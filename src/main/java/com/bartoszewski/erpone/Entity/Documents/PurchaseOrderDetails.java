package com.bartoszewski.erpone.Entity.Documents;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.bartoszewski.erpone.Entity.Contractor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties(value = { "document" }, allowSetters = true)
public class PurchaseOrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@Column(name = "Target_Date_Time", nullable = false)
	private LocalDateTime targetDateTime;
	@ManyToOne
	@JoinColumn(name = "Supplier_Id")
	private Contractor supplier;
	@OneToOne
	@JoinColumn(name = "Document_Id")
	@JsonProperty("document")
	private Documents document;

	public PurchaseOrderDetails() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getTargetDateTime() {
		return targetDateTime;
	}

	public void setTargetDateTime(LocalDateTime targeDateTime) {
		this.targetDateTime = targeDateTime;
	}

	public Contractor getSupplier() {
		return supplier;
	}

	public void setSupplier(Contractor supplier) {
		this.supplier = supplier;
	}

	public Documents getDocument() {
		return document;
	}

	public void setDocument(Documents document) {
		this.document = document;
	}

}