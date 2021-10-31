package com.essaadani.operationservice.dtos;

import com.essaadani.operationservice.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationResponseDTO {
    private Date operationDate;
    private BigDecimal amount;
    private OperationType operationType;
}
