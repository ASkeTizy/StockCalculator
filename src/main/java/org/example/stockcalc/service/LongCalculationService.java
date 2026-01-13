package org.example.stockcalc.service;

import org.example.stockcalc.entity.Position;
import org.example.stockcalc.repository.PositionReceive;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LongCalculationService {
    PositionReceive positionReceive;

    public LongCalculationService(PositionReceive positionReceive) {
        this.positionReceive = positionReceive;
    }
    public Double calculateProfitWithoutReinvesting(Position position) {
       var list =  positionReceive.getPositionsByKeyAndDate(position.getType(),position.getStartDate(),position.getEndDate());
       var buyPrice =  list.getFirst().legalClosePrice();
       var sellPrice = list.getLast().legalClosePrice();
       return calculateProfitInPureMoney(buyPrice,sellPrice,position.getCount());
    }
    private Double calculateProfit(Double buyPrice, Double sellPrice) {
        return (double) Math.round((sellPrice - buyPrice) / buyPrice);
    }
    private Double calculateProfitInPercent(Double buyPrice, Double sellPrice) {
        return calculateProfit(buyPrice,sellPrice)*100;
    }
    private Double calculateProfitInPureMoney(Double buyPrice, Double sellPrice,Integer num) {
        return calculateProfit(buyPrice,sellPrice)*num;
    }
}
