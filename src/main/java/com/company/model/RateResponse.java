package com.company.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RateResponse {

    public String base;
    @JsonProperty("rates")
    public Rate rate;

    public String getBase() {
        return base;
    }

    @Override
    public String toString() {
        return "Текущий курс " + base + ": " + Math.ceil(rate.getUAH()) + " UAH" + " Дата: " + getCurrentDate();
    }

    private String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return formatter.format(LocalDateTime.now());
    }
}
