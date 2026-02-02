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
       return parser.getPositions(type);

    }

    @Override
    public List<PositionFromSource> getPositionsByKeyAndDate(String name, LocalDate startDate, LocalDate endDate) {
        var endedList = getPositions(name);
        return endedList.stream().filter(el -> el.tradeDate().isAfter(startDate) && el.tradeDate().isBefore(endDate)).toList();
    }

}
