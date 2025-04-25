<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="model.Order" %>
<%@ page import="model.OrderItem" %>
<%@ page import="dao.OrderDAO" %>
<%
    String orderIdParam = request.getParameter("orderId");
    int orderId = 0;
    if (orderIdParam != null) {
        try {
            orderId = Integer.parseInt(orderIdParam);
        } catch (NumberFormatException e) {
            orderId = 0;
        }
    }

    OrderDAO orderDAO = new OrderDAO();
    Order order = null;
    List<OrderItem> orderItems = null;

    if (orderId > 0) {
        order = orderDAO.getOrderById(orderId);
        orderItems = orderDAO.getOrderItemsByOrderId(orderId);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Order Details - Order #<%= orderId %> | TrendSphere</title>
    <link rel="stylesheet" type="text/css" href="../css/styles.css" />
    <style>
        :root {
            --primary: #2c3e50;
            --secondary: #34495e;
            --accent: #e74c3c;
            --light: #ecf0f1;
            --dark: #2c3e50;
            --text: #333;
            --text-light: #7f8c8d;
            --border: #e0e0e0;
            --success: #27ae60;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f9f9f9;
            color: var(--text);
            line-height: 1.6;
            margin: 0;
            padding: 20px;
        }
        
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
        }
        
        h1 {
            color: var(--primary);
            margin-top: 0;
            padding-bottom: 15px;
            border-bottom: 1px solid var(--border);
        }
        
        .back-link {
            display: inline-block;
            margin-bottom: 20px;
            color: var(--accent);
            text-decoration: none;
            font-weight: 600;
        }
        
        .back-link:hover {
            text-decoration: underline;
        }
        
        .order-info {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 6px;
            margin-bottom: 30px;
        }
        
        .order-info h2 {
            margin-top: 0;
            color: var(--secondary);
        }
        
        .info-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 15px;
        }
        
        .info-item {
            margin-bottom: 10px;
        }
        
        .info-item strong {
            color: var(--secondary);
        }
        
        .order-items {
            margin-top: 30px;
        }
        
        .order-items h2 {
            color: var(--secondary);
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }
        
        th {
            background-color: var(--primary);
            color: white;
            padding: 12px;
            text-align: left;
        }
        
        td {
            padding: 12px;
            border-bottom: 1px solid var(--border);
        }
        
        tr:nth-child(even) {
            background-color: #f8f9fa;
        }
        
        tr:hover {
            background-color: #e9ecef;
        }
        
        .not-found {
            text-align: center;
            padding: 40px;
            color: var(--text-light);
        }
        
        .no-items {
            text-align: center;
            padding: 20px;
            color: var(--text-light);
            background: #f8f9fa;
            border-radius: 6px;
        }
        
        .total-price {
            font-size: 1.2rem;
            font-weight: 600;
            color: var(--primary);
            margin-top: 20px;
            padding-top: 10px;
            border-top: 2px solid var(--border);
        }
        
        @media (max-width: 768px) {
            .info-grid {
                grid-template-columns: 1fr;
            }
            
            .container {
                padding: 20px;
            }
            
            th, td {
                padding: 8px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Order Details - #<%= orderId %></h1>
    <a href="admin/orders" class="back-link">← Back to Orders Management</a>
    
    <% if (order == null) { %>
        <div class="not-found">
            <p>Order not found.</p>
        </div>
    <% } else { %>
        <div class="order-info">
            <h2>Order Information</h2>
            <div class="info-grid">
                <div class="info-item">
                    <strong>Order ID:</strong> <%= order.getOrderId() %>
                </div>
                <div class="info-item">
                    <strong>User ID:</strong> <%= order.getUserId() %>
                </div>
                <div class="info-item">
                    <strong>Order Date:</strong> <%= order.getOrderDate() %>
                </div>
                <div class="info-item">
                    <strong>Status:</strong> <span class="status">Processing</span>
                </div>
            </div>
            <div class="total-price">
                <strong>Total Price:</strong> LSL <%= String.format("%.2f", order.getTotalPrice()) %>
            </div>
        </div>

        <div class="order-items">
            <h2>Order Items</h2>
            <% if (orderItems == null || orderItems.isEmpty()) { %>
                <div class="no-items">
                    <p>No items found for this order.</p>
                </div>
            <% } else { %>
                <table>
                    <thead>
                        <tr>
                            <th>Product ID</th>
                            <th>Product Name</th>
                            <th>Quantity</th>
                            <th>Unit Price</th>
                            <th>Subtotal</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (OrderItem item : orderItems) { %>
                            <tr>
                                <td><%= item.getProductId() %></td>
                                <td>Product Name Here</td>
                                <td><%= item.getQuantity() %></td>
                                <td>LSL <%= String.format("%.2f", item.getPrice()) %></td>
                                <td>LSL <%= String.format("%.2f", item.getPrice() * item.getQuantity()) %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } %>
        </div>
    <% } %>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>