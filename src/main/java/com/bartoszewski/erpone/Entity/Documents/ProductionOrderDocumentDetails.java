package com.bartoszewski.erpone.Entity.Documents;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.bartoszewski.erpone.Entity.Recipe;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "document" }, allowSetters = true)
public class ProductionOrderDocumentDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@NotNull
	@Column(name = "Target_Date_Time")
	private LocalDateTime targetDateTime;
	@OneToOne
	@NotNull
	@JoinColumn(name = "Recipe_Id")
	private Recipe recipe;
	@OneToOne
	@JoinColumn(name = "Document_Id")
	@NotNull
	private Documents document;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getTargetDateTime() {
		return targetDateTime;
	}

	public void setTargetDateTime(LocalDateTime targetDateTime) {
		this.targetDateTime = targetDateTime;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Documents getDocument() {
		return document;
	}

	public void setDocument(Documents document) {
		this.document = document;
	}

}