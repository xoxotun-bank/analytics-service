package com.example.analyticsservice.swagger.server.dto;

import java.math.*;
import java.util.*;

import javax.validation.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.*;
import org.springframework.validation.annotation.*;

/**
 * StatisticsDto
 */
@Validated

public class StatisticsDto {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("value")
    private BigDecimal value = null;

    @JsonProperty("growthValue")
    private BigDecimal growthValue = null;

    @JsonProperty("mark")
    private String mark = null;

    @JsonProperty("nameOfPeriod")
    private String nameOfPeriod = null;

    public StatisticsDto name(String name) {
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

    public StatisticsDto value(BigDecimal value) {
        this.value = value;
        return this;
    }

    /**
     * Get value
     *
     * @return value
     **/
    @Schema(required = true, description = "")
    @NotNull

    @Valid
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public StatisticsDto growthValue(BigDecimal growthValue) {
        this.growthValue = growthValue;
        return this;
    }

    /**
     * Get growthValue
     *
     * @return growthValue
     **/
    @Schema(description = "")
    @NotNull

    @Valid
    public BigDecimal getGrowthValue() {
        return growthValue;
    }

    public void setGrowthValue(BigDecimal growthValue) {
        this.growthValue = growthValue;
    }

    public StatisticsDto mark(String mark) {
        this.mark = mark;
        return this;
    }

    /**
     * Get mark
     *
     * @return mark
     **/
    @Schema(required = true, description = "")
    @NotNull

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public StatisticsDto nameOfPeriod(String nameOfPeriod) {
        this.nameOfPeriod = nameOfPeriod;
        return this;
    }

    /**
     * Get nameOfPeriod
     *
     * @return nameOfPeriod
     **/
    @Schema(required = true, description = "")
    @NotNull

    public String getNameOfPeriod() {
        return nameOfPeriod;
    }

    public void setNameOfPeriod(String nameOfPeriod) {
        this.nameOfPeriod = nameOfPeriod;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StatisticsDto statisticsDto = (StatisticsDto) o;
        return Objects.equals(this.name, statisticsDto.name) &&
               Objects.equals(this.value, statisticsDto.value) &&
               Objects.equals(this.growthValue, statisticsDto.growthValue) &&
               Objects.equals(this.mark, statisticsDto.mark) &&
               Objects.equals(this.nameOfPeriod, statisticsDto.nameOfPeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, growthValue, mark, nameOfPeriod);
    }

    @Override
    public String toString() {

        String sb = "class StatisticsDto {\n" +
                    "    name: " + toIndentedString(name) + "\n" +
                    "    value: " + toIndentedString(value) + "\n" +
                    "    growthValue: " + toIndentedString(growthValue) + "\n" +
                    "    mark: " + toIndentedString(mark) + "\n" +
                    "    nameOfPeriod: " + toIndentedString(nameOfPeriod) + "\n" +
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
