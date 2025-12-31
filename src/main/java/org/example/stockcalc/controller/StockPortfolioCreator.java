package org.example.stockcalc.controller;

import org.example.stockcalc.entity.Position;
import org.example.stockcalc.entity.Promotion;
import org.example.stockcalc.entity.StockPortfolio;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.HashMap;
@Controller
public class StockPortfolioCreator {
    private StockPortfolio stockPortfolio;
    public void createPortfolio(){
        this.stockPortfolio = new StockPortfolio(new HashMap<>());
    }
    public void addPositionToPortfolio(String key, Position position){
        var promotions = this.stockPortfolio.getPromotions();
        promotions.putIfAbsent(key, new Promotion(key));
        var positions = promotions.get(key).getPositions();
        positions.add(position);

    }

    public StockPortfolio getStockPortfolio() {
        return stockPortfolio;
    }

    public Position createPosition(Double price, LocalDateTime date, Integer count) {
        return new Position(price,date,count);

    }
}
