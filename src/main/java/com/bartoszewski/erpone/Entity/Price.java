package com.bartoszewski.erpone.Entity;

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
import javax.validation.constraints.NotNull;

import com.bartoszewski.erpone.Entity.Documents.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@JsonIgnoreProperties(value = { "documentsDetails", "thing" }, allowSetters = true)
public class Price {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@Column(name = "Date")
	@CreationTimestamp
	@NotNull
	private LocalDateTime date;
	@NotNull
	@Column(name = "Price")
	private Double price;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Thing_Id")
	private Thing thing;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Currency_Id")
	@NotNull
	private Currency currency;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "price")
	private List<DocumentDetails> documentsDetails;

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Thing getThing() {
		return thing;
	}

	public void setThing(Thing thing) {
		this.thing = thing;
	}

	public List<DocumentDetails> getDocumentsDetails() {
		return documentsDetails;
	}

	public void setDocumentsDetails(List<DocumentDetails> documentsDetails) {
		this.documentsDetails = documentsDetails;
	}

	public void addDocumentsDetails(DocumentDetails documentsDetails) {
		this.documentsDetails.add(documentsDetails);
		documentsDetails.setPrice(this);
	}

	public void removeDocumentsDetails(DocumentDetails documentsDetails) {
		this.documentsDetails.remove(documentsDetails);
		documentsDetails.setPrice(null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Price [currency=" + currency + ", date=" + date + ", price=" + price + "]";
	}

}