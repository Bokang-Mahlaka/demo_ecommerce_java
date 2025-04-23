package dao;

import model.Product;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Product entity.
 */
public class ProductDAO {

    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Products (name, type, size, color, price, stock, currency, image_url, eco_friendly) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getType());
            stmt.setString(3, product.getSize());
            stmt.setString(4, product.getColor());
            stmt.setDouble(5, product.getPrice());
            stmt.setInt(6, product.getStock());
            stmt.setString(7, product.getCurrency());
            stmt.setString(8, product.getImageUrl());
            stmt.setBoolean(9, product.isEcoFriendly());
            stmt.executeUpdate();
        }
    }

    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM Products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToProduct(rs);
                }
            }
        }
        return null;
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                products.add(mapResultSetToProduct(rs));
            }
        }
        return products;
    }

    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE Products SET name = ?, type = ?, size = ?, color = ?, price = ?, stock = ?, currency = ?, image_url = ?, eco_friendly = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getType());
            stmt.setString(3, product.getSize());
            stmt.setString(4, product.getColor());
            stmt.setDouble(5, product.getPrice());
            stmt.setInt(6, product.getStock());
            stmt.setString(7, product.getCurrency());
            stmt.setString(8, product.getImageUrl());
            stmt.setBoolean(9, product.isEcoFriendly());
            stmt.setInt(10, product.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM Products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setType(rs.getString("type"));
        product.setSize(rs.getString("size"));
        product.setColor(rs.getString("color"));
        product.setPrice(rs.getDouble("price"));
        product.setStock(rs.getInt("stock"));
        product.setCurrency(rs.getString("currency"));
        product.setImageUrl(rs.getString("image_url"));
        product.setEcoFriendly(rs.getBoolean("eco_friendly"));
        return product;
    }
}
