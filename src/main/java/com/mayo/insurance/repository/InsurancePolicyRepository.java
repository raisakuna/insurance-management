package com.mayo.insurance.repository;

import com.mayo.insurance.dto.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer> {
}
