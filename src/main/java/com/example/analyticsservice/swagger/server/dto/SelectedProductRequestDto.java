package com.example.analyticsservice.swagger.server.dto;

import java.math.*;
import java.time.*;
import java.util.*;

import javax.validation.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.*;
import org.springframework.validation.annotation.*;

/**
 * SelectedProductRequestDto
 */
@Validated

public class SelectedProductRequestDto {

    @JsonProperty("createdAt")
    private LocalDate createdAt = null;

    @JsonProperty("amount")
    private BigDecimal amount = null;

    @JsonProperty("isNewClient")
    private Boolean isNewClient = null;

    @JsonProperty("isSuccessfullySelected")
    @NotNull
    private Boolean isSuccessfullySelected = null;

    @JsonProperty("clientBirthDate")
    private LocalDate clientBirthDate = null;

    @JsonProperty("financialProduct")
    private FinancialProductDto financialProduct = null;

    public SelectedProductRequestDto createdAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Время выбора вклада
     *
     * @return createdAt
     **/
    @Schema(example = "Sun Jan 01 10:00:00 VLAT 2017", required = true, description = "Время выбора вклада")
    @NotNull

    @Valid
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public SelectedProductRequestDto amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Название вклада
     *
     * @return amount
     **/
    @Schema(example = "200000", required = true, description = "Название вклада")
    @NotNull

    @Valid
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public SelectedProductRequestDto isNewClient(Boolean isNewClient) {
        this.isNewClient = isNewClient;
        return this;
    }

    /**
     * Вклад подобран новому клиенту
     *
     * @return isNewClient
     **/
    @Schema(example = "true", description = "Вклад подобран новому клиенту")
    @NotNull

    public Boolean isIsNewClient() {
        return isNewClient;
    }

    public void setIsNewClient(Boolean isNewClient) {
        this.isNewClient = isNewClient;
    }

    public SelectedProductRequestDto isSuccessfullySelected(Boolean isSuccessfullySelected) {
        this.isSuccessfullySelected = isSuccessfullySelected;
        return this;
    }

    /**
     * Вклад был передан на оформление (успешно подобран)
     *
     * @return isSuccessfullySelected
     **/
    @Schema(example = "true", required = true, description = "Вклад был передан на оформление (успешно подобран)")
    @NotNull

    public Boolean isIsSuccessfullySelected() {
        return isSuccessfullySelected;
    }

    public void setIsSuccessfullySelected(Boolean isSuccessfullySelected) {
        this.isSuccessfullySelected = isSuccessfullySelected;
    }

    public SelectedProductRequestDto clientBirthDate(LocalDate clientBirthDate) {
        this.clientBirthDate = clientBirthDate;
        return this;
    }

    /**
     * Дата рождения клиента, формат ГГГГ-ММ-ДД
     *
     * @return clientBirthDate
     **/
    @Schema(example = "Wed May 15 11:00:00 VLAST 1985", description = "Дата рождения клиента, формат ГГГГ-ММ-ДД")

    @Valid
    public LocalDate getClientBirthDate() {
        return clientBirthDate;
    }

    public void setClientBirthDate(LocalDate clientBirthDate) {
        this.clientBirthDate = clientBirthDate;
    }

    public SelectedProductRequestDto financialProduct(FinancialProductDto financialProduct) {
        this.financialProduct = financialProduct;
        return this;
    }

    /**
     * Get financialProduct
     *
     * @return financialProduct
     **/
    @Schema(required = true, description = "")
    @NotNull

    @Valid
    public FinancialProductDto getFinancialProduct() {
        return financialProduct;
    }

    public void setFinancialProduct(FinancialProductDto financialProduct) {
        this.financialProduct = financialProduct;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SelectedProductRequestDto selectedProductRequestDto = (SelectedProductRequestDto) o;
        return Objects.equals(this.createdAt, selectedProductRequestDto.createdAt) &&
               Objects.equals(this.amount, selectedProductRequestDto.amount) &&
               Objects.equals(this.isNewClient, selectedProductRequestDto.isNewClient) &&
               Objects.equals(
                   this.isSuccessfullySelected,
                   selectedProductRequestDto.isSuccessfullySelected) &&
               Objects.equals(this.clientBirthDate, selectedProductRequestDto.clientBirthDate) &&
               Objects.equals(this.financialProduct, selectedProductRequestDto.financialProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            createdAt,
            amount,
            isNewClient,
            isSuccessfullySelected,
            clientBirthDate,
            financialProduct);
    }

    @Override
    public String toString() {

        String sb = "class SelectedProductRequestDto {\n" +
                    "    createdAt: " + toIndentedString(createdAt) + "\n" +
                    "    amount: " + toIndentedString(amount) + "\n" +
                    "    isNewClient: " + toIndentedString(isNewClient) + "\n" +
                    "    isSuccessfullySelected: " +
                    toIndentedString(isSuccessfullySelected) +
                    "\n" +
                    "    clientBirthDate: " + toIndentedString(clientBirthDate) + "\n" +
                    "    financialProduct: " + toIndentedString(financialProduct) + "\n" +
                    "}";
        return sb;
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
