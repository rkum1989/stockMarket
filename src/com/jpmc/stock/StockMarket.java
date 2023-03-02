package com.jpmc.stock;

import com.jpmc.stock.model.Stock;
import com.jpmc.stock.model.StockDetails;
import org.jetbrains.annotations.NotNull;

import java.time.*;
import java.util.*;

public class StockMarket {
    private static final String TEA = "TEA";
    private static final String POP = "POP";
    private static final String ALE = "ALE";
    private static final String GIN = "GIN";
    private static final String JOE = "JOE";
    public static void main(String[] args) {
        StockDetails details = new StockDetails(100, 100,"BUY", Instant.now());
        StockDetails details2 = new StockDetails(100, 100,"BUY", Instant.parse("2023-03-02T12:15:00.00Z"));
        List<StockDetails> list = new ArrayList<>();
        list.add(details);
        list.add(details2);
        Stock tea = new Stock(TEA, "Common", 0, 1, 100, list);
        Stock pop = new Stock(POP, "Common", 9, 1, 100, list);
        Stock ale = new Stock(ALE, "Common", 23, 1, 60, list);
        Stock gin = new Stock(GIN, "Preferred", 8, 2, 100, list);
        Stock joe = new Stock(JOE, "Common", 13, 1, 250, list);

        Map<String, Stock> stocks = new HashMap<>();
        stocks.put(TEA, tea);
        stocks.put(POP, pop);
        stocks.put(ALE, ale);
        stocks.put(GIN, gin);
        stocks.put(JOE, joe);

        System.out.println("Please Enter 1. Calculate Dividend yield\n2. Calculate P/E Ratio\n3. Record trade date\n4. Volume weighted Stock price for last 15 minutes\n5. GBCE geometric mean");
        Scanner input=new Scanner(System.in);
        int selection = input.nextInt();
        while (selection<4)
        {
            switch(selection){
                case 1:
                    System.out.print("please enter stock symbol");
                    String stockSymbol = input.next();
                    if(!isValidStockSymbol(stocks, stockSymbol)) break;
                    System.out.print("please enter stock price");
                    double stockPrice = input.nextDouble();
                    if (!isValidPrice(stockPrice)) break;
                    calculateDividendYield(stocks.get(stockSymbol.toUpperCase()), stockPrice);
                    break;

                case 2:
                    System.out.print("please enter stock symbol");
                    stockSymbol = input.next();
                    if(!isValidStockSymbol(stocks, stockSymbol)) break;
                    System.out.print("please enter stock price");
                    stockPrice = input.nextDouble();
                    if (!isValidPrice(stockPrice)) break;
                    double dividend = calculateDividendYield(stocks.get(stockSymbol.toUpperCase()), stockPrice);
                    calculatePERatio(dividend, stockPrice);
                    break;

                case 3:
                    System.out.print("please enter stock symbol");
                    stockSymbol = input.next();
                    if(!isValidStockSymbol(stocks, stockSymbol)) break;
                    System.out.print("please enter quantity of shares");
                    int qty = input.nextInt();
                    System.out.print("please enter buy or sell");
                    String tradeIndicator = input.next();
                    System.out.print("please enter stock price");
                    stockPrice = input.nextDouble();
                    if(!isValidStockSymbol(stocks, stockSymbol)) break;
                    StockDetails temp = new StockDetails(stockPrice, qty, tradeIndicator, Instant.now());
                    recordTrade(stocks, stockSymbol, temp);
                    break;

                case 4:
                    System.out.print("please enter stock symbol");
                    stockSymbol = input.next();
                    if(!isValidStockSymbol(stocks, stockSymbol)) break;
                    calculateVolumeWeightedStock(stocks, stockSymbol);
                    break;

                case 5:
                    calculateGBCE(stocks);
                    break;
            }
            System.out.println("Please Enter 1. Calculate Dividend yield\n2. Calculate P/E Ratio\n3. Record trade date\n4. Volume weighted Stock price for last 15 minutes\n5. GBCE geometric mean");
            selection = input.nextInt();
        }
    }

    private static double calculateDividendYield(@NotNull Stock stock, Double stockPrice){
        double dividend;
        if(stock.getType().equals("Common")){
            dividend = (stock.getLastDividend()/stockPrice);
            System.out.println("common dividend "+ dividend);
        }else{
            dividend = (stock.getFixedDividend()*stock.getParValue())/(stockPrice*100);
            System.out.println("preferred dividend "+ dividend);
        }

        return dividend;
    }

    private static void calculatePERatio(double dividend, double stockPrice){
        System.out.println("PE Ratio is " +stockPrice/dividend);
    }

    private static void recordTrade(@NotNull Map<String, Stock> tradeRecords, String stockSymbol, StockDetails stockDetails){
        System.out.println("before recording trade "+ tradeRecords);
        tradeRecords.get(stockSymbol.toUpperCase()).getStockDetails().add(stockDetails);
        System.out.println("After recording trades " + tradeRecords);
    }

    private static void calculateVolumeWeightedStock(@NotNull Map<String, Stock> tradeRecords, String stockSymbol){
        Collection<StockDetails> col = tradeRecords.get(stockSymbol).getStockDetails();
        Instant start = Instant.now();
        double tradePriceMulQtySum = 0;
        int qty = 0;
        for (StockDetails stock : col){
            Duration timeElapsed = Duration.between(start, stock.getTimeStamp());
            long minutes = (timeElapsed.toMillis()/1000)/60;
            System.out.println(minutes);
            if(minutes > -15 && minutes <= 0){
                tradePriceMulQtySum = tradePriceMulQtySum + (stock.getTradePrice()*stock.getQuantity());
                qty = qty + stock.getQuantity();
            }
        }
        System.out.println("VolumeWeightedStock in last 15 minutes " + tradePriceMulQtySum/qty);
    }

    private static void calculateGBCE(@NotNull Map<String, Stock> tradeRecords){
        Collection<Stock> col = tradeRecords.values();
        double price =1.0;
        for(Stock stock: col){
            price = price * stock.getStockDetails().get(0).getTradePrice();
        }
        System.out.println((float) Math.pow(price, (float)1 / col.size()));
    }

    private static boolean isValidStockSymbol(Map<String, Stock> stocks, String stockSymbol){
        if(stocks.containsKey(stockSymbol.toUpperCase())){
            return true;
        }else {
            System.err.println("please enter valid stock symbol");
        }
        return false;
    }

    private static boolean isValidPrice(double stockPrice){
        if (stockPrice > 0 ){
            return true;
        }else{
            System.err.println("please enter valid stock price");
        }
        return false;
    }
}