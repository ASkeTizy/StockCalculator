package org.example.stockcalc.entity;

import java.time.LocalDate;

public class Dividend {
    String name;
    private Double coefficient;
    LocalDate date;

    public Dividend(String name,Double coefficient, LocalDate date) {
        this.coefficient = coefficient;
        this.date = date;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
