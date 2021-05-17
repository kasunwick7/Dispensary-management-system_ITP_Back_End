package com.example.demo.income;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeJpaRepo extends JpaRepository<Income,Long> {
	List<Income> findByUsername(String username);

}
