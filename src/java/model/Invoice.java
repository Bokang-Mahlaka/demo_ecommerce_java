package model;


import java.util.Date;
import java.util.List;


public class Invoice {
    private int invoiceId;
    private int orderId;
    private int userId;
    private List<OrderItem> items;
    private double totalAmount;
    private Date invoiceDate;
    private String currency;

    public Invoice() {
    }

    public Invoice(int invoiceId, int orderId, int userId, List<OrderItem> items, double totalAmount, Date invoiceDate, String currency) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.invoiceDate = invoiceDate;
        this.currency = currency;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
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

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
