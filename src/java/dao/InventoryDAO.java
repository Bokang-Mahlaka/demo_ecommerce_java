package dao;

import model.Inventory;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Inventory entity.
 */
public class InventoryDAO {

    public void addInventory(Inventory inventory) throws SQLException {
        String sql = "INSERT INTO Inventory (product_id, stock_level) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inventory.getProductId());
            stmt.setInt(2, inventory.getStockLevel());
            stmt.executeUpdate();
        }
    }

    public Inventory getInventoryByProductId(int productId) throws SQLException {
        String sql = "SELECT * FROM Inventory WHERE product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Inventory inventory = new Inventory();
                    inventory.setProductId(rs.getInt("product_id"));
                    inventory.setStockLevel(rs.getInt("stock_level"));
                    return inventory;
                }
            }
        }
        return null;
    }

    public List<Inventory> getAllInventory() throws SQLException {
        List<Inventory> inventoryList = new ArrayList<>();
        String sql = "SELECT * FROM Inventory";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Inventory inventory = new Inventory();
                inventory.setProductId(rs.getInt("product_id"));
                inventory.setStockLevel(rs.getInt("stock_level"));
                inventoryList.add(inventory);
            }
        }
        return inventoryList;
    }

    public void updateInventory(Inventory inventory) throws SQLException {
        String sql = "UPDATE Inventory SET stock_level = ? WHERE product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inventory.getStockLevel());
            stmt.setInt(2, inventory.getProductId());
            stmt.executeUpdate();
        }
    }

    public void deleteInventory(int productId) throws SQLException {
        String sql = "DELETE FROM Inventory WHERE product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
    }
}
