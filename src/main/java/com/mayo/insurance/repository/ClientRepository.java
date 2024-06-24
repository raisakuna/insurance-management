package com.mayo.insurance.repository;

import com.mayo.insurance.dto.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
