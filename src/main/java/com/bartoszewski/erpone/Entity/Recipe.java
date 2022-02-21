package com.bartoszewski.erpone.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.bartoszewski.erpone.Entity.Documents.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "thing" }, allowSetters = true)
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;

	@Column(name = "Name", nullable = false)
	private String name;
	@Column(name = "Description")
	private String description;

	@OneToMany

	private List<DocumentDetails> documentDetails;
	@ManyToOne

	@JoinColumn(name = "Thing_Id")
	private Thing thing;

	public Recipe() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<DocumentDetails> getDocumentDetails() {
		return documentDetails;
	}

	public void setDocumentDetails(List<DocumentDetails> documentDetails) {
		this.documentDetails = documentDetails;
	}

	public Thing getThing() {
		return thing;
	}

	public void setThing(Thing thing) {
		this.thing = thing;
	}

	public void addDocumentDetails(DocumentDetails documentDetails) {
		this.documentDetails.add(documentDetails);
		documentDetails.setRecipe(this);
	}

	public void removeDocumentDetails(DocumentDetails documentDetails) {
		this.documentDetails.remove(documentDetails);
		documentDetails.setRecipe(null);
	}
}