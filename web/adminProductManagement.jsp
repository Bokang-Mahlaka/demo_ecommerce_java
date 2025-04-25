<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="dao.ProductDAO" %>
<%
dao.ProductDAO productDAO = new dao.ProductDAO();
java.util.List<model.Product> products = null;
try {
products = productDAO.getAllProducts();
} catch (Exception e) {
products = new java.util.ArrayList<>();
}
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Product Management | TrendSphere</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        :root {
            --primary: #2c3e50;
            --secondary: #34495e;
            --accent: #e74c3c;
            --light: #ecf0f1;
            --dark: #2c3e50;
            --text: #333;
            --text-light: #7f8c8d;
            --success: #27ae60;
            --warning: #f39c12;
            --info: #3498db;
            --border: #e0e0e0;
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
            max-width: 1400px;
            margin: 0 auto;
            padding: 30px 20px;
        }
        
        .admin-header {
            margin-bottom: 30px;
            padding-bottom: 20px;
            border-bottom: 1px solid var(--light);
        }
        
        .admin-header h1 {
            color: var(--primary);
            font-size: 2.2rem;
            margin-bottom: 10px;
        }
        
        .section {
            background: white;
            border-radius: 8px;
            padding: 25px;
            margin-bottom: 30px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
        }
        
        .section h2 {
            color: var(--primary);
            font-size: 1.5rem;
            margin-top: 0;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 1px solid var(--light);
        }
        
        /* Form Styles */
        .form-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 20px;
        }
        
        .form-group {
            margin-bottom: 15px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: var(--secondary);
        }
        
        .form-control {
            width: 100%;
            padding: 12px 15px;
            border: 1px solid var(--border);
            border-radius: 4px;
            font-size: 1rem;
            transition: all 0.3s;
        }
        
        .form-control:focus {
            outline: none;
            border-color: var(--accent);
            box-shadow: 0 0 0 3px rgba(231, 76, 60, 0.1);
        }
        
        .checkbox-group {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        
        .checkbox-group input {
            width: 18px;
            height: 18px;
            accent-color: var(--accent);
        }
        
        .form-actions {
            margin-top: 20px;
            grid-column: 1 / -1;
        }
        
        /* Table Styles */
        .products-table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            border-radius: 8px;
            overflow: hidden;
        }
        
        .products-table thead {
            background-color: var(--primary);
            color: white;
        }
        
        .products-table th {
            padding: 15px;
            text-align: left;
            font-weight: 600;
        }
        
        .products-table td {
            padding: 15px;
            border-bottom: 1px solid var(--border);
        }
        
        .products-table tbody tr:last-child td {
            border-bottom: none;
        }
        
        .products-table tbody tr:hover {
            background-color: rgba(231, 76, 60, 0.05);
        }
        
        /* Buttons */
        .btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 0.9rem;
            font-weight: 600;
            text-align: center;
            text-decoration: none;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: all 0.3s;
        }
        
        .btn-primary {
            background-color: var(--accent);
            color: white;
        }
        
        .btn-primary:hover {
            background-color: #c0392b;
        }
        
        .btn-secondary {
            background-color: var(--secondary);
            color: white;
        }
        
        .btn-secondary:hover {
            background-color: var(--dark);
        }
        
        .btn-danger {
            background-color: #e74c3c;
            color: white;
        }
        
        .btn-danger:hover {
            background-color: #c0392b;
        }
        
        .btn-sm {
            padding: 8px 12px;
            font-size: 0.8rem;
        }
        
        .action-buttons {
            display: flex;
            gap: 8px;
        }
        
        /* Modal/Edit Form */
        .modal-overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 1000;
            align-items: center;
            justify-content: center;
            padding: 20px;
            overflow-y: auto;
        }
        
        .modal-container {
            background-color: white;
            border-radius: 8px;
            width: 100%;
            max-width: 900px;
            max-height: 90vh;
            overflow-y: auto;
            box-shadow: 0 5px 20px rgba(0,0,0,0.2);
        }
        
        .modal-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            border-bottom: 1px solid var(--light);
        }
        
        .modal-title {
            font-size: 1.5rem;
            color: var(--primary);
            margin: 0;
        }
        
        .modal-close {
            background: none;
            border: none;
            font-size: 1.5rem;
            cursor: pointer;
            color: var(--text-light);
        }
        
        .modal-body {
            padding: 20px;
        }
        
        /* Responsive Design */
        @media (max-width: 1200px) {
            .form-grid {
                grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            }
        }
        
        @media (max-width: 768px) {
            .admin-container {
                padding: 15px;
            }
            
            .section {
                padding: 20px;
            }
            
            .products-table {
                display: block;
                overflow-x: auto;
            }
            
            .action-buttons {
                flex-direction: column;
                gap: 5px;
            }
            
            .btn-sm {
                width: 100%;
            }
        }
    </style>
    <script>
        function populateEditForm(id, name, type, size, color, price, stock, currency, ecoFriendly) {
            document.getElementById('editId').value = id;
            document.getElementById('editName').value = name;
            document.getElementById('editType').value = type;
            document.getElementById('editSize').value = size;
            document.getElementById('editColor').value = color;
            document.getElementById('editPrice').value = price;
            document.getElementById('editStock').value = stock;
            document.getElementById('editCurrency').value = currency;
            document.getElementById('editEcoFriendly').checked = ecoFriendly;
            document.getElementById('editFormContainer').style.display = 'flex';
        }

        function hideEditForm() {
            document.getElementById('editFormContainer').style.display = 'none';
        }
    </script>
