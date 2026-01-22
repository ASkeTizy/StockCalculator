package org.example.stockcalc.repository.fileSource;

import org.example.stockcalc.entity.Dividend;
import org.example.stockcalc.repository.DividendsReceive;
import org.example.stockcalc.repository.JSONParser;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;



public class DividendsFromFile implements DividendsReceive {
    private final JSONParser jsonParser;

    public DividendsFromFile(JSONParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    @Override
    public List<Dividend> getDividends(String type) {
        var parser = new JSONParser("dividends", "dividends");
        var indexes = parser.getNeededColumnsFromFile(Arrays.asList("registryclosedate", "value", "currencyid"));
        var spittedList = parser.getListOfDataFromFile();
        List<Dividend> endedList = spittedList.stream()
                .map(el -> {
                    var date = parser.dateParser(el.get(indexes.get("registryclosedate")));
                    var currencyId = el.get(indexes.get("currencyid"));
                    var value = Double.parseDouble(el.get(indexes.get("value")));
                    return new Dividend(currencyId, value, date);
                }).toList();
        return endedList;
    }

    @Override
    public List<Dividend> getDividendByKeyAndDate(String key, LocalDate startDate, LocalDate endDate) {
        var dividends =getDividends(key);
        List<Dividend> finalArr = dividends.stream().filter(el -> el.date().isAfter(startDate) && el.date().isBefore(endDate)).toList();
//        endedList.forEach(System.out::println);
        return finalArr;
    }


}
