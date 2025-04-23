package dao;

import model.Invoice;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Invoice entity.
 */
public class InvoiceDAO {

    public void addInvoice(Invoice invoice) throws SQLException {
        String sql = "INSERT INTO Invoices (order_id, user_id, total_amount, invoice_date, currency) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, invoice.getOrderId());
            stmt.setInt(2, invoice.getUserId());
            stmt.setDouble(3, invoice.getTotalAmount());
            stmt.setTimestamp(4, new Timestamp(invoice.getInvoiceDate().getTime()));
            stmt.setString(5, invoice.getCurrency());
            stmt.executeUpdate();
        }
    }

    public Invoice getInvoiceById(int invoiceId) throws SQLException {
        String sql = "SELECT * FROM Invoices WHERE invoice_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, invoiceId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setInvoiceId(rs.getInt("invoice_id"));
                    invoice.setOrderId(rs.getInt("order_id"));
                    invoice.setUserId(rs.getInt("user_id"));
                    invoice.setTotalAmount(rs.getDouble("total_amount"));
                    invoice.setInvoiceDate(rs.getTimestamp("invoice_date"));
                    invoice.setCurrency(rs.getString("currency"));
                    return invoice;
                }
            }
        }
        return null;
    }

    public List<Invoice> getAllInvoices() throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM Invoices";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setOrderId(rs.getInt("order_id"));
                invoice.setUserId(rs.getInt("user_id"));
                invoice.setTotalAmount(rs.getDouble("total_amount"));
                invoice.setInvoiceDate(rs.getTimestamp("invoice_date"));
                invoice.setCurrency(rs.getString("currency"));
                invoices.add(invoice);
            }
        }
        return invoices;
    }

    public void updateInvoice(Invoice invoice) throws SQLException {
        String sql = "UPDATE Invoices SET order_id = ?, user_id = ?, total_amount = ?, invoice_date = ?, currency = ? WHERE invoice_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, invoice.getOrderId());
            stmt.setInt(2, invoice.getUserId());
            stmt.setDouble(3, invoice.getTotalAmount());
            stmt.setTimestamp(4, new Timestamp(invoice.getInvoiceDate().getTime()));
            stmt.setString(5, invoice.getCurrency());
            stmt.setInt(6, invoice.getInvoiceId());
            stmt.executeUpdate();
        }
    }

    public void deleteInvoice(int invoiceId) throws SQLException {
        String sql = "DELETE FROM Invoices WHERE invoice_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, invoiceId);
            stmt.executeUpdate();
        }
    }
}
