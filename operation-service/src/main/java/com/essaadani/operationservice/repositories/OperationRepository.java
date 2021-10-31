package com.essaadani.operationservice.repositories;

import com.essaadani.operationservice.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
