package com.mayo.insurance.repository;

import com.mayo.insurance.dto.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository <Claim, Integer> {
}
