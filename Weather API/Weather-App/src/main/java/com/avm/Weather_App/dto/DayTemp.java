package com.avm.Weather_App.dto;

import java.time.LocalDate;

public class DayTemp {

    private String date;
    private Double minTemp;
    private Double avgTemp;
    private Double maxTemp;

    public DayTemp() {
    }

    public DayTemp(String date, Double minTemp, Double avgTemp, Double maxTemp) {
        this.date = date;
        this.minTemp = minTemp;
        this.avgTemp = avgTemp;
        this.maxTemp = maxTemp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public Double getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(Double avgTemp) {
        this.avgTemp = avgTemp;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }
}
