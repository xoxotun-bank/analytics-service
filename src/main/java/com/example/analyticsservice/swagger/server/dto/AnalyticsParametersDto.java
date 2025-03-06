package com.example.analyticsservice.swagger.server.dto;

import java.util.*;

import javax.validation.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.*;
import org.springframework.validation.annotation.*;

/**
 * AnalyticsParametersDto
 */
@Validated

public class AnalyticsParametersDto {

    @JsonProperty("cities")
    @Valid
    private List<String> cities = null;

    @JsonProperty("currencies")
    @Valid
    private List<String> currencies = null;

    @JsonProperty("periods")
    @Valid
    private List<String> periods = null;

    @JsonProperty("startDate")
    private String startDate = null;

    public AnalyticsParametersDto cities(List<String> cities) {
        this.cities = cities;
        return this;
    }

    public AnalyticsParametersDto addCitiesItem(String citiesItem) {
        if (this.cities == null) {
            this.cities = new ArrayList<>();
        }
        this.cities.add(citiesItem);
        return this;
    }

    /**
     * Get cities
     *
     * @return cities
     **/
    @Schema(example = "[\"Владивосток\",\"Екатеринбург\",\"Биробиджан\",\"Хабаровск\"]", description = "")
    @NotNull

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public AnalyticsParametersDto currencies(List<String> currencies) {
        this.currencies = currencies;
        return this;
    }

    public AnalyticsParametersDto addCurrenciesItem(String currenciesItem) {
        if (this.currencies == null) {
            this.currencies = new ArrayList<>();
        }
        this.currencies.add(currenciesItem);
        return this;
    }

    /**
     * Get currencies
     *
     * @return currencies
     **/
    @Schema(example = "[\"RUB\",\"CNY\"]", description = "")
    @NotNull

    public List<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }

    public AnalyticsParametersDto periods(List<String> periods) {
        this.periods = periods;
        return this;
    }

    public AnalyticsParametersDto addPeriodsItem(String periodsItem) {
        if (this.periods == null) {
            this.periods = new ArrayList<>();
        }
        this.periods.add(periodsItem);
        return this;
    }

    /**
     * Get periods
     *
     * @return periods
     **/
    @Schema(example = "[\"Месяц\",\"Квартал\",\"Год\"]", description = "")
    @NotNull

    public List<String> getPeriods() {
        return periods;
    }

    public void setPeriods(List<String> periods) {
        this.periods = periods;
    }

    public AnalyticsParametersDto startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Первая дата за которую есть подобранные вклады в БД аналитики
     *
     * @return startDate
     **/
    @Schema(example = "2017-01-01", description = "Первая дата за которую есть подобранные вклады в БД аналитики")
    @NotNull

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AnalyticsParametersDto analyticsParametersDto = (AnalyticsParametersDto) o;
        return Objects.equals(this.cities, analyticsParametersDto.cities) &&
               Objects.equals(this.currencies, analyticsParametersDto.currencies) &&
               Objects.equals(this.periods, analyticsParametersDto.periods) &&
               Objects.equals(this.startDate, analyticsParametersDto.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cities, currencies, periods, startDate);
    }

    @Override
    public String toString() {

        String sb = "class AnalyticsParametersDto {\n" +
                    "    cities: " + toIndentedString(cities) + "\n" +
                    "    currencies: " + toIndentedString(currencies) + "\n" +
                    "    periods: " + toIndentedString(periods) + "\n" +
                    "    startDate: " + toIndentedString(startDate) + "\n" +
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
