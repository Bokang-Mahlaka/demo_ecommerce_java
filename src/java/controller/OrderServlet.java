package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Order;
import model.OrderItem;
import model.Invoice;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Servlet to handle order placement, invoice generation, and email sending.
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    // For demonstration, using in-memory order list. In real app, connect to DB.
    private List<Order> orders = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process order placement
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // For demo, assume order details come from request parameters or session cart
        // Here, we create a dummy order for demonstration
        int userId = ((model.User) session.getAttribute("user")).getId();

        List<OrderItem> orderItems = new ArrayList<>();
        // In real app, populate orderItems from cart/session/request
        // Example dummy item:
orderItems.add(new OrderItem(0, 0, 1, 2, 49.99));

        double totalPrice = 0;
        for (OrderItem item : orderItems) {
            totalPrice += item.getPrice() * item.getQuantity();
        }

        Order newOrder = new Order(orders.size() + 1, userId, orderItems, totalPrice, new Date());
        orders.add(newOrder);

        // Generate invoice
        Invoice invoice = new Invoice(orders.size(), newOrder.getOrderId(), userId, orderItems, totalPrice, new Date(), "USD");

        // TODO: Send invoice via email (requires email utility integration)

        // Set order and invoice in request and forward to confirmation page
        request.setAttribute("order", newOrder);
        request.setAttribute("invoice", invoice);
        request.getRequestDispatcher("/orderConfirmation.jsp").forward(request, response);
    }
}
