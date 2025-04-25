<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Product Catalog</title>
    <style>
        /* Modern Product Catalog Styling */
        * {
          box-sizing: border-box;
          margin: 0;
          padding: 0;
        }
        
        body {
          font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
          line-height: 1.6;
          color: #333;
          background-color: #f8f9fa;
        }
        
        .container {
          max-width: 1200px;
          margin: 0 auto;
          padding: 2rem;
        }
        
        /* Typography */
        h1 {
          font-size: 2.5rem;
          font-weight: 700;
          color: #2d3748;
          margin-bottom: 2rem;
          text-align: center;
        }
        
        h2 {
          font-size: 1.25rem;
          font-weight: 600;
          margin-bottom: 0.5rem;
          color: #2d3748;
        }
        
        /* Filter Form */
        #filterForm {
          background-color: white;
          border-radius: 12px;
          padding: 1.5rem;
          margin-bottom: 2rem;
          box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
          display: grid;
          grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
          gap: 1rem;
          align-items: end;
        }
        
        #filterForm label {
          display: block;
          font-size: 0.875rem;
          font-weight: 500;
          margin-bottom: 0.5rem;
          color: #4a5568;
        }
        
        #filterForm select,
        #filterForm input[type="text"] {
          width: 100%;
          padding: 0.75rem;
          border: 1px solid #e2e8f0;
          border-radius: 8px;
          font-size: 0.875rem;
          background-color: #fff;
          transition: border-color 0.2s, box-shadow 0.2s;
        }
        
        #filterForm select:focus,
        #filterForm input[type="text"]:focus {
          outline: none;
          border-color: #4299e1;
          box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.2);
        }
        
        #filterForm button {
          padding: 0.75rem 1rem;
          background-color: #4299e1;
          color: white;
          border: none;
          border-radius: 8px;
          font-weight: 500;
          cursor: pointer;
          transition: background-color 0.2s;
        }
        
        #filterForm button:hover {
          background-color: #3182ce;
        }
        
        #filterForm button[type="button"] {
          background-color: #e2e8f0;
          color: #4a5568;
        }
        
        #filterForm button[type="button"]:hover {
          background-color: #cbd5e0;
        }
        
        /* Product Grid */
        .product-list {
          display: grid;
          grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
          gap: 2rem;
        }
        
        .product-item {
          background-color: white;
          border-radius: 12px;
          overflow: hidden;
          box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
          transition: transform 0.2s, box-shadow 0.2s;
        }
        
        .product-item:hover {
          transform: translateY(-5px);
          box-shadow: 0 10px 15px rgba(0, 0, 0, 0.1);
        }
        
        /* Updated image styling to prevent cropping */
        .product-image-container {
          width: 100%;
          height: 250px;
          overflow: hidden;
          position: relative;
          background-color: #f7fafc;
          border-bottom: 1px solid #e2e8f0;
        }
        
        .product-image {
          width: 100%;
          height: 100%;
          object-fit: contain; /* Changed from cover to contain */
          display: block;
          margin: 0 auto;
        }
        
        .product-content {
          padding: 1.5rem;
        }
        
        .product-item h2 {
          margin-bottom: 1rem;
        }
        
        .product-details {
          margin-bottom: 1.5rem;
        }
        
        .product-details p {
          margin-bottom: 0.5rem;
          font-size: 0.875rem;
          color: #4a5568;
          display: flex;
          justify-content: space-between;
        }
        
        .product-details p strong {
          font-weight: 500;
          color: #2d3748;
        }
        
        .eco-badge {
          display: inline-block;
          background-color: #c6f6d5;
          color: #2f855a;
          padding: 0.25rem 0.75rem;
          border-radius: 9999px;
          font-weight: 500;
          font-size: 0.75rem;
          margin-left: 0.5rem;
        }
        
        .non-eco-badge {
          display: inline-block;
          background-color: #e2e8f0;
          color: #4a5568;
          padding: 0.25rem 0.75rem;
          border-radius: 9999px;
          font-weight: 500;
          font-size: 0.75rem;
          margin-left: 0.5rem;
        }
        
        .price {
          font-size: 1.25rem;
          font-weight: 600;
          color: #2d3748;
          margin-bottom: 1rem;
        }
        
        .stock-status {
          font-size: 0.875rem;
          margin-bottom: 1.5rem;
        }
        
        .in-stock {
          color: #2f855a;
        }
        
        .out-of-stock {
          background-color: #fed7d7;
          color: #c53030;
          padding: 0.5rem;
          border-radius: 8px;
          text-align: center;
          font-weight: bold;
          margin-bottom: 1.5rem;
        }
        
        /* Buttons */
        .button-container {
          display: flex;
          gap: 0.75rem;
        }
        
        .button-container form {
          flex: 1;
        }
        
        .product-item button {
          width: 100%;
          padding: 0.75rem;
          border: none;
          border-radius: 8px;
          font-weight: 500;
          cursor: pointer;
          transition: background-color 0.2s;
        }
        
        .add-to-cart {
          background-color: #e74c3c;
          color: white;
        }
        
        .add-to-cart:hover {
          background-color: #3182ce;
        }
        
        .buy-now {
          background-color: #2d3748;
          color: white;
        }
        
        .buy-now:hover {
          background-color: #1a202c;
        }
        
        /* Status messages */
        .filter-message {
          background-color: #fed7d7;
          color: #c53030;
          padding: 1rem;
          border-radius: 8px;
          margin-bottom: 1.5rem;
          text-align: center;
        }
        
        .no-products {
          text-align: center;
          padding: 2rem;
          font-size: 1.25rem;
          color: #4a5568;
          background-color: white;
          border-radius: 12px;
          box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
        }
        
        /* Responsive adjustments */
        @media (max-width: 768px) {
          #filterForm {
            grid-template-columns: 1fr;
          }
          
          .product-list {
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
          }
          
          .container {
            padding: 1rem;
          }
          
          .button-container {
            flex-direction: column;
          }
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Product Catalog</h1>

    <form method="get" action="products" id="filterForm">
        <div>
            <label for="type">Type:</label>
            <select id="type" name="type">
                <option value="">All</option>
                <c:forEach var="t" items="${types}">
                    <option value="${t}" <c:if test="${t == param.type}">selected</c:if>>${t}</option>
                </c:forEach>
            </select>
        </div>

        <div>
            <label for="size">Size:</label>
            <select id="size" name="size">
                <option value="">All</option>
                <c:forEach var="s" items="${sizes}">
                    <option value="${s}" <c:if test="${s == param.size}">selected</c:if>>${s}</option>
                </c:forEach>
            </select>
        </div>

        <div>
            <label for="color">Color:</label>
            <select id="color" name="color">
                <option value="">All</option>
                <c:forEach var="c" items="${colors}">
                    <option value="${c}" <c:if test="${c == param.color}">selected</c:if>>${c}</option>
                </c:forEach>
            </select>
        </div>

        <div>
            <label for="priceRange">Price Range:</label>
            <input type="text" id="priceRange" name="priceRange" placeholder="min-max" value="${param.priceRange}" />
        </div>

        <div>
            <label for="sortBy">Sort By:</label>
            <select id="sortBy" name="sortBy">
                <option value="" <c:if test="${param.sortBy == null}">selected</c:if>>Default</option>
                <option value="priceAsc" <c:if test="${param.sortBy == 'priceAsc'}">selected</c:if>>Price: Low to High</option>
                <option value="priceDesc" <c:if test="${param.sortBy == 'priceDesc'}">selected</c:if>>Price: High to Low</option>
                <option value="newest" <c:if test="${param.sortBy == 'newest'}">selected</c:if>>Newest</option>
            </select>
        </div>

        <button type="submit">Filter</button>
        <button type="button" onclick="window.location.href='products'">Reset</button>
    </form>

    <c:choose>
        <c:when test="${not empty filterMessage}">
            <div class="filter-message">${filterMessage}</div>
        </c:when>
        <c:when test="${empty products}">
            <div class="no-products">No products available.</div>
        </c:when>
        <c:otherwise>
            <div class="product-list">
                <c:forEach var="product" items="${products}">
                    <div class="product-item">
                        <div class="product-image-container">
                            <img src="${product.imageUrl}" alt="${product.name}" class="product-image" />
                        </div>
                        <div class="product-content">
                            <h2>${product.name}</h2>
                            <div class="product-details">
                                <p><strong>Type:</strong> ${product.type}</p>
                                <p><strong>Size:</strong> ${product.size}</p>
                                <p><strong>Color:</strong> ${product.color}</p>
                                <p>
                                    <strong>Eco Friendly:</strong>
                                    <c:choose>
                                        <c:when test="${product.ecoFriendly}">
                                            <span class="eco-badge">Yes</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="non-eco-badge">No</span>
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                            
                            <div class="price">${product.price} ${product.currency}</div>
                            
                            <c:choose>
                                <c:when test="${product.stock == 0}">
                                    <div class="out-of-stock">Out of Stock</div>
                                </c:when>
                                <c:otherwise>
                                    <p class="stock-status in-stock">Stock: ${product.stock} available</p>
                                    <div class="button-container">
                                        <form method="post" action="cart">
                                            <input type="hidden" name="action" value="add" />
                                            <input type="hidden" name="productId" value="${product.id}" />
                                            <button type="submit" class="add-to-cart">Add to Cart</button>
                                        </form>
                                      
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>