</head>
<body>
    <div class="admin-container">
        <div class="admin-header">
            <h1>Product Management</h1>
            <a href="adminDashboard.jsp">Go Back to Admin Dashboard</a><br/><br/>
        </div>

        <div class="section">
            <h2>Add New Product</h2>
            <form action="products" method="post" enctype="multipart/form-data" class="form-grid">
                <input type="hidden" name="action" value="add" />
                
                <div class="form-group">
                    <label for="name">Product Name</label>
                    <input type="text" id="name" name="name" class="form-control" required />
                </div>
                
                <div class="form-group">
                    <label for="type">Type</label>
                    <input type="text" id="type" name="type" class="form-control" />
                </div>
                
                <div class="form-group">
                    <label for="size">Size</label>
                    <input type="text" id="size" name="size" class="form-control" />
                </div>
                
                <div class="form-group">
                    <label for="color">Color</label>
                    <input type="text" id="color" name="color" class="form-control" />
                </div>
                
                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="number" step="0.01" id="price" name="price" class="form-control" required />
                </div>
                
                <div class="form-group">
                    <label for="stock">Stock Quantity</label>
                    <input type="number" id="stock" name="stock" class="form-control" required />
                </div>
                
                <div class="form-group">
                    <label for="currency">Currency</label>
                    <select id="currency" name="currency" class="form-control">
                        <option value="USD">USD ($)</option>
                        <option value="LSL">LSL (L)</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <div class="checkbox-group">
                        <input type="checkbox" id="ecoFriendly" name="ecoFriendly" value="true" />
                        <label for="ecoFriendly">Eco Friendly</label>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="imageFile">Product Image</label>
                    <input type="file" id="imageFile" name="imageFile" class="form-control" accept="image/*" />
                </div>
                
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Add Product</button>
                </div>
            </form>
        </div>

        <div class="section">
            <h2>Product List</h2>
            <div style="overflow-x: auto;">
                <table class="products-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Type</th>
                            <th>Size</th>
                            <th>Color</th>
                            <th>Price</th>
                            <th>Stock</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Product p : products) { %>
                            <tr>
                                <td><%= p.getId() %></td>
                                <td><%= p.getName() %></td>
                                <td><%= p.getType() %></td>
                                <td><%= p.getSize() %></td>
                                <td><%= p.getColor() %></td>
                                <td><%= p.getCurrency() %> <%= String.format("%.2f", p.getPrice()) %></td>
                                <td><%= p.getStock() %></td>
                                <td class="action-buttons">
                                    <button type="button" class="btn btn-secondary btn-sm" 
                                        onclick="populateEditForm('<%=p.getId()%>', '<%=p.getName()%>', '<%=p.getType()%>', '<%=p.getSize()%>', '<%=p.getColor()%>', '<%=p.getPrice()%>', '<%=p.getStock()%>', '<%=p.getCurrency()%>', <%=p.isEcoFriendly()%>)">
                                        Edit
                                    </button>
                                    <form action="products" method="post" style="display:inline;">
                                        <input type="hidden" name="action" value="delete" />
                                        <input type="hidden" name="id" value="<%=p.getId()%>" />
                                        <button type="submit" class="btn btn-danger btn-sm" 
                                            onclick="return confirm('Are you sure you want to delete this product?');">
                                            Delete
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Edit Product Modal -->
    <div id="editFormContainer" class="modal-overlay">
        <div class="modal-container">
            <div class="modal-header">
                <h2 class="modal-title">Edit Product</h2>
                <button type="button" class="modal-close" onclick="hideEditForm()">&times;</button>
            </div>
            <div class="modal-body">
                <form action="products" method="post" enctype="multipart/form-data" class="form-grid">
                    <input type="hidden" name="action" value="update" />
                    <input type="hidden" id="editId" name="id" />
                    
                    <div class="form-group">
                        <label for="editName">Product Name</label>
                        <input type="text" id="editName" name="name" class="form-control" required />
                    </div>
                    
                    <div class="form-group">
                        <label for="editType">Type</label>
                        <input type="text" id="editType" name="type" class="form-control" />
                    </div>
                    
                    <div class="form-group">
                        <label for="editSize">Size</label>
                        <input type="text" id="editSize" name="size" class="form-control" />
                    </div>
                    
                    <div class="form-group">
                        <label for="editColor">Color</label>
                        <input type="text" id="editColor" name="color" class="form-control" />
                    </div>
                    
                    <div class="form-group">
                        <label for="editPrice">Price</label>
                        <input type="number" step="0.01" id="editPrice" name="price" class="form-control" required />
                    </div>
                    
                    <div class="form-group">
                        <label for="editStock">Stock Quantity</label>
                        <input type="number" id="editStock" name="stock" class="form-control" required />
                    </div>
                    
                    <div class="form-group">
                        <label for="editCurrency">Currency</label>
                        <select id="editCurrency" name="currency" class="form-control">
                            <option value="USD">USD ($)</option>
                            <option value="LSL">LSL (L)</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <div class="checkbox-group">
                            <input type="checkbox" id="editEcoFriendly" name="ecoFriendly" value="true" />
                            <label for="editEcoFriendly">Eco Friendly</label>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="editImageFile">Product Image (Leave blank to keep current)</label>
                        <input type="file" id="editImageFile" name="imageFile" class="form-control" accept="image/*" />
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Update Product</button>
                        <button type="button" class="btn btn-secondary" onclick="hideEditForm()">Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>