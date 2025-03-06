package com.example.analyticsservice.swagger.server.dto;

import java.util.*;

import javax.validation.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.*;
import org.springframework.validation.annotation.*;

/**
 * AnalyticsResponseDto
 */
@Validated

public class AnalyticsResponseDto {

    @JsonProperty("lineChart")
    private AnalyticsResponseDtoLineChart lineChart = null;

    @JsonProperty("barChart")
    private AnalyticsResponseDtoBarChart barChart = null;

    @JsonProperty("pieChart")
    @Valid
    private List<ChartDto> pieChart = new ArrayList<>();

    @JsonProperty("products")
    @Valid
    private List<ProductDto> products = new ArrayList<>();

    @JsonProperty("statistics")
    @Valid
    private List<StatisticsDto> statistics = null;

    public AnalyticsResponseDto lineChart(AnalyticsResponseDtoLineChart lineChart) {
        this.lineChart = lineChart;
        return this;
    }

    /**
     * Get lineChart
     *
     * @return lineChart
     **/
    @Schema(required = true, description = "")
    @NotNull

    @Valid
    public AnalyticsResponseDtoLineChart getLineChart() {
        return lineChart;
    }

    public void setLineChart(AnalyticsResponseDtoLineChart lineChart) {
        this.lineChart = lineChart;
    }

    public AnalyticsResponseDto barChart(AnalyticsResponseDtoBarChart barChart) {
        this.barChart = barChart;
        return this;
    }

    /**
     * Get barChart
     *
     * @return barChart
     **/
    @Schema(required = true, description = "")
    @NotNull

    @Valid
    public AnalyticsResponseDtoBarChart getBarChart() {
        return barChart;
    }

    public void setBarChart(AnalyticsResponseDtoBarChart barChart) {
        this.barChart = barChart;
    }

    public AnalyticsResponseDto pieChart(List<ChartDto> pieChart) {
        this.pieChart = pieChart;
        return this;
    }

    public AnalyticsResponseDto addPieChartItem(ChartDto pieChartItem) {
        this.pieChart.add(pieChartItem);
        return this;
    }

    /**
     * Get pieChart
     *
     * @return pieChart
     **/
    @Schema(example = "[{\"label\":\"Молодёжь (18-35)\",\"values\":45},{\"label\":\"Взрослые (35-60)\",\"values\":25},{\"label\":\"Пожилые (60+)\",\"values\":30}]", required = true, description = "")
    @NotNull
    @Valid
    public List<ChartDto> getPieChart() {
        return pieChart;
    }

    public void setPieChart(List<ChartDto> pieChart) {
        this.pieChart = pieChart;
    }

    public AnalyticsResponseDto products(List<ProductDto> products) {
        this.products = products;
        return this;
    }

    public AnalyticsResponseDto addProductsItem(ProductDto productsItem) {
        this.products.add(productsItem);
        return this;
    }

    /**
     * Get products
     *
     * @return products
     **/
    @Schema(example = "[{\"name\":\"АТБ.ВИП. ВКЛАД (без пополнения и снятия)\",\"avgAmount\":1234000,\"avgPeriod\":24},{\"name\":\"АТБ. СЧЕТ\",\"avgAmount\":1134000,\"avgPeriod\":9},{\"name\":\"АТБ. ВКЛАД (с пополнением)\",\"avgAmount\":1034000,\"avgPeriod\":36},{\"name\":\"АТБ. ВКЛАД ОНЛАЙН (без пополнения и снятия)\",\"avgAmount\":934000,\"avgPeriod\":6},{\"name\":\"«НОМИНАЛЬНЫЙ СЧЕТ»\",\"avgAmount\":834000,\"avgPeriod\":3},{\"name\":\"«В ПЛЮСЕ»\",\"avgAmount\":734000,\"avgPeriod\":1}]", required = true, description = "")
    @NotNull
    @Valid
    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public AnalyticsResponseDto statistics(List<StatisticsDto> statistics) {
        this.statistics = statistics;
        return this;
    }

    public AnalyticsResponseDto addStatisticsItem(StatisticsDto statisticsItem) {
        if (this.statistics == null) {
            this.statistics = new ArrayList<>();
        }
        this.statistics.add(statisticsItem);
        return this;
    }

    /**
     * Get statistics
     *
     * @return statistics
     **/
    @Schema(example = "[{\"name\":\"Объем привлеченных средств\",\"value\":1234000000,\"growthValue\":8.6,\"mark\":\"₽\",\"nameOfPeriod\":\"по сравнению с прошлым годом\"},{\"name\":\"Успешно подобранные вклады\",\"value\":75.6,\"growthValue\":5.2,\"mark\":\"%\",\"nameOfPeriod\":\"по сравнению с прошлым годом\"},{\"name\":\"Кол-во подобранных вкладов\",\"value\":10353,\"growthValue\":8.5,\"mark\":\"\",\"nameOfPeriod\":\"по сравнению с прошлым годом\"},{\"name\":\"Процент новых клиентов\",\"value\":10,\"growthValue\":-7,\"mark\":\"%\",\"nameOfPeriod\":\"по сравнению с прошлым годом\"}]", description = "")
    @NotNull
    @Valid
    public List<StatisticsDto> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<StatisticsDto> statistics) {
        this.statistics = statistics;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AnalyticsResponseDto analyticsResponseDto = (AnalyticsResponseDto) o;
        return Objects.equals(this.lineChart, analyticsResponseDto.lineChart) &&
               Objects.equals(this.barChart, analyticsResponseDto.barChart) &&
               Objects.equals(this.pieChart, analyticsResponseDto.pieChart) &&
               Objects.equals(this.products, analyticsResponseDto.products) &&
               Objects.equals(this.statistics, analyticsResponseDto.statistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineChart, barChart, pieChart, products, statistics);
    }

    @Override
    public String toString() {

        String sb = "class AnalyticsResponseDto {\n" +
                    "    lineChart: " + toIndentedString(lineChart) + "\n" +
                    "    barChart: " + toIndentedString(barChart) + "\n" +
                    "    pieChart: " + toIndentedString(pieChart) + "\n" +
                    "    products: " + toIndentedString(products) + "\n" +
                    "    statistics: " + toIndentedString(statistics) + "\n" +
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
