package org.example.stockcalc.service;

import org.example.stockcalc.entity.Position;

import org.example.stockcalc.repository.PositionReceive;
import org.springframework.stereotype.Service;

@Service
public class PositionCalculationService {
    private final PositionReceive positionReceive;


    public PositionCalculationService(PositionReceive positionReceive) {
        this.positionReceive = positionReceive;

    }

    public Position storePrices(Position position) {
        if (position.getBuyPrice() == null && position.getSellPrice() == null) {
            var list = positionReceive.getPositionsByKeyAndDate(position.getType(), position.getStartDate(), position.getEndDate());
            var buyPrice = list.getFirst().legalClosePrice();
            var sellPrice = list.getLast().legalClosePrice();
            position.setBuyPrice(buyPrice);
            position.setSellPrice(sellPrice);
        }
        return position;
    }


}
