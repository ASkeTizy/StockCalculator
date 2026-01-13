package org.example.stockcalc.service;

import org.example.stockcalc.entity.Dividend;
import org.example.stockcalc.entity.Position;
import org.example.stockcalc.entity.PositionFromSource;
import org.example.stockcalc.entity.StockPortfolio;
import org.example.stockcalc.repository.DividendsReceive;
import org.example.stockcalc.repository.PositionReceive;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@Service
public class DividendService {
    DividendsReceive dividendsReceive;
    PositionReceive positionReceive;
    public DividendService(DividendsReceive dividendsReceive, PositionReceive positionReceive) {
        this.dividendsReceive = dividendsReceive;
        this.positionReceive = positionReceive;
    }
    public List<Dividend> getDividendsByKeyAndDate(String key, LocalDate startDate, LocalDate endDate){
        List<Dividend> dividends = dividendsReceive.getDividendByKeyAndDate(key,startDate,endDate);
        List<PositionFromSource> positions = positionReceive.getPositionsByKeyAndDate(key,startDate,endDate);
        return dividends;
    }
    public Double calculateDividends(String key, StockPortfolio stockPortfolio) {
//        var dividends = dividendsReceive.getDividendByKeyAndDate(key);
        var promotions = stockPortfolio.getPromotions();
        var promotion = promotions.get(key);
        return 0.0;
//        return promotion.getPositions().stream().map(e->e.getPrice() * dividend.getCoefficient()).reduce(0.0,Double::sum);
    }
}
