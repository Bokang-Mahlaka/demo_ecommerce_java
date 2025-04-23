/**
 * Represents the inventory management for products.
 */
 package model;
public class Inventory {
    private int productId;
    private int stockLevel;

    public Inventory() {
    }

    public Inventory(int productId, int stockLevel) {
        this.productId = productId;
        this.stockLevel = stockLevel;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }
}
