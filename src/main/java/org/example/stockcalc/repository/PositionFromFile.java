package org.example.stockcalc.repository;

import org.example.stockcalc.entity.PositionFromSource;

import java.time.LocalDate;
import java.util.List;

public class PositionFromFile implements PositionReceive{
    @Override
    public List<String> getPositionCodes() {
        return List.of();
    }

    @Override
    public List<PositionFromSource> getPositions(String type) {
        return List.of();
    }

    @Override
    public List<PositionFromSource> getPositionsByKeyAndDate(String name, LocalDate startDate, LocalDate endDate) {
        return List.of();
    }
}
