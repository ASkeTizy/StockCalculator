package org.example.stockcalc.entity;

import java.util.ArrayList;
import java.util.List;

public class Promotion {
    private String name;
    private List<Position> positions;

    public Promotion(String name) {
        this.name = name;
        this.positions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
