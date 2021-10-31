package com.essaadani.operationservice.dtos;

import com.essaadani.operationservice.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @AllArgsConstructor @NoArgsConstructor
public class OperationRequestDTO {
    private BigDecimal amount;
    private OperationType operationType;
}
