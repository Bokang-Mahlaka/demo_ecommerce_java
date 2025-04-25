package controller;

import dao.InventoryDAO;
import dao.InvoiceDAO;
import dao.OrderDAO;
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
import model.User;
import javax.mail.MessagingException;
import utils.EmailUtil;


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

    private OrderDAO orderDAO;
    private InvoiceDAO invoiceDAO;
    private InventoryDAO inventoryDAO;
    private dao.ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        orderDAO = new OrderDAO();
        invoiceDAO = new InvoiceDAO();
        inventoryDAO = new InventoryDAO();
        productDAO = new dao.ProductDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        String address = request.getParameter("address");
        String paymentMethod = request.getParameter("paymentMethod");

        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            request.setAttribute("error", "Your cart is empty.");
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
            return;
        }

        try {
            List<OrderItem> orderItems = new ArrayList<>();
            double totalPrice = 0.0;

            for (Product product : cart) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(product.getId());
                orderItem.setQuantity(product.getStock());
                orderItem.setPrice(product.getPrice());
                orderItems.add(orderItem);

                totalPrice += product.getPrice() * product.getStock();

                // Update inventory stock
                // Get current inventory stock level
                model.Inventory currentInventory = inventoryDAO.getInventoryByProductId(product.getId());
                int currentStock = currentInventory != null ? currentInventory.getStockLevel() : 0;
                int orderedQuantity = product.getStock();

                // Get current stock from Products table
                int currentProductStock = productDAO.getProductStock(product.getId());

                int updatedStockLevel = currentStock - orderedQuantity;
                if (updatedStockLevel < 0) {
                    updatedStockLevel = 0; // Prevent negative stock
                }

                int updatedProductStock = currentProductStock - orderedQuantity;
                if (updatedProductStock < 0) {
                    updatedProductStock = 0;
                }

                model.Inventory updatedInventory = new model.Inventory(product.getId(), updatedStockLevel);
                inventoryDAO.updateInventory(updatedInventory);

                // Also update stock in Products table
                productDAO.updateProductStock(product.getId(), updatedProductStock);
            }

            Order newOrder = new Order(0, userId, orderItems, totalPrice, new Date());
            orderDAO.addOrder(newOrder);

            // No need to add order items separately as addOrder handles it
            // Remove the loop that calls addOrderItem

            // Use newOrder.getOrderId() to get the generated order ID
            Invoice invoice = new Invoice(0, newOrder.getOrderId(), userId, orderItems, totalPrice, new Date(), cart.get(0).getCurrency());
            invoiceDAO.addInvoice(invoice);
            // No need to capture invoiceId if not used further

            // Send invoice email to user
            try {
                // Send email to logged in user's email
                String toEmail = user.getEmail();
                String subject = "Your Order Invoice - Order #" + newOrder.getOrderId();
                StringBuilder emailContent = new StringBuilder();
                emailContent.append("Dear ").append(user.getUsername()).append(",\n\n");
                emailContent.append("Thank you for your order. Here are your order details:\n");
                emailContent.append("Order ID: ").append(newOrder.getOrderId()).append("\n");
                emailContent.append("Order Date: ").append(newOrder.getOrderDate()).append("\n");
                emailContent.append("Items:\n");
                for (OrderItem item : orderItems) {
                    emailContent.append("- Product ID: ").append(item.getProductId())
                            .append(", Quantity: ").append(item.getQuantity())
                            .append(", Price: ").append(item.getPrice()).append("\n");
                }
                emailContent.append("Total Price: ").append(totalPrice).append("\n\n");
                emailContent.append("Thank you for shopping with us.\n");
                emailContent.append("Best regards,\nTrendSphere Team");

                EmailUtil.sendEmail(toEmail, subject, emailContent.toString());
            } catch (MessagingException me) {
                me.printStackTrace();
                // Optionally log the error or notify admin
            }

            // Clear cart after order placed
            session.removeAttribute("cart");

            request.setAttribute("order", newOrder);
            request.setAttribute("invoice", invoice);
            // Forward to order confirmation page after successful order placement
            request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error processing order", e);
        }
    }
}
