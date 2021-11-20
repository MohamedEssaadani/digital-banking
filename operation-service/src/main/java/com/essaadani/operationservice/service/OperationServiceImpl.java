package com.essaadani.operationservice.service;

import com.essaadani.operationservice.dtos.OperationRequestDTO;
import com.essaadani.operationservice.dtos.OperationResponseDTO;
import com.essaadani.operationservice.entities.Operation;
import com.essaadani.operationservice.enums.OperationType;
import com.essaadani.operationservice.mappers.OperationMapper;
import com.essaadani.operationservice.openfeign.AccountRestClient;
import com.essaadani.operationservice.repositories.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

        // check operation type
        if(operationRequestDTO.getOperationType() == OperationType.CREDIT){
            // credit + ==> update account set balance += amount (openFeign)
            BigDecimal amount = operation.getAmount();
            BigDecimal balance = operation.getAccount().getBalance();
            balance = balance.add(amount);

            // update account balance
            operation.getAccount().setBalance(balance);

            // update account micro service
            accountRestClient.updateAccount(operation.getAccountId(), operation.getAccount());
        }else if(operationRequestDTO.getOperationType() == OperationType.DEBIT){
            // debit - ==> update account set balance -= amount (openFeign)
            BigDecimal amount = operation.getAmount();
            BigDecimal balance = operation.getAccount().getBalance();
            balance = balance.subtract(amount);

            // update account balance
            operation.getAccount().setBalance(balance);

            // update account micro service
            accountRestClient.updateAccount(operation.getAccountId(), operation.getAccount());

        }else{
            return null;
        }

        Operation savedOperation = operationRepository.save(operation);

        return operationMapper.toOperationDTO(savedOperation);
    }

    // cancel operation
    @Override
    public void deleteOperationById(Long id) {
        // get operation
        Operation operation = operationRepository.findById(id).get();

        // cancel operation account
        if(operation.getOperationType() == OperationType.CREDIT){
            // credit + ==> update account set balance -= amount (openFeign)
            BigDecimal amount = operation.getAmount();
            BigDecimal balance = operation.getAccount().getBalance();
            balance = balance.subtract(amount);

            // update account balance
            operation.getAccount().setBalance(balance);

            // update account micro service
            accountRestClient.updateAccount(operation.getAccountId(), operation.getAccount());
        }else if(operation.getOperationType() == OperationType.DEBIT) {
            // debit - ==> update account set balance += amount (openFeign)
            BigDecimal amount = operation.getAmount();
            BigDecimal balance = operation.getAccount().getBalance();
            balance = balance.add(amount);

            // update account balance
            operation.getAccount().setBalance(balance);

            // update account micro service
            accountRestClient.updateAccount(operation.getAccountId(), operation.getAccount());
        }
        // delete operation
        operationRepository.deleteById(id);
    }

    @Override
    public OperationResponseDTO updateOperation(Long id, OperationRequestDTO operationRequestDTO) {
        Operation operation = operationMapper.toOperation(operationRequestDTO);
        operation.setId(id);
        Operation updatedOperation = operationRepository.save(operation);
        return operationMapper.toOperationDTO(updatedOperation);    }
}
