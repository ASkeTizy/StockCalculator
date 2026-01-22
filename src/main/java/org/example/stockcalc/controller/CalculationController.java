package org.example.stockcalc.controller;

import org.example.stockcalc.entity.Dividend;
import org.example.stockcalc.entity.Position;
import org.example.stockcalc.entity.PositionFromSource;
import org.example.stockcalc.service.CalculateService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class CalculationController {
    private CalculateService calculateService;

    public CalculationController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    public List<Double> calculateProfitWithDividends(String type, LocalDate startDate, LocalDate endDate, Integer amount) {
        var position = new Position(type, startDate, endDate, amount);
        var dividendProfit = calculateService.calculateDividendsProfit(position);
        var dividendProfitPercent = calculateService.calculateProfitInPercentWithDividends(position);
        return List.of(dividendProfit, dividendProfitPercent);
    }

    public List<Double> calculateProfitWithoutDividends(String type, LocalDate startDate, LocalDate endDate, Integer amount) {
        var position = new Position(type, startDate, endDate, amount);
        var dividendProfit = calculateService.calculateProfitMoneyWithoutReinvesting(position);
        var dividendProfitPercent = calculateService.calculateProfitInPercentWithoutReinvesting(position);
        return List.of(dividendProfit, dividendProfitPercent);
    }

    public List<Dividend> getDividends(String type) {
        return calculateService.getDividends(type);
    }
    public List<PositionFromSource> getPositions(String type,LocalDate startDate,LocalDate endDate) {
        return calculateService.getPositionByKeyAndDate(type,startDate,endDate);
    }
}
