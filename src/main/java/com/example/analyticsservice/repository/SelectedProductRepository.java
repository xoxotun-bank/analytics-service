package com.example.analyticsservice.repository;

import java.time.*;
import java.util.*;

import org.springframework.data.jpa.domain.*;
import org.springframework.data.jpa.repository.*;

import com.example.analyticsservice.entity.*;

public interface SelectedProductRepository extends JpaRepository<SelectedProductEntity, Long>,
    JpaSpecificationExecutor<SelectedProductEntity> {

    @EntityGraph(value = "selected-products_entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    List<SelectedProductEntity> findAll(Specification<SelectedProductEntity> spec);

    @Query("select distinct sp.city from SelectedProductEntity sp")
    List<String> findAllCities();

    @Query("select min(sp.createdAt) from SelectedProductEntity sp")
    LocalDate findMinCreatedAt();

}
