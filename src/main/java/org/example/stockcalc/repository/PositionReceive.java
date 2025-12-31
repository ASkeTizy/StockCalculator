package org.example.stockcalc.repository;

import org.example.stockcalc.entity.Position;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PositionReceive {
    List<String> getPositionCodes();
    List<Position> getPositionsByKeyAndDate(String name, LocalDate startDate, LocalDate endDate);
}
