package org.example.stockcalc.service;

import org.example.stockcalc.entity.Position;
import org.springframework.stereotype.Service;

@Service
public class CalculateService {
    private final DividendService dividendService;
    private final PositionCalculationService positionCalculationService;

    public CalculateService(DividendService dividendService, PositionCalculationService positionCalculationService) {
        this.dividendService = dividendService;
        this.positionCalculationService = positionCalculationService;
    }


    private Double calculateProfitPercent(Double buyPrice, Double sellPrice,Double dividendSum) {
        double profit = (sellPrice - buyPrice + dividendSum) / buyPrice * 100;
        return Math.round(profit * 100.0) / 100.0;

    }
    private Double calculateProfitInPureMoney(Double buyPrice, Double sellPrice, Integer num) {
        return (sellPrice - buyPrice) * num;
    }
    public Double calculateDividendsProfit(Position position) {
        return dividendService.calculateDividends(position);
    }
    public Double calculateProfitInPercentWithReinvesting(Position position){
        Position positionWithPrices = positionCalculationService.storePrices(position);
        Double dividendSum = calculateDividendsProfit(position);
        System.out.println(positionWithPrices);
        return calculateProfitPercent(positionWithPrices.getBuyPrice(),positionWithPrices.getSellPrice(),dividendSum);
    }
    public Double calculateProfitInPercentWithoutReinvesting(Position position) {
        Position positionWithPrices = positionCalculationService.storePrices(position);
        return calculateProfitPercent(positionWithPrices.getBuyPrice(),positionWithPrices.getSellPrice(),0.0);

    }
    public Double calculateProfitMoneyWithoutReinvesting(Position position) {
        Position positionWithPrices = positionCalculationService.storePrices(position);
        return calculateProfitInPureMoney(position.getBuyPrice(),position.getSellPrice(), position.getCount());
    }
    public Double calculateProfitWithReinvesting(Position position) {
        Double dividendSum = calculateDividendsProfit(position);
        Double pureProfitBySell = calculateProfitMoneyWithoutReinvesting(position);
        return pureProfitBySell + dividendSum;
    }

}
