/**
 * Represents currency details and conversion rates.
 */

 package model;
 
public class Currency {
    private String code; // e.g., USD, LSL
    private String symbol; // e.g., $, M
    private double conversionRateToUSD;

    public Currency() {
    }

    public Currency(String code, String symbol, double conversionRateToUSD) {
        this.code = code;
        this.symbol = symbol;
        this.conversionRateToUSD = conversionRateToUSD;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getConversionRateToUSD() {
        return conversionRateToUSD;
    }

    public void setConversionRateToUSD(double conversionRateToUSD) {
        this.conversionRateToUSD = conversionRateToUSD;
    }
}
