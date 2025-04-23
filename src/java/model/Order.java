/**
 * Represents a customer order in the TrendSphere system.
 */
package model;
import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private int userId;
    private List<OrderItem> orderItems;
    private double totalPrice;
    private Date orderDate;

    public Order() {
    }

    public Order(int orderId, int userId, List<OrderItem> orderItems, double totalPrice, Date orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
