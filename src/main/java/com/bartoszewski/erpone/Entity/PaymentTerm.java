package com.bartoszewski.erpone.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.bartoszewski.erpone.Entity.Documents.Documents;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "documents" }, allowSetters = true)
public class PaymentTerm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@NotNull
	@Column(name = "Term")
	private Long term;
	@OneToMany(mappedBy = "paymentTerm", fetch = FetchType.LAZY)
	private List<Documents> documents;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTerm() {
		return term;
	}

	public void setTerm(Long term) {
		this.term = term;
	}

	public List<Documents> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Documents> documents) {
		this.documents = documents;
	}

	public void addDocuments(Documents document) {
		documents.add(document);
		document.setPaymentTerm(this);
	}

	public void removeDocuments(Documents document) {
		documents.remove(document);
		document.setPaymentTerm(null);
	}
}
