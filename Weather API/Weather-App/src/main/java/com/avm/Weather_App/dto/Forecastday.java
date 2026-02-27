package com.avm.Weather_App.dto;

import java.util.ArrayList;

public class Forecastday  {
    public String date;
    public int date_epoch;
    public Day day;
    public Astro astro;
    public ArrayList<Hour> hour;

    public Forecastday() {
    }

    public Forecastday(String date, int date_epoch, Day day, Astro astro, ArrayList<Hour> hour) {
        this.date = date;
        this.date_epoch = date_epoch;
        this.day = day;
        this.astro = astro;
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDate_epoch() {
        return date_epoch;
    }

    public void setDate_epoch(int date_epoch) {
        this.date_epoch = date_epoch;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    public ArrayList<Hour> getHour() {
        return hour;
    }

    public void setHour(ArrayList<Hour> hour) {
        this.hour = hour;
    }
}
