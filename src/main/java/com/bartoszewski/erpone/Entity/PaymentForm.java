package com.bartoszewski.erpone.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.bartoszewski.erpone.Entity.Documents.Documents;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "documents" }, allowSetters = true)
public class PaymentForm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@Column(name = "Form", nullable = false)
	private String form;
	@OneToMany(mappedBy = "paymentForm", fetch = FetchType.LAZY)
	private List<Documents> documents;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public List<Documents> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Documents> documents) {
		this.documents = documents;
	}

	public void addDocuments(Documents document) {
		documents.add(document);
		document.setPaymentForm(this);
	}

	public void removeDocuments(Documents document) {
		documents.remove(document);
		document.setPaymentForm(null);
	}
}
