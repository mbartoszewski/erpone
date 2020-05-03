package com.bartoszewski.erpone.Models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.bartoszewski.erpone.Models.Documents.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@JsonIgnoreProperties(value =
{
        "documentsDetails", "thing", "documentsDetails"
}, allowSetters = true)
public class Price
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@Column(name = "Date")
	@CreationTimestamp
	private LocalDateTime date;
	@Column(name = "Price", nullable = false)
	private Double price;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Thing_Id")
	@JsonProperty("thing")
	private Thing thing;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Currency_Id", nullable = false)
	@NotNull
	private Currency currency;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "price")
	@JsonProperty("documentsDetails")
	private List<DocumentDetails> documentsDetails;

	public LocalDateTime getDate()
	{
		return date;
	}

	public void setDate(LocalDateTime date)
	{
		this.date = date;
	}

	public Double getPrice()
	{
		return price;
	}

	public void setPrice(Double price)
	{
		this.price = price;
	}

	public Currency getCurrency()
	{
		return currency;
	}

	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	public Thing getThing()
	{
		return thing;
	}

	public void setThing(Thing thing)
	{
		this.thing = thing;
	}

	public List<DocumentDetails> getDocumentsDetails()
	{
		return documentsDetails;
	}

	public void setDocumentsDetails(List<DocumentDetails> documentsDetails)
	{
		this.documentsDetails = documentsDetails;
	}

	public void addDocumentsDetails(DocumentDetails documentsDetails)
	{
		this.documentsDetails.add(documentsDetails);
		documentsDetails.setPrice(this);
	}

	public void removeDocumentsDetails(DocumentDetails documentsDetails)
	{
		this.documentsDetails.remove(documentsDetails);
		documentsDetails.setPrice(null);
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
}