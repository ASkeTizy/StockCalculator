package org.example.stockcalc.controller;

import org.example.stockcalc.entity.StockPortfolio;
import org.example.stockcalc.service.DividendService;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
@Controller
public class DividendCalculationController {
    private DividendService dividendService;

    public DividendCalculationController(DividendService dividendService) {
        this.dividendService = dividendService;
    }
    public void calculateDividends(){

        dividendService.getDividendsByKeyAndDate("SBER",LocalDate.of(2024,1,1),LocalDate.now());

    }
}
