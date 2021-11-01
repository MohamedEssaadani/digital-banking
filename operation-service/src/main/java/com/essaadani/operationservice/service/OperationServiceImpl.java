package com.essaadani.operationservice.service;

import com.essaadani.operationservice.dtos.OperationRequestDTO;
import com.essaadani.operationservice.dtos.OperationResponseDTO;
import com.essaadani.operationservice.entities.Operation;
import com.essaadani.operationservice.mappers.OperationMapper;
import com.essaadani.operationservice.openfeign.AccountRestClient;
import com.essaadani.operationservice.repositories.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;
    private final AccountRestClient accountRestClient;

    @Override
    public List<OperationResponseDTO> operationsList() {
        List<Operation> operations = operationRepository.findAll();
        for (Operation operation : operations) {
            operation.setAccount(accountRestClient.getAccountById(operation.getAccountId()));
        }
        return operations.stream()
                .map(operationMapper::toOperationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OperationResponseDTO getOperationById(Long id) {
        Operation operation = operationRepository.getById(id);
        operation.setAccount(accountRestClient.getAccountById(operation.getAccountId()));
        return operationMapper.toOperationDTO(operation);
    }

    @Override
    public List<OperationResponseDTO> getOperationsByAccountId(Long accountId) {
        List<Operation> operations = operationRepository.findByAccountId(accountId);
        for (Operation operation : operations) {
            operation.setAccount(accountRestClient.getAccountById(operation.getAccountId()));
        }
        return operations.stream()
                .map(operationMapper::toOperationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OperationResponseDTO saveOperation(OperationRequestDTO operationRequestDTO) {
        Operation operation = operationMapper.toOperation(operationRequestDTO);
        Operation savedOperation = operationRepository.save(operation);
        return operationMapper.toOperationDTO(savedOperation);
    }

    @Override
    public void deleteOperationById(Long id) {
        operationRepository.deleteById(id);
    }

    @Override
    public OperationResponseDTO updateOperation(Long id, OperationRequestDTO operationRequestDTO) {
        Operation operation = operationMapper.toOperation(operationRequestDTO);
        operation.setId(id);
        Operation updatedOperation = operationRepository.save(operation);
        return operationMapper.toOperationDTO(updatedOperation);    }
}
