package com.practice.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.transaction.model.EmployeeHealthInsurance;

@Repository
public interface HealthInsuranceRepository extends JpaRepository<EmployeeHealthInsurance, Long> {
	void deleteEmployeeHealthInsuranceByEmpId(Long empid);
}