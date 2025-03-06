package com.example.analyticsservice.service;

import java.io.*;
import java.time.*;

import lombok.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;
import org.supercsv.io.*;
import org.supercsv.prefs.*;

import com.example.analyticsservice.exception.*;
import com.example.analyticsservice.model.*;
import com.example.analyticsservice.repository.*;

import static com.example.analyticsservice.repository.SelectedProductsSpecifications.*;

@Service
@RequiredArgsConstructor
public class AnalyticsCsvService {

    private final SelectedProductRepository selectedProductRepository;

    private final ModelMapper modelMapper;

    @SneakyThrows
    public String generateCsv(
        String currency,
        LocalDate startDate,
        LocalDate endDate,
        String city
    ) {
        var spec = betweenDateByCurrencyAndCityIfPresent(startDate, endDate, currency, city);
        var selectedProducts = selectedProductRepository.findAll(spec);

        var products = selectedProducts.stream()
            .map(selected -> modelMapper.map(selected, CsvSelectedProductModel.class))
            .toList();

        if (products.isEmpty()) {
            throw new SelectedProductsNotFoundException();
        }

        StringWriter stringWriter = new StringWriter();
        ICsvBeanWriter beanWriter = new CsvBeanWriter(
            stringWriter,
            CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

        var headers = getHeaders(CsvSelectedProductModel.class);
        beanWriter.writeHeader(headers);
        for (var selected : products) {
            beanWriter.write(selected, headers);
        }
        beanWriter.close();
        return stringWriter.toString();
    }

    private String[] getHeaders(Class<?> clazz) {

        var fields = clazz.getDeclaredFields();
        var length = fields.length;
        var headers = new String[length];

        for (int i = 0; i < length; i++) {
            headers[i] = fields[i].getName();
        }

        return headers;
    }

}
