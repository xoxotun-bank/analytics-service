package com.example.analyticsservice.service;

import lombok.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

import com.example.analyticsservice.entity.*;
import com.example.analyticsservice.model.*;
import com.example.analyticsservice.repository.*;
import com.example.analyticsservice.swagger.server.dto.*;

@Service
@RequiredArgsConstructor
public class FinancialProductService {

    private final FinancialProductRepository financialProductRepository;

    private final ModelMapper modelMapper;

    public FinancialProductModel addFinancialProduct(FinancialProductDto dto) {

        var entity = modelMapper.map(dto, FinancialProductEntity.class);

        financialProductRepository.save(entity);

        var result = modelMapper.map(entity, FinancialProductModel.class);
        return result;
    }

}
