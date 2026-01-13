package org.example.stockcalc.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Position {
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer count;

    public Position(String type, LocalDate startDate, LocalDate endDate, Integer count) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
