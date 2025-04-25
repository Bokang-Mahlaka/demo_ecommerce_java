<%@ page import="java.util.List" %>
<%@ page import="model.Order" %>
<%@ page import="dao.OrderDAO" %>
<%
    OrderDAO orderDAO = new OrderDAO();
    List<Order> orders = orderDAO.getAllOrders();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Orders Management | TrendSphere</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            padding: 0;
            margin: 0;
        }
        
        .admin-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        
        .page-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            padding-bottom: 15px;
            border-bottom: 1px solid var(--border);
        }
        
        .page-title {
            font-size: 1.8rem;
            color: var(--primary);
            font-weight: 700;
            margin: 0;
        }
        
        .orders-table-container {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
            overflow: hidden;
        }
        
        .orders-table {
            width: 100%;
            border-collapse: collapse;
        }
        
        .orders-table thead {
            background-color: var(--primary);
            color: white;
        }
        
        .orders-table th {
            padding: 15px;
            text-align: left;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 0.8rem;
            letter-spacing: 0.5px;
        }
        
        .orders-table td {
            padding: 15px;
            border-bottom: 1px solid var(--border);
        }
        
        .orders-table tbody tr:last-child td {
            border-bottom: none;
        }
        
        .orders-table tbody tr:hover {
            background-color: rgba(231, 76, 60, 0.05);
        }
        
        .price-cell {
            font-weight: 600;
            color: var(--primary);
        }
        
        .action-link {
            display: inline-block;
            padding: 8px 15px;
            background-color: var(--accent);
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-size: 0.85rem;
            font-weight: 600;
            transition: all 0.3s;
        }
        
        .action-link:hover {
            background-color: #c0392b;
            transform: translateY(-1px);
        }
        
        .empty-state {
            text-align: center;
            padding: 40px;
            color: var(--text-light);
            font-style: italic;
        }
        
        /* Status Badges */
        .status-badge {
            display: inline-block;
            padding: 4px 8px;
            border-radius: 12px;
            font-size: 0.75rem;
            font-weight: 600;
            text-transform: uppercase;
        }
        
        .status-processing {
            background-color: #f39c12;
            color: white;
        }
        
        .status-completed {
            background-color: var(--success);
            color: white;
        }
        
        .status-cancelled {
            background-color: var(--accent);
            color: white;
        }
        
        /* Responsive Design */
        @media (max-width: 768px) {
            .admin-container {
                padding: 15px;
            }
            
            .orders-table {
                display: block;
                overflow-x: auto;
            }
            
            .orders-table th, 
            .orders-table td {
                padding: 12px 10px;
                font-size: 0.9rem;
            }
            
            .action-link {
                padding: 6px 10px;
                font-size: 0.8rem;
            }
        }
    </style>
</head>
<body>
    <div class="admin-container">
        <div class="page-header">
            <h1 class="page-title">Orders Management</h1>
            
        </div>

        <div class="orders-table-container">
            <table class="orders-table">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>User ID</th>
                        <th>Total</th>
                        <th>Date</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (orders != null && !orders.isEmpty()) {
                        for (Order order : orders) { %>
                            <tr>
                                <td><%= order.getOrderId() %></td>
                                <td><%= order.getUserId() %></td>
                                <td class="price-cell">LSL <%= String.format("%.2f", order.getTotalPrice()) %></td>
                                <td><%= order.getOrderDate() %></td>
                                <td>
                                    <span class="status-badge status-processing">Processing</span>
                                </td>
                                <td>
                                    <a href="<%= request.getContextPath() %>/orderDetails.jsp?orderId=<%=order.getOrderId()%>" 
                                       class="action-link">View Details</a>
                                </td>
                            </tr>
                    <%  }
                    } else { %>
                        <tr>
                            <td colspan="6" class="empty-state">No orders found</td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
    <%@ include file="../footer.jsp" %>
</body>
</html>