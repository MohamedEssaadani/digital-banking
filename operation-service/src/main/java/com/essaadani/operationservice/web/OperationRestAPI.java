package com.essaadani.operationservice.web;

import com.essaadani.operationservice.dtos.OperationRequestDTO;
import com.essaadani.operationservice.dtos.OperationResponseDTO;
import com.essaadani.operationservice.enums.OperationType;
import com.essaadani.operationservice.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.HttpHead;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin("*")
@RequiredArgsConstructor
public class OperationRestAPI {
    private final OperationService operationService;

    @GetMapping("/operations")
    public List<OperationResponseDTO> getOperationsList(@RequestHeader HttpHeaders headers){
        return operationService.operationsList(headers.get("Authorization").get(0));
    }

    @GetMapping("/operations/byAccount/{accountId}")
    public List<OperationResponseDTO> getOperationsByAccount(@PathVariable Long accountId,
                                                             @RequestHeader HttpHeaders headers){
        String access_token = headers.get("Authorization").get(0);
        return operationService.getOperationsByAccountId(accountId, access_token);
    }

    @GetMapping("/operations/{id}")
    public OperationResponseDTO getOperationById(@PathVariable Long id, @RequestHeader HttpHeaders headers){
        return operationService.getOperationById(id, headers.get("Authorization").get(0));
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
