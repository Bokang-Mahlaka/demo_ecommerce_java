package dao;

import model.Order;
import model.OrderItem;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DAO class for Order entity.
 */
public class OrderDAO {

    public void addOrder(Order order) throws SQLException {
        String orderSql = "INSERT INTO Orders (user_id, total_price, order_date) VALUES (?, ?, ?)";
        String orderItemSql = "INSERT INTO OrderItems (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement orderStmt = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
                orderStmt.setInt(1, order.getUserId());
                orderStmt.setDouble(2, order.getTotalPrice());
                orderStmt.setTimestamp(3, new Timestamp(order.getOrderDate().getTime()));
                orderStmt.executeUpdate();

                try (ResultSet generatedKeys = orderStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int orderId = generatedKeys.getInt(1);
                        order.setOrderId(orderId);

                        try (PreparedStatement itemStmt = conn.prepareStatement(orderItemSql)) {
                            for (OrderItem item : order.getOrderItems()) {
                                itemStmt.setInt(1, orderId);
                                itemStmt.setInt(2, item.getProductId());
                                itemStmt.setInt(3, item.getQuantity());
                                itemStmt.setDouble(4, item.getPrice());
                                itemStmt.addBatch();
                            }
                            itemStmt.executeBatch();
                        }
                    } else {
                        throw new SQLException("Creating order failed, no ID obtained.");
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    public Order getOrderById(int orderId) throws SQLException {
        String orderSql = "SELECT * FROM Orders WHERE order_id = ?";
        String itemsSql = "SELECT * FROM OrderItems WHERE order_id = ?";
        Order order = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement orderStmt = conn.prepareStatement(orderSql);
             PreparedStatement itemsStmt = conn.prepareStatement(itemsSql)) {
            orderStmt.setInt(1, orderId);
            try (ResultSet rs = orderStmt.executeQuery()) {
                if (rs.next()) {
                    order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setUserId(rs.getInt("user_id"));
                    order.setTotalPrice(rs.getDouble("total_price"));
                    order.setOrderDate(rs.getTimestamp("order_date"));
                }
            }

            if (order != null) {
                itemsStmt.setInt(1, orderId);
                try (ResultSet rsItems = itemsStmt.executeQuery()) {
                    List<OrderItem> items = new ArrayList<>();
                    while (rsItems.next()) {
                        OrderItem item = new OrderItem();
                        item.setOrderItemId(rsItems.getInt("order_item_id"));
                        item.setOrderId(rsItems.getInt("order_id"));
                        item.setProductId(rsItems.getInt("product_id"));
                        item.setQuantity(rsItems.getInt("quantity"));
                        item.setPrice(rsItems.getDouble("price"));
                        items.add(item);
                    }
                    order.setOrderItems(items);
                }
            }
        }
        return order;
    }

    // Additional methods like getAllOrders, updateOrder, deleteOrder can be added similarly
}
