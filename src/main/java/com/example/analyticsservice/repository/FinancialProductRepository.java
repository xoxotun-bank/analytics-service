package com.example.analyticsservice.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.example.analyticsservice.entity.*;

public interface FinancialProductRepository extends JpaRepository<FinancialProductEntity, Long> {

    @Query("select distinct fp.currency from FinancialProductEntity fp")
    List<String> findAllCurrencies();

}
