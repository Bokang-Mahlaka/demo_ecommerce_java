package dao;

import model.Currency;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Currency entity.
 */
public class CurrencyDAO {

    public void addCurrency(Currency currency) throws SQLException {
        String sql = "INSERT INTO Currency (code, symbol, conversion_rate_to_usd) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, currency.getCode());
            stmt.setString(2, currency.getSymbol());
            stmt.setDouble(3, currency.getConversionRateToUSD());
            stmt.executeUpdate();
        }
    }

    public Currency getCurrencyByCode(String code) throws SQLException {
        String sql = "SELECT * FROM Currency WHERE code = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Currency currency = new Currency();
                    currency.setCode(rs.getString("code"));
                    currency.setSymbol(rs.getString("symbol"));
                    currency.setConversionRateToUSD(rs.getDouble("conversion_rate_to_usd"));
                    return currency;
                }
            }
        }
        return null;
    }

    public List<Currency> getAllCurrencies() throws SQLException {
        List<Currency> currencies = new ArrayList<>();
        String sql = "SELECT * FROM Currency";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Currency currency = new Currency();
                currency.setCode(rs.getString("code"));
                currency.setSymbol(rs.getString("symbol"));
                currency.setConversionRateToUSD(rs.getDouble("conversion_rate_to_usd"));
                currencies.add(currency);
            }
        }
        return currencies;
    }

    public void updateCurrency(Currency currency) throws SQLException {
        String sql = "UPDATE Currency SET symbol = ?, conversion_rate_to_usd = ? WHERE code = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, currency.getSymbol());
            stmt.setDouble(2, currency.getConversionRateToUSD());
            stmt.setString(3, currency.getCode());
            stmt.executeUpdate();
        }
    }

    public void deleteCurrency(String code) throws SQLException {
        String sql = "DELETE FROM Currency WHERE code = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            stmt.executeUpdate();
        }
    }
}
