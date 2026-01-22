package org.example.stockcalc.repository.fileSource;

import org.example.stockcalc.entity.PositionFromSource;
import org.example.stockcalc.repository.JSONParser;
import org.example.stockcalc.repository.PositionReceive;

import java.time.LocalDate;
import java.util.*;


public class PositionReceiveFromFile implements PositionReceive {
    private final JSONParser parser;

    public PositionReceiveFromFile(JSONParser jsonParser) {
        this.parser = jsonParser;
    }

    @Override
    public List<String> getPositionCodes() {
        return List.of();
    }

    @Override
    public List<PositionFromSource> getPositions(String type) {
        var indexes = parser.getNeededColumnsFromFile(Arrays.asList("BOARDID", "SHORTNAME", "TRADEDATE", "LEGALCLOSEPRICE"));
        var spittedList = parser.getListOfDataFromFile();
        List<PositionFromSource> endedList = spittedList.stream().filter(el -> el.get(indexes.get("BOARDID")).equals("TQBR"))
                .map(el -> {
                    var date = parser.dateParser(el.get(indexes.get("TRADEDATE")));
                    var shortName = el.get(indexes.get("SHORTNAME"));
                    var price = Double.parseDouble(el.get(indexes.get("LEGALCLOSEPRICE")));
                    return new PositionFromSource(shortName, date, price);
                }).toList();
        return endedList;
    }

    @Override
    public List<PositionFromSource> getPositionsByKeyAndDate(String name, LocalDate startDate, LocalDate endDate) {
        var endedList = getPositions(name);
        return endedList.stream().filter(el -> el.tradeDate().isAfter(startDate) && el.tradeDate().isBefore(endDate)).toList();
    }

}
