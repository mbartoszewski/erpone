package com.bartoszewski.erpone.Entity.Documents;

import java.time.LocalDateTime;

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

import com.bartoszewski.erpone.Entity.Contractor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "document" }, allowSetters = true)
public class OrderDocumentDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@NotNull
	@Column(name = "Target_Date_Time")
	private LocalDateTime targetDateTime;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Contractor_Id")
	@NotNull
	private Contractor contractor;
	@OneToOne
	@JoinColumn(name = "Document_Id")
	@NotNull
	private Documents document;

	public OrderDocumentDetails() {
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

	public Documents getDocument() {
		return document;
	}

	public void setDocument(Documents document) {
		this.document = document;
	}

	public Contractor getContractor() {
		return contractor;
	}

	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
	}

}