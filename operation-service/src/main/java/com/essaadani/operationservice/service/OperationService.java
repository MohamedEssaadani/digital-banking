package com.essaadani.operationservice.service;

import com.essaadani.operationservice.dtos.OperationRequestDTO;
import com.essaadani.operationservice.dtos.OperationResponseDTO;

import java.util.List;

public interface OperationService {
    List<OperationResponseDTO> operationsList();
    OperationResponseDTO getOperationById(Long id);
    List<OperationResponseDTO> getOperationsByAccountId(Long accountId);
    OperationResponseDTO saveOperation(OperationRequestDTO operationRequestDTO);
    void deleteOperationById(Long id);
    OperationResponseDTO updateOperation(Long id, OperationRequestDTO operationRequestDTO );

}
