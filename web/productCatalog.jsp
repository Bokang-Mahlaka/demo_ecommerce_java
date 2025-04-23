<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>TrendSphere - Product Catalog</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/catalog.css">
    <script src="js/filter.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
</head>
<body>
<div class="catalog-container">
    <div class="catalog-header">
        <h1 class="catalog-title">Product Catalog</h1>
        <p class="catalog-subtitle">Discover our latest collection of premium fashion items</p>
    </div>
    
    <div class="filter-container">
        <form method="get" action="filter" id="filterForm" class="filter-form">
            <div class="filter-group">
                <label for="type" class="filter-label">Category</label>
                <select name="type" id="type" class="filter-input">
                    <option value="">All Categories</option>
                    <option value="Shirt">Shirt</option>
                    <option value="Trousers">Trousers</option>
                    <option value="Suit">Suit</option>
                    <option value="Cap">Cap</option>
                    <option value="Tie">Tie</option>
                    <option value="Belt">Belt</option>
                    <option value="Accessory">Accessory</option>
                    <option value="Shoe">Shoe</option>
                    <option value="Cologne">Cologne</option>
                </select>
            </div>
            
            <div class="filter-group">
                <label for="size" class="filter-label">Size</label>
                <select name="size" id="size" class="filter-input">
                    <option value="">All Sizes</option>
                    <option value="S">S</option>
                    <option value="M">M</option>
                    <option value="L">L</option>
                    <option value="XL">XL</option>
                </select>
            </div>
            
            <div class="filter-group">
                <label for="color" class="filter-label">Color</label>
                <input type="text" name="color" id="color" placeholder="Search by color" class="filter-input">
            </div>
            
            <div class="filter-group">
                <label for="priceRange" class="filter-label">Price Range</label>
                <select name="priceRange" id="priceRange" class="filter-input">
                    <option value="">All Prices</option>
                    <option value="0-50">M0 - M50</option>
                    <option value="51-100">M51 - M100</option>
                    <option value="101-200">M101 - M500</option>
                    <option value="201-500">M501 - M1000</option>
                </select>
            </div>
            
            <div class="filter-group">
                <button type="submit" class="filter-button">Apply Filters</button>
            </div>
        </form>
    </div>
    
    <div class="product-grid">
        <c:forEach var="product" items="${products}">
            <div class="product-card">
                <div class="product-image-container">
                    <img src="${product.imageUrl}" alt="${product.name}" class="product-image" />
                </div>
                <div class="product-details">
                    <h3 class="product-name">${product.name}</h3>
                    <div class="product-meta">
                        <span class="product-meta-item">${product.type}</span>
                        <span class="product-meta-item">Size: ${product.size}</span>
                        <span class="product-meta-item">${product.color}</span>
                    </div>
                    <p class="product-price">${product.currency} ${product.price}</p>
                    <a href="productDetails.jsp?id=${product.id}" class="product-link">View Details</a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>