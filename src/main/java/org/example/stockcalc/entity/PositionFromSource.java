package org.example.stockcalc.entity;

import java.time.LocalDate;

public record PositionFromSource(
        String shortname,
        LocalDate tradeDate,
        Double legalClosePrice

) {
}
