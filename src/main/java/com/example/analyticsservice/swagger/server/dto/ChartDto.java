package com.example.analyticsservice.swagger.server.dto;

import java.math.*;
import java.util.*;

import javax.validation.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.*;
import org.springframework.validation.annotation.*;

/**
 * ChartDto
 */
@Validated

public class ChartDto {

    @JsonProperty("label")
    private String label = null;

    @JsonProperty("values")
    @Valid
    private List<BigDecimal> values = new ArrayList<>();

    public ChartDto label(String label) {
        this.label = label;
        return this;
    }

    /**
     * Get label
     *
     * @return label
     **/
    @Schema(required = true, description = "")
    @NotNull

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ChartDto values(List<BigDecimal> values) {
        this.values = values;
        return this;
    }

    public ChartDto addValuesItem(BigDecimal valuesItem) {
        this.values.add(valuesItem);
        return this;
    }

    /**
     * Get values
     *
     * @return values
     **/
    @Schema(required = true, description = "")
    @NotNull
    @Valid
    public List<BigDecimal> getValues() {
        return values;
    }

    public void setValues(List<BigDecimal> values) {
        this.values = values;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChartDto chartDto = (ChartDto) o;
        return Objects.equals(this.label, chartDto.label) &&
               Objects.equals(this.values, chartDto.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, values);
    }

    @Override
    public String toString() {

        String sb = "class ChartDto {\n" +
                    "    label: " + toIndentedString(label) + "\n" +
                    "    values: " + toIndentedString(values) + "\n" +
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
