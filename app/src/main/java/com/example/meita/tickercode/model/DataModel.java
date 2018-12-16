package com.example.meita.tickercode.model;

import android.provider.ContactsContract;

public class DataModel {
    String symbol, name, region, matchScore;

    public DataModel(String symbol, String name, String region, String matchScore) {
        this.symbol = symbol;
        this.name = name;
        this.region = region;
        this.matchScore = matchScore;
    }

    public DataModel() {

    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(String matchScore) {
        this.matchScore = matchScore;
    }
}
