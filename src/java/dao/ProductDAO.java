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

    public List<Product> getFilteredProducts(String type, String size, String color, String priceRange, String sortBy) throws SQLException {
        List<Product> products = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM Products WHERE 1=1");
        List<Object> parameters = new ArrayList<>();

        if (type != null && !type.isEmpty()) {
            sqlBuilder.append(" AND type = ?");
            parameters.add(type);
        }
        if (size != null && !size.isEmpty()) {
            sqlBuilder.append(" AND size = ?");
            parameters.add(size);
        }
        if (color != null && !color.isEmpty()) {
            sqlBuilder.append(" AND color = ?");
            parameters.add(color);
        }
        if (priceRange != null && !priceRange.isEmpty()) {
            // Expecting priceRange format: "min-max" e.g. "10-50" or single value e.g. "12"
            String[] parts = priceRange.split("-");
            try {
                if (parts.length == 2) {
                    double minPrice = Double.parseDouble(parts[0]);
                    double maxPrice = Double.parseDouble(parts[1]);
                    sqlBuilder.append(" AND price BETWEEN ? AND ?");
                    parameters.add(minPrice);
                    parameters.add(maxPrice);
                } else if (parts.length == 1) {
                    double price = Double.parseDouble(parts[0]);
                    sqlBuilder.append(" AND price = ?");
                    parameters.add(price);
                }
            } catch (NumberFormatException e) {
                // Ignore invalid price range format
            }
        }

        // Sorting
        if (sortBy != null) {
            switch (sortBy) {
                case "priceAsc":
                    sqlBuilder.append(" ORDER BY price ASC");
                    break;
                case "priceDesc":
                    sqlBuilder.append(" ORDER BY price DESC");
                    break;
                case "newest":
                    sqlBuilder.append(" ORDER BY id DESC");
                    break;
                default:
                    // No sorting or default sorting
                    break;
            }
        }

        String sql = sqlBuilder.toString();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < parameters.size(); i++) {
                Object param = parameters.get(i);
                if (param instanceof String) {
                    stmt.setString(i + 1, (String) param);
                } else if (param instanceof Double) {
                    stmt.setDouble(i + 1, (Double) param);
                }
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(mapResultSetToProduct(rs));
                }
            }
        }
        return products;
    }

    public List<String> getDistinctTypes() throws SQLException {
        List<String> types = new ArrayList<>();
        String sql = "SELECT DISTINCT type FROM Products WHERE type IS NOT NULL AND type <> ''";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                types.add(rs.getString("type"));
            }
        }
        return types;
    }

    public List<String> getDistinctSizes() throws SQLException {
        List<String> sizes = new ArrayList<>();
        String sql = "SELECT DISTINCT size FROM Products WHERE size IS NOT NULL AND size <> ''";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                sizes.add(rs.getString("size"));
            }
        }
        return sizes;
    }

    public List<String> getDistinctColors() throws SQLException {
        List<String> colors = new ArrayList<>();
        String sql = "SELECT DISTINCT color FROM Products WHERE color IS NOT NULL AND color <> ''";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                colors.add(rs.getString("color"));
            }
        }
        return colors;
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

    public void updateProductStock(int productId, int newStock) throws SQLException {
        String sql = "UPDATE Products SET stock = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newStock);
            stmt.setInt(2, productId);
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

    public int getProductStock(int productId) throws SQLException {
        String sql = "SELECT stock FROM Products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("stock");
                }
            }
        }
        return 0;
    }
}
