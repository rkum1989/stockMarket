package com.jpmc.stock.model;

import java.time.Instant;
import java.util.Collection;

public class StockDetails{
    private double tradePrice;
    private int quantity;
    private String tradeIndicator;
    private Instant timeStamp;

    public StockDetails(double tradePrice, int quantity, String tradeIndicator, Instant timeStamp) {
        this.tradePrice = tradePrice;
        this.quantity = quantity;
        this.tradeIndicator = tradeIndicator;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StockDetails{");
        sb.append(", tradePrice=").append(tradePrice);
        sb.append(", quantity=").append(quantity);
        sb.append(", tradeIndicator='").append(tradeIndicator).append('\'');
        sb.append(", timeStamp=").append(timeStamp);
        sb.append('}');
        return sb.toString();
    }

    public double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTradeIndicator() {
        return tradeIndicator;
    }

    public void setTradeIndicator(String tradeIndicator) {
        this.tradeIndicator = tradeIndicator;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }
}
