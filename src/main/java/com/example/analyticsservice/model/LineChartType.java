package com.example.analyticsservice.model;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum LineChartType {

    INVESTED_MONEY_RUB("Объём привлеченных средств, 100тыс руб"),
    INVESTED_MONEY_CNY("Объём привлеченных средств, 10тыс юань"),
    NUM_OF_CLIENTS("Кол-во новых клиентов"),
    SELECTED_PRODUCTS("Кол-во подобранных вкладов");

    private final String type;

    public static LineChartType getInvestedMoneyType(CurrencyType currency) {
        switch (currency) {
            case RUB -> {
                return INVESTED_MONEY_RUB;
            }
            case CNY -> {
                return INVESTED_MONEY_CNY;
            }
            default -> throw new BadRequestConfigurationException("currency - " + currency);
        }
    }

}
