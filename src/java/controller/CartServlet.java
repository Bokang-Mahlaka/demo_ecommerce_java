package controller;

import dao.ProductDAO;
import model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet to manage shopping cart actions (add, remove, update items).
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO();
    }

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
        int productId = -1;
        if (productIdStr != null) {
            productId = Integer.parseInt(productIdStr);
        }

        switch (action) {
            case "add":
                try {
                    Product productToAdd = productDAO.getProductById(productId);
                    if (productToAdd != null) {
                        cart.add(productToAdd);
                    }
                } catch (SQLException e) {
                    throw new ServletException("Error fetching product for cart", e);
                }
                break;
            case "remove":
                // Use traditional for loop to avoid lambda capturing productId
                for (int i = 0; i < cart.size(); i++) {
                    if (cart.get(i).getId() == productId) {
                        cart.remove(i);
                        break;
                    }
                }
                break;
            case "update":
                for (Product p : cart) {
                    String quantityStr = request.getParameter("quantity_" + p.getId());
                    if (quantityStr != null) {
                        try {
                            int quantity = Integer.parseInt(quantityStr);
                            if (quantity > 0) {
                                p.setStock(quantity);
                            }
                        } catch (NumberFormatException e) {
                            // ignore invalid input, keep old quantity
                        }
                    }
                }
                break;
            default:
                break;
        }

        response.sendRedirect("cart.jsp");
    }
}
