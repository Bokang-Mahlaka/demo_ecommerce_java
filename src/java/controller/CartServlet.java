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
        if (productIdStr == null) {
            response.sendRedirect("cart.jsp");
            return;
        }
        int productId = Integer.parseInt(productIdStr);

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
