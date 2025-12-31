package org.example.stockcalc.entity;

import java.util.Map;

public class StockPortfolio {
    private Map<String,Promotion> promotions;

    public StockPortfolio(Map<String, Promotion> promotions) {
        this.promotions = promotions;
    }

    public Map<String, Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(Map<String, Promotion> promotions) {
        this.promotions = promotions;
    }
}
