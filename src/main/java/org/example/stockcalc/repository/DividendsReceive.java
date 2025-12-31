package org.example.stockcalc.repository;

import org.example.stockcalc.entity.Dividend;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DividendsReceive {
    Map<String, List<Dividend>> getDividends();
    List<Dividend> getDividendByKeyAndDate(String key, LocalDate startDate, LocalDate endDate);

}
