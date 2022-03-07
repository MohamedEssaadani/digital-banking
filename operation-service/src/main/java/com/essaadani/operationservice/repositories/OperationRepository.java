package com.essaadani.operationservice.repositories;

import com.essaadani.operationservice.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByAccountId(Long accountId);

}
