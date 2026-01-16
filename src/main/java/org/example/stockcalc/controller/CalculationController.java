package org.example.stockcalc.controller;

import org.example.stockcalc.entity.Position;
import org.example.stockcalc.service.CalculateService;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
@Controller
public class CalculationController {
    private CalculateService calculateService;

    public CalculationController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    public Double profitWithDividendsPercent(){
        var position = new Position("SBER",LocalDate.of(2022,2,24),LocalDate.now(),1000);
        return calculateService.calculateProfitInPercentWithReinvesting(position);

    }
    public Double profitDividends(){
        var position = new Position("SBER",LocalDate.of(2022,2,24),LocalDate.now(),1000);
        return calculateService.calculateDividendsProfit(position);

    }
    public Double profitWithoutDividendsPercent(){
        var position = new Position("SBER",LocalDate.of(2022,2,24),LocalDate.now(),1000);
        return calculateService.calculateProfitInPercentWithoutReinvesting(position);

    }
    public Double profitWithoutDividends() {
        var position = new Position("SBER",LocalDate.of(2022,2,24),LocalDate.now(),1000);
        return calculateService.calculateProfitMoneyWithoutReinvesting(position);

    }
    public Double profitWithDividends() {
        var position = new Position("SBER",LocalDate.of(2022,1,1),LocalDate.now(),1000);
        return calculateService.calculateProfitWithReinvesting(position);

    }
}
