package org.example.stockcalc.entity;

import java.time.LocalDate;

public record Dividend(
        String currencyId,
        Double coefficient,
        LocalDate date
) {
}


