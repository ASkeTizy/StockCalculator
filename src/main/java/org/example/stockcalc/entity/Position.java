package org.example.stockcalc.entity;

import java.time.LocalDateTime;

public class Position {
    private Double price;
    private LocalDateTime date;
    private Integer count;

    public Position(Double price, LocalDateTime date, Integer count) {
        this.price = price;
        this.date = date;
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
