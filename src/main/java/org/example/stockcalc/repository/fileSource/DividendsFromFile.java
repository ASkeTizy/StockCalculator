package org.example.stockcalc.repository.fileSource;

import org.example.stockcalc.entity.Dividend;
import org.example.stockcalc.repository.DividendsReceive;
import org.example.stockcalc.repository.JSONParser;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;



public class DividendsFromFile implements DividendsReceive {
    private final JSONParser jsonParser;

    public DividendsFromFile(JSONParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    @Override
    public List<Dividend> getDividends(String type) {
        InputStream input = JSONParser.class.getResourceAsStream("/source/dividends.json");
        var parser = new JSONParser(input, "dividends");
        return parser.getDividends(type);

    }

    @Override
    public List<Dividend> getDividendByKeyAndDate(String key, LocalDate startDate, LocalDate endDate) {
        var dividends =getDividends(key);
        List<Dividend> finalArr = dividends.stream().filter(el -> el.date().isAfter(startDate) && el.date().isBefore(endDate)).toList();
//        endedList.forEach(System.out::println);
        return finalArr;
    }


}
