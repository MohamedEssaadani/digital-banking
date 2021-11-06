package com.essaadani.operationservice.web;

import com.essaadani.operationservice.dtos.OperationRequestDTO;
import com.essaadani.operationservice.dtos.OperationResponseDTO;
import com.essaadani.operationservice.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
public class OperationRestAPI {
    private final OperationService operationService;

    @GetMapping("/operations")
    public List<OperationResponseDTO> getOperationsList(){
        return operationService.operationsList();
    }

    @GetMapping("/operations/byAccount/{accountId}")
    public List<OperationResponseDTO> getOperationsByAccount(@PathVariable Long accountId){
        return operationService.getOperationsByAccountId(accountId);
    }

    @GetMapping("/operations/{id}")
    public OperationResponseDTO getOperationById(@PathVariable Long id){
        return operationService.getOperationById(id);
    }

    @PostMapping("/operations")
    public OperationResponseDTO saveOperation(@RequestBody OperationRequestDTO operationRequestDTO){
        return operationService.saveOperation(operationRequestDTO);
    }

    @PutMapping("/operations/{id}")
    public OperationResponseDTO updateOperation(@PathVariable Long id,
                                              @RequestBody OperationRequestDTO operationRequestDTO)
    {
        return operationService.updateOperation(id, operationRequestDTO);
    }

    @DeleteMapping("/operations/{id}")
    public void deleteOperation(@PathVariable Long id){
        operationService.deleteOperationById(id);
    }
}
