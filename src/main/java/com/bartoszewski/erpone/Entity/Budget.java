package com.bartoszewski.erpone.Entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.bartoszewski.erpone.Enum.BudgetTypeEnum;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String budgetName;
	@CreationTimestamp
	@Column(name = "Created_At", columnDefinition = "TIMESTAMP")
	private LocalDateTime createdAt;
	@UpdateTimestamp
	@Column(name = "Updated_At", columnDefinition = "TIMESTAMP")
	private LocalDateTime updatedAt;

	@Enumerated(EnumType.STRING)
	private BudgetTypeEnum budgetTypeEnum;

	@Column(name = "Valid_From_Date", columnDefinition = "TIMESTAMP")
	private LocalDateTime validFromDate;

	@Column(name = "Valid_To_Date", columnDefinition = "TIMESTAMP")
	private LocalDateTime validToDate;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Price price;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Unit unit;

	public Long getId() {
		return id;
	}

	public BudgetTypeEnum getBudgetTypeEnum() {
		return budgetTypeEnum;
	}

	public void setBudgetTypeEnum(BudgetTypeEnum budgetTypeEnum) {
		this.budgetTypeEnum = budgetTypeEnum;
	}

	public LocalDateTime getValidFromDate() {
		return validFromDate;
	}

	public void setValidFromDate(LocalDateTime validFromDate) {
		this.validFromDate = validFromDate;
	}

	public LocalDateTime getValidToDate() {
		return validToDate;
	}

	public void setValidToDate(LocalDateTime validToDate) {
		this.validToDate = validToDate;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

}
