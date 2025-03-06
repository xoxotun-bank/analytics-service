package com.example.analyticsservice.swagger.server.dto;

import java.math.*;
import java.util.*;

import javax.validation.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.*;
import org.springframework.validation.annotation.*;

/**
 * ProductDto
 */
@Validated

public class ProductDto {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("avgAmount")
    private BigDecimal avgAmount = null;

    @JsonProperty("avgPeriod")
    private BigDecimal avgPeriod = null;

    public ProductDto name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/
    @Schema(required = true, description = "")
    @NotNull

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductDto avgAmount(BigDecimal avgAmount) {
        this.avgAmount = avgAmount;
        return this;
    }

    /**
     * Get avgAmount
     *
     * @return avgAmount
     **/
    @Schema(required = true, description = "")
    @NotNull

    @Valid
    public BigDecimal getAvgAmount() {
        return avgAmount;
    }

    public void setAvgAmount(BigDecimal avgAmount) {
        this.avgAmount = avgAmount;
    }

    public ProductDto avgPeriod(BigDecimal avgPeriod) {
        this.avgPeriod = avgPeriod;
        return this;
    }

    /**
     * Get avgPeriod
     *
     * @return avgPeriod
     **/
    @Schema(required = true, description = "")
    @NotNull

    @Valid
    public BigDecimal getAvgPeriod() {
        return avgPeriod;
    }

    public void setAvgPeriod(BigDecimal avgPeriod) {
        this.avgPeriod = avgPeriod;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductDto productDto = (ProductDto) o;
        return Objects.equals(this.name, productDto.name) &&
               Objects.equals(this.avgAmount, productDto.avgAmount) &&
               Objects.equals(this.avgPeriod, productDto.avgPeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, avgAmount, avgPeriod);
    }

    @Override
    public String toString() {

        String sb = "class ProductDto {\n" +
                    "    name: " + toIndentedString(name) + "\n" +
                    "    avgAmount: " + toIndentedString(avgAmount) + "\n" +
                    "    avgPeriod: " + toIndentedString(avgPeriod) + "\n" +
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
