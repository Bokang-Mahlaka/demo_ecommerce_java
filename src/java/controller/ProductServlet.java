package controller;

import model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet to handle product listing, filtering, and sorting.
 */
@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private List<Product> productList = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        super.init();
        productList.add(new Product(1, "Classic Shirt", "Shirt", "M", "Blue", 49.99, 100, "USD", "images/shirt1.jpg", true));
        productList.add(new Product(2, "Slim Fit Trousers", "Trousers", "L", "Black", 79.99, 50, "USD", "images/trousers1.jpg", false));
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Retrieve filter parameters
        String type = request.getParameter("type");
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        String priceRange = request.getParameter("priceRange");
        String sortBy = request.getParameter("sortBy");

        List<Product> filteredProducts = filterProducts(type, size, color, priceRange);
        sortProducts(filteredProducts, sortBy);

        request.setAttribute("products", filteredProducts);
        request.getRequestDispatcher("/productCatalog.jsp").forward(request, response);
    }

    private List<Product> filterProducts(String type, String size, String color, String priceRange) {
        List<Product> filtered = new ArrayList<>();
        for (Product p : productList) {
            if ((type == null || type.isEmpty() || p.getType().equalsIgnoreCase(type)) &&
                (size == null || size.isEmpty() || p.getSize().equalsIgnoreCase(size)) &&
                (color == null || color.isEmpty() || p.getColor().equalsIgnoreCase(color)) &&
                (priceInRange(p.getPrice(), priceRange))) {
                filtered.add(p);
            }
        }
        return filtered;
    }

    private boolean priceInRange(double price, String priceRange) {
        if (priceRange == null || priceRange.isEmpty()) {
            return true;
        }
        String[] parts = priceRange.split("-");
        if (parts.length != 2) return true;
        try {
            double min = Double.parseDouble(parts[0]);
            double max = Double.parseDouble(parts[1]);
            return price >= min && price <= max;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private void sortProducts(List<Product> products, String sortBy) {
        if (sortBy == null) return;
        switch (sortBy) {
            case "priceAsc":
                products.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
                break;
            case "priceDesc":
                products.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
                break;
            case "newest":
               
                products.sort((p1, p2) -> Integer.compare(p2.getId(), p1.getId()));
                break;
            default:
                break;
        }
    }
}
