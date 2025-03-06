package com.example.analyticsservice.service;

import lombok.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

import com.example.analyticsservice.entity.*;
import com.example.analyticsservice.model.*;
import com.example.analyticsservice.repository.*;

@Service
@RequiredArgsConstructor
public class SelectedProductService {

    private final SelectedProductRepository selectedProductRepository;

    private final ModelMapper modelMapper;

    public void addSelectedProduct(SelectedProductModel product) {
        var entity = modelMapper.map(product, SelectedProductEntity.class);
        selectedProductRepository.save(entity);
    }

}
