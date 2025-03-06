package com.example.analyticsservice.swagger.server.dto;

import java.util.*;

import javax.validation.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.*;
import org.springframework.validation.annotation.*;

/**
 * AnalyticsResponseDtoBarChart
 */
@Validated

public class AnalyticsResponseDtoBarChart {

    @JsonProperty("xAxis")
    @Valid
    private List<String> xAxis = new ArrayList<>();

    @JsonProperty("bars")
    @Valid
    private List<ChartDto> bars = new ArrayList<>();

    public AnalyticsResponseDtoBarChart xAxis(List<String> xAxis) {
        this.xAxis = xAxis;
        return this;
    }

    public AnalyticsResponseDtoBarChart addXAxisItem(String xAxisItem) {
        this.xAxis.add(xAxisItem);
        return this;
    }

    /**
     * Get xAxis
     *
     * @return xAxis
     **/
    @Schema(example = "[\"АТБ.ВИП. ВКЛАД (без пополнения и снятия)\",\"АТБ. СЧЕТ\",\"АТБ. ВКЛАД (с пополнением)\",\"АТБ. ВКЛАД ОНЛАЙН (без пополнения и снятия)\",\"«НОМИНАЛЬНЫЙ СЧЕТ»\",\"«В ПЛЮСЕ»\"]", required = true, description = "")
    @NotNull

    public List<String> getXAxis() {
        return xAxis;
    }

    public void setXAxis(List<String> xAxis) {
        this.xAxis = xAxis;
    }

    public AnalyticsResponseDtoBarChart bars(List<ChartDto> bars) {
        this.bars = bars;
        return this;
    }

    public AnalyticsResponseDtoBarChart addBarsItem(ChartDto barsItem) {
        this.bars.add(barsItem);
        return this;
    }

    /**
     * Get bars
     *
     * @return bars
     **/
    @Schema(example = "[{\"label\":\"Молодёжь (18-35)\",\"values\":[15,63,72,15,63,72]},{\"label\":\"Взрослые (35-60)\",\"values\":[8,45,96,8,45,96]},{\"label\":\"Пожилые (60+)\",\"values\":[22,36,87,22,36,87]}]", required = true, description = "")
    @NotNull
    @Valid
    public List<ChartDto> getBars() {
        return bars;
    }

    public void setBars(List<ChartDto> bars) {
        this.bars = bars;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AnalyticsResponseDtoBarChart analyticsResponseDtoBarChart = (AnalyticsResponseDtoBarChart) o;
        return Objects.equals(this.xAxis, analyticsResponseDtoBarChart.xAxis) &&
               Objects.equals(this.bars, analyticsResponseDtoBarChart.bars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xAxis, bars);
    }

    @Override
    public String toString() {

        String sb = "class AnalyticsResponseDtoBarChart {\n" +
                    "    xAxis: " + toIndentedString(xAxis) + "\n" +
                    "    bars: " + toIndentedString(bars) + "\n" +
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
