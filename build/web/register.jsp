<!DOCTYPE html>
<html>
<head>
    <title>TrendSphere - Register</title>
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
        
        .registration-container {
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
            object-fit:fill;
        }
        
        .form-section {
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            padding: 40px 60px;
            background-color: white;
        }
        
        .registration-title {
            font-size: 24px;
            font-weight: 600;
            color: var(--text);
            margin-bottom: 8px;
        }
        
        .registration-subtitle {
            font-size: 14px;
            color: var(--text-light);
            margin-bottom: 30px;
        }
        
        .form-label {
            font-size: 14px;
            color: var(--text);
            margin-bottom: 8px;
        }
        
        .registration-form {
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
        
        .newsletter-checkbox {
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
        
        .signup-button {
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
        
        .signup-button:hover {
            background-color: #333;
        }
        
        .terms-text {
            font-size: 12px;
            color: var(--text-light);
            margin: 20px 0;
            line-height: 1.5;
            text-align: center;
        }
        
        .terms-link {
            color: var(--accent);
            text-decoration: none;
        }
        
        .login-link {
            font-size: 14px;
            color: var(--text);
            margin-top: 20px;
            text-align: center;
        }
        
        .login-link a {
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
            margin-top: -10px;
            text-align: left;
        }
    </style>
</head>
<body>
<div class="registration-container">
    <div class="image-section">
        <img src="images/coat_img.jpg" alt="girl image"/>
    </div>
    <div class="form-section">
        <h1 class="registration-title">Create your free account</h1>
        <p class="registration-subtitle">Get 10% off our new collection on signing up</p>
        
        <form class="registration-form" method="post" action="user">
            <input type="hidden" name="action" value="register" />
            
            <div>
                <div class="form-label">Name</div>
                <input type="text" class="form-input" id="username" name="username" required />
            </div>
            
            <div>
                <div class="form-label">Email</div>
                <input type="email" class="form-input" id="email" name="email" required />
            </div>
            
            <div>
                <div class="form-label">Password</div>
                <div class="password-field">
                    <input type="password" class="form-input" id="password" name="password" required />
                   
                </div>
            </div>
            
       
            
            <button type="submit" class="signup-button">Sign Up</button>
            
            <p class="terms-text">By clicking on 'Sign Up', you accept our <a href="#" class="terms-link">Confidentiality Policy</a></p>
            
            <p class="login-link">Already have an account? <a href="login.jsp">Sign in</a></p>
        </form>
    </div>
</div>
</body>
</html>