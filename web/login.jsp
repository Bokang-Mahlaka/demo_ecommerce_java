<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>TrendSphere - Login</title>
    <style>
        :root {
            --primary: #2c3e50;
            --secondary: #34495e;
            --accent: #000000;
            --light: #ecf0f1;
            --dark: #2c3e50;
            --text: #333;
            --text-light: #7f8c8d;
            --border: #e0e0e0;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            min-height: 100vh;
        }
        
        .login-container {
            display: flex;
            width: 100%;
            height: 100vh;
        }
        
        .image-section {
            flex: 1;
            background-color: #f5f5f5;
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden;
        }
        
        .image-section img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        
        .form-section {
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            padding: 40px 60px;
            background-color: white;
        }
        
        .login-title {
            font-size: 24px;
            font-weight: 600;
            color: var(--text);
            margin-bottom: 8px;
        }
        
        .login-subtitle {
            font-size: 14px;
            color: var(--text-light);
            margin-bottom: 30px;
        }
        
        .form-label {
            font-size: 14px;
            color: var(--text);
            margin-bottom: 8px;
        }
        
        .login-form {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
        
        .form-input {
            width: 100%;
            padding: 14px 16px;
            border: 1px solid var(--border);
            border-radius: 4px;
            font-size: 14px;
            transition: border-color 0.3s;
        }
        
        .form-input:focus {
            outline: none;
            border-color: var(--accent);
        }
        
        .remember-me {
            display: flex;
            align-items: center;
            margin: 15px 0;
            gap: 10px;
        }
        
        .checkbox-input {
            width: 18px;
            height: 18px;
            accent-color: var(--accent);
        }
        
        .checkbox-label {
            font-size: 14px;
            color: var(--text);
        }
        
        .login-button {
            width: 100%;
            padding: 14px;
            background-color: var(--accent);
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        .login-button:hover {
            background-color: #333;
        }
        
        .forgot-password {
            font-size: 14px;
            color: var(--text-light);
            text-align: center;
            margin: 15px 0;
        }
        
        .forgot-password a {
            color: var(--accent);
            text-decoration: none;
            font-weight: 600;
        }
        
        .register-link {
            font-size: 14px;
            color: var(--text);
            margin-top: 20px;
            text-align: center;
        }
        
        .register-link a {
            color: var(--accent);
            text-decoration: none;
            font-weight: 600;
        }
        
        .password-field {
            position: relative;
        }
        
        .password-toggle {
            position: absolute;
            right: 15px;
            top: 50%;
            transform: translateY(-50%);
            cursor: pointer;
            opacity: 0.6;
        }
        
        .error-message {
            color: #e74c3c;
            font-size: 14px;
            margin-top: 15px;
            text-align: center;
            padding: 10px;
            background-color: #fadbd8;
            border-radius: 4px;
        }
    </style>
</head>
<body>
<div class="login-container">
    <div class="image-section">
        <img src="images/login_img.jpg" alt="Fashion model in stylish outfit" />
    </div>
    <div class="form-section">
        <h1 class="login-title">Welcome back</h1>
        <p class="login-subtitle">Sign in to your TrendSphere account</p>
        
        <form class="login-form" method="post" action="user">
            <input type="hidden" name="action" value="login" />
            
            <div>
                <div class="form-label">Email or Username</div>
                <input type="text" class="form-input" id="username" name="username" required />
            </div>
            
            <div>
                <div class="form-label">Password</div>
                <div class="password-field">
                    <input type="password" class="form-input" id="password" name="password" required />
                    <span class="password-toggle">?</span>
                </div>
            </div>
            
          
            <button type="submit" class="login-button">Sign In</button>
            
            <c:if test="${not empty error}">
                <div class="error-message">${error}</div>
            </c:if>
          
            <p class="register-link">Don't have an account? <a href="register.jsp">Sign up</a></p>
        </form>
    </div>
</div>
</body>
</html>