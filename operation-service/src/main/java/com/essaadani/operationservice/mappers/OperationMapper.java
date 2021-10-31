package com.essaadani.operationservice.mappers;

import com.essaadani.operationservice.dtos.OperationRequestDTO;
import com.essaadani.operationservice.dtos.OperationResponseDTO;
import com.essaadani.operationservice.entities.Operation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperationMapper {
    OperationResponseDTO toOperationDTO(Operation operation);
    Operation toOperation(OperationRequestDTO operation);
}
