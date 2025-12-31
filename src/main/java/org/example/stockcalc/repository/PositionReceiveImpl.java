package org.example.stockcalc.repository;

import org.example.stockcalc.entity.Position;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
public class PositionReceiveImpl implements PositionReceive{
    public PositionReceiveImpl() {

    }

    @Override
    public List<String> getPositionCodes() {
        return List.of();
    }

    @Override
    public List<Position> getPositionsByKeyAndDate(String name, LocalDate startDate, LocalDate endDate) {
        return List.of();
    }
}
