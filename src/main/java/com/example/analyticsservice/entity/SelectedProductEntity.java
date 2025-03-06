package com.example.analyticsservice.entity;

import java.math.*;
import java.time.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "selected_products")
@NamedEntityGraph(
    name = "selected-products_entity-graph",
    attributeNodes = {
        @NamedAttributeNode("financialProduct")
    }
)
public class SelectedProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotNull
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "client_age")
    private Integer clientAge;

    @Column(name = "is_new_client")
    private Boolean isNewClient;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Size(max = 255)
    @NotNull
    @Column(name = "city", nullable = false)
    private String city;

    @Size(max = 255)
    @Column(name = "region", nullable = false)
    private String region;

    @NotNull
    @Column(name = "is_successfully_selected", nullable = false)
    private Boolean isSuccessfullySelected = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financial_product_id", nullable = false)
    private FinancialProductEntity financialProduct;

}
