package com.mercury.SpringBootRestDemo.bean;

import java.time.LocalDate;

public class DailySales {
    private LocalDate date;
    private int total;


    public DailySales() {
    }

    public DailySales(LocalDate date, int total) {
        this.date = date;
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "DailySales{" +
                "date=" + date +
                ", total=" + total +
                '}';
    }
}
