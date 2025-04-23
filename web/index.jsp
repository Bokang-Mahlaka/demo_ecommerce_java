<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>TrendSphere - Home</title>
<!--    <link rel="stylesheet" href="css/styles.css">-->
    <style>
        :root {
            --primary: #2c3e50;
            --secondary: #34495e;
            --accent: #e74c3c;
            --light: #ecf0f1;
            --dark: #2c3e50;
            --text: #333;
            --text-light: #7f8c8d;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6;
            color: var(--text);
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        
        h1 {
            color: var(--primary);
            font-size: 2.5rem;
            margin-bottom: 10px;
            text-align: center;
            font-weight: 700;
        }
        
        p {
            color: var(--text-light);
            font-size: 1.1rem;
            text-align: center;
            margin-bottom: 40px;
        }
        
        nav {
            background-color: white;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 40px;
        }
        
        nav ul {
            display: flex;
            justify-content: center;
            list-style: none;
            padding: 0;
            margin: 0;
            flex-wrap: wrap;
        }
        
        nav li {
            margin: 0 15px;
        }
        
        nav a {
            text-decoration: none;
            color: var(--secondary);
            font-weight: 600;
            font-size: 1rem;
            padding: 8px 12px;
            border-radius: 4px;
            transition: all 0.3s ease;
        }
        
        nav a:hover {
            color: var(--accent);
            background-color: rgba(231, 76, 60, 0.1);
        }
        
        section {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            margin-bottom: 40px;
        }
        
        h2 {
            color: var(--primary);
            font-size: 1.8rem;
            margin-top: 0;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 2px solid var(--light);
        }
        
        #featured-products {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 30px;
            padding: 20px 0;
        }
        
        /* Placeholder for product cards */
        .product-placeholder {
            background-color: var(--light);
            border-radius: 8px;
            height: 350px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: var(--text-light);
            font-weight: 600;
        }
        
        @media (max-width: 768px) {
            nav ul {
                flex-direction: column;
                align-items: center;
            }
            
            nav li {
                margin: 5px 0;
            }
            
            h1 {
                font-size: 2rem;
            }
        }
    </style>
</head>
<body>
    
<div class="container">
     <h1>Welcome to TrendSphere</h1>
             <p>Your destination for contemporary and sustainable men's fashion.</p>

    <section>
        <h2>Featured Products</h2>
        <div id="featured-products">
            <!-- Placeholder product cards -->
            <div class="product-placeholder">Product 1</div>
            <div class="product-placeholder">Product 2</div>
            <div class="product-placeholder">Product 3</div>
            <div class="product-placeholder">Product 4</div>
        </div>
    </section>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>