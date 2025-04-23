package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet to manage shopping cart actions (add, remove, update items).
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display cart page
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Retrieve or create cart in session
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        String productIdStr = request.getParameter("productId");
        if (productIdStr == null) {
            response.sendRedirect("cart.jsp");
            return;
        }
        int productId = Integer.parseInt(productIdStr);

        switch (action) {
            case "add":
                // For demo, create dummy product. In real app, fetch from DB.
                Product productToAdd = new Product(productId, "Sample Product", "Type", "M", "Color", 49.99, 1, "USD", "images/sample.jpg", true);
                cart.add(productToAdd);
                break;
            case "remove":
                cart.removeIf(p -> p.getId() == productId);
                break;
            case "update":
                String quantityStr = request.getParameter("quantity");
                int quantity = 1;
                try {
                    quantity = Integer.parseInt(quantityStr);
                } catch (NumberFormatException e) {
                    quantity = 1;
                }
                for (Product p : cart) {
                    if (p.getId() == productId) {
                        // For demo, update stock as quantity
                        p.setStock(quantity);
                        break;
                    }
                }
                break;
            default:
                break;
        }

        response.sendRedirect("cart.jsp");
    }
}
