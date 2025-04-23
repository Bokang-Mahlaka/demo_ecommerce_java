package controller;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Invoice;
import model.Order;
import model.OrderItem;
import model.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Servlet to handle order placement, invoice generation, and email sending.
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = ((model.User) session.getAttribute("user")).getId();

        String productIdStr = request.getParameter("productId");
        String quantityStr = request.getParameter("quantity");
        int productId = 0;
        int quantity = 1;

        try {
            productId = Integer.parseInt(productIdStr);
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid product ID or quantity.");
            request.getRequestDispatcher("/productCatalog.jsp").forward(request, response);
            return;
        }

        try {
            Product product = productDAO.getProductById(productId);
            if (product == null) {
                request.setAttribute("error", "Product not found.");
                request.getRequestDispatcher("/productCatalog.jsp").forward(request, response);
                return;
            }

            List<OrderItem> orderItems = new ArrayList<>();
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getId());
            orderItem.setQuantity(quantity);
            orderItem.setPrice(product.getPrice());
            orderItems.add(orderItem);

            double totalPrice = product.getPrice() * quantity;

            Order newOrder = new Order(0, userId, orderItems, totalPrice, new Date());

            Invoice invoice = new Invoice(0, 0, userId, orderItems, totalPrice, new Date(), product.getCurrency());

            request.setAttribute("order", newOrder);
            request.setAttribute("invoice", invoice);
            request.getRequestDispatcher("/orderConfirmation.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error processing order", e);
        }
    }
}
