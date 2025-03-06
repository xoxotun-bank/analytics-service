package com.example.analyticsservice.configuration;

import java.time.*;
import java.time.temporal.*;

import org.modelmapper.*;
import org.modelmapper.convention.*;
import org.springframework.context.annotation.*;

import static org.modelmapper.config.Configuration.AccessLevel.*;

import com.example.analyticsservice.entity.*;
import com.example.analyticsservice.model.*;
import com.example.analyticsservice.swagger.server.dto.*;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();

        mapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(PRIVATE)
            .setSkipNullEnabled(true);

        addSelectedProductMapping(mapper);

        return mapper;
    }

    private void addSelectedProductMapping(ModelMapper modelMapper) {
        modelMapper.createTypeMap(SelectedProductRequestDto.class, SelectedProductModel.class);
        modelMapper.getTypeMap(SelectedProductRequestDto.class, SelectedProductModel.class)
            .setPostConverter(
                mappingContext -> {
                    var source = mappingContext.getSource();
                    var destination = mappingContext.getDestination();

                    if (source.getClientBirthDate() != null) {
                        int age = (int) ChronoUnit.YEARS.between(
                            source.getClientBirthDate(),
                            LocalDate.now());
                        destination.setClientAge(age);
                    }

                    return destination;
                }
            );

        modelMapper.createTypeMap(SelectedProductEntity.class, CsvSelectedProductModel.class)
            .addMappings(mapper -> {
                mapper.map(
                    src -> src.getFinancialProduct().getId(),
                    CsvSelectedProductModel::setFinancialProductId
                );

                mapper.map(
                    src -> src.getFinancialProduct().getName(), CsvSelectedProductModel::setName
                );

                mapper.map(
                    src -> src.getFinancialProduct().getCategory(),
                    CsvSelectedProductModel::setCategory
                );

                mapper.map(
                    src -> src.getFinancialProduct().getCanDeposit(),
                    CsvSelectedProductModel::setCanDeposit
                );

                mapper.map(
                    src -> src.getFinancialProduct().getCanWithdrawal(),
                    CsvSelectedProductModel::setCanWithdrawal
                );

                mapper.map(
                    src -> src.getFinancialProduct().getCapitalizationToSameAccount(),
                    CsvSelectedProductModel::setCapitalizationToSameAccount
                );

                mapper.map(
                    src -> src.getFinancialProduct().getPercent(),
                    CsvSelectedProductModel::setPercent
                );

                mapper.map(
                    src -> src.getFinancialProduct().getCapitalizationPeriod(),
                    CsvSelectedProductModel::setCapitalizationPeriod
                );

                mapper.map(
                    src -> src.getFinancialProduct().getCurrency(),
                    CsvSelectedProductModel::setCurrency
                );

                mapper.map(
                    src -> src.getFinancialProduct().getPeriod(),
                    CsvSelectedProductModel::setPeriod
                );

            });
    }

}
