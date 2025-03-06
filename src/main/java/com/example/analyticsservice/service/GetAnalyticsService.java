package com.example.analyticsservice.service;

import java.time.*;
import java.util.*;

import lombok.*;
import org.springframework.stereotype.*;

import com.example.analyticsservice.entity.*;
import com.example.analyticsservice.repository.*;
import com.example.analyticsservice.swagger.server.dto.*;

import static com.example.analyticsservice.repository.SelectedProductsSpecifications.*;
import static com.example.analyticsservice.util.DateUtil.*;

@Service
@RequiredArgsConstructor
public class GetAnalyticsService {

    private final SelectedProductRepository selectedProductRepository;

    private final LineChartService lineChartService;

    private final ProductsService productsService;

    private final PieChartService pieChartService;

    private final StatisticsService statisticsService;

    private final BarChartService barChartService;

    public AnalyticsResponseDto getAnalytics(
        String currency,
        String period,
        String city,
        LocalDate startDate,
        LocalDate endDate
    ) {
        var analyticsResponse = new AnalyticsResponseDto();

        var startPoint = startDate;
        var endPoint = endDate;

        if (period != null) {
            endPoint = LocalDate.now();
            startPoint = getStartDate(period, endPoint);
        }

        var selectedProducts = getSelectedProducts(
            startPoint,
            endPoint,
            currency,
            city);

        if (selectedProducts.isEmpty()) {
            return null;
        }

        var lineChartDto = getLineCharts(startPoint, endPoint, currency, selectedProducts);
        var productsDto = productsService.getTopOfProducts(selectedProducts);
        var pieChartDto = pieChartService.getPieChart(selectedProducts);
        var statistics = getStatistics(
            currency,
            period,
            city,
            startPoint,
            endPoint,
            selectedProducts);
        var barChartDto = barChartService.getBarChartDto(selectedProducts);

        analyticsResponse.setLineChart(lineChartDto);
        analyticsResponse.setProducts(productsDto);
        analyticsResponse.setPieChart(pieChartDto);
        analyticsResponse.setStatistics(statistics);
        analyticsResponse.setBarChart(barChartDto);
        return analyticsResponse;
    }

    private List<StatisticsDto> getStatistics(
        String currency,
        String period,
        String city,
        LocalDate startDate,
        LocalDate endDate,
        List<SelectedProductEntity> selectedProducts
    ) {

        List<StatisticsDto> statistics = new ArrayList<>();

        var previousStartDate = getStartDateOfPreviousPeriod(
            period,
            startDate,
            endDate);

        var previousEndDate = getEndDateOfPreviousPeriod(
            period,
            startDate,
            previousStartDate);

        var previousSelectedProducts = getSelectedProducts(
            previousStartDate,
            previousEndDate,
            currency,
            city);

        var sumStatistics = statisticsService.getSum(
            period,
            currency,
            selectedProducts,
            previousSelectedProducts);

        var successfullySelectedProductsStatistic =
            statisticsService.getConversionOfSuccessfullySelectedProducts(
                period,
                selectedProducts,
                previousSelectedProducts);

        var failedProductsStatistic =
            statisticsService.getNumOfSelectedProducts(
                period,
                selectedProducts,
                previousSelectedProducts);

        var newClientsStatistic = statisticsService.getNumOfNewClients(
            period,
            selectedProducts,
            previousSelectedProducts);

        statistics.add(sumStatistics);
        statistics.add(failedProductsStatistic);
        statistics.add(newClientsStatistic);
        statistics.add(successfullySelectedProductsStatistic);
        return statistics;
    }

    private AnalyticsResponseDtoLineChart getLineCharts(
        LocalDate startDate,
        LocalDate endDate,
        String currency,
        List<SelectedProductEntity> selectedProducts
    ) {

        var lineChartDto = new AnalyticsResponseDtoLineChart();

        var xAxis = lineChartService.getXAxis(startDate, endDate);

        List<ChartDto> charts = new ArrayList<>();

        var investedMoneyChart = lineChartService.getInvestedMoneyChart(
            xAxis,
            currency,
            selectedProducts);
        var numOfClientsChart = lineChartService.getNumOfClientsChart(xAxis, selectedProducts);
        var selectedProductsChart = lineChartService.getSelectedProductsChart(
            xAxis,
            selectedProducts);

        charts.add(investedMoneyChart);
        charts.add(numOfClientsChart);
        charts.add(selectedProductsChart);

        lineChartDto.setXAxis(xAxis);
        lineChartDto.setLines(charts);

        return lineChartDto;
    }

    private List<SelectedProductEntity> getSelectedProducts(
        LocalDate startDate,
        LocalDate endDate,
        String currency,
        String city
    ) {

        var products = selectedProductRepository.findAll(
            betweenDateByCurrencyAndCityIfPresent(startDate, endDate, currency, city)
        );

        return products;
    }

}
