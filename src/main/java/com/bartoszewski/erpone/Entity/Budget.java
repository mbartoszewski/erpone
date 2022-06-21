package com.bartoszewski.erpone.Entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.bartoszewski.erpone.Enum.BudgetTypeEnum;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = { "BudgetName", "ValidYear" })
})
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "BudgetName")
	private String budgetName;

	@CreationTimestamp
	@Column(name = "CreatedAt", columnDefinition = "TIMESTAMP")
	private LocalDateTime createdAt;
	@UpdateTimestamp
	@Column(name = "UpdatedAt", columnDefinition = "TIMESTAMP")
	private LocalDateTime updatedAt;

	@Enumerated(EnumType.STRING)
	private BudgetTypeEnum budgetTypeEnum;

	@Column(name = "ValidYear")
	private int validYear;

	@Column(name = "Active")
	private int active;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "UnitId")
	private Unit unit;

	@OneToMany(mappedBy = "budget", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BudgetValue> budgetValues;

	public List<BudgetValue> getBudgetValues() {
		return budgetValues;
	}

	public Long getId() {
		return id;
	}

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	public BudgetTypeEnum getBudgetTypeEnum() {
		return budgetTypeEnum;
	}

	public void setBudgetTypeEnum(BudgetTypeEnum budgetTypeEnum) {
		this.budgetTypeEnum = budgetTypeEnum;
	}

	public int getValidYear() {
		return validYear;
	}

	public void setValidYear(int validYear) {
		this.validYear = validYear;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void addBudgeValue(List<BudgetValue> bValue) {
		this.budgetValues = bValue;
		budgetValues.stream().forEach(bV -> bV.setBudget(this));
	}

	public void remveBudgetValue(BudgetValue bValue) {
		this.budgetValues.stream().forEach(bV -> bV.setBudget(null));
		this.budgetValues.clear();
	}
}
