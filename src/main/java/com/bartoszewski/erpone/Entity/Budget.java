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

@Entity
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private BudgetTypeEnum budgetTypeEnum;

	@Column(name = "Valid_From_Date", columnDefinition = "TIMESTAMP")
	private LocalDateTime validFromDate;

	@Column(name = "Valid_To_Date", columnDefinition = "TIMESTAMP")
	private LocalDateTime validToDate;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Price price;

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
