package com.bartoszewski.erpone.Repository;

import com.bartoszewski.erpone.Entity.Budget;
import com.bartoszewski.erpone.Enum.BudgetTypeEnum;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends BaseRepository<Budget, Long> {
	@Query("SELECT b FROM Budget b WHERE (COALESCE(:type) IS NULL OR b.budgetTypeEnum IN :type) AND (COALESCE(:year) IS NULL OR b.validYear IN :year) AND (:active is NULL OR active = :active)")
	Page<Budget> getBudgetsList(Pageable pageable, @Param("type") List<BudgetTypeEnum> type,
			@Param("active") Integer active, @Param("year") List<Integer> year);
}
