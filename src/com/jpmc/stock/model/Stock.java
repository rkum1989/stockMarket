package com.jpmc.stock.model;

import java.util.List;

public class Stock {
    private String stockSymbol;
    private String type;
    private Integer lastDividend;
    private Integer fixedDividend;
    private Integer parValue;

    private List<StockDetails> stockDetails;

    public Stock(String stockSymbol, String type, Integer lastDividend, Integer fixedDividend, Integer parValue, List<StockDetails> stockDetails) {
        this.stockSymbol = stockSymbol;
        this.type = type;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
        this.stockDetails = stockDetails;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(Integer lastDividend) {
        this.lastDividend = lastDividend;
    }

    public Integer getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(Integer fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    public Integer getParValue() {
        return parValue;
    }

    public void setParValue(Integer parValue) {
        this.parValue = parValue;
    }

    public List<StockDetails> getStockDetails() {
        return stockDetails;
    }

    public void setStockDetails(List<StockDetails> stockDetails) {
        this.stockDetails = stockDetails;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Stock{");
        sb.append("stockSymbol='").append(stockSymbol).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", lastDividend=").append(lastDividend);
        sb.append(", fixedDividend=").append(fixedDividend);
        sb.append(", parValue=").append(parValue);
        sb.append(", stockDetails=").append(stockDetails);
        sb.append('}');
        return sb.toString();
    }
}
