
package model;

/**
 * Represents a product in the TrendSphere catalog.
 */
public class Product {
    private int id;
    private String name;
    private String type;
    private String size;
    private String color;
    private double price;
    private int stock;
    private String currency;
    private String imageUrl;
    private boolean ecoFriendly;

    public Product() {
    }

    public Product(int id, String name, String type, String size, String color, double price, int stock, String currency, String imageUrl, boolean ecoFriendly) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.color = color;
        this.price = price;
        this.stock = stock;
        this.currency = currency;
        this.imageUrl = imageUrl;
        this.ecoFriendly = ecoFriendly;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isEcoFriendly() {
        return ecoFriendly;
    }

    public void setEcoFriendly(boolean ecoFriendly) {
        this.ecoFriendly = ecoFriendly;
    }
}
