package com.essaadani.operationservice.service;

import com.essaadani.operationservice.dtos.OperationRequestDTO;
import com.essaadani.operationservice.dtos.OperationResponseDTO;

import java.util.List;

public interface OperationService {
    List<OperationResponseDTO> operationsList(String access_token);
    OperationResponseDTO getOperationById(Long id, String access_token);
    List<OperationResponseDTO> getOperationsByAccountId(Long accountId, String access_token);
    OperationResponseDTO saveOperation(OperationRequestDTO operationRequestDTO);
    void deleteOperationById(Long id);
    OperationResponseDTO updateOperation(Long id, OperationRequestDTO operationRequestDTO );

}
