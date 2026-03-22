<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Portfolio Builder Website</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        body { display: flex; flex-direction: column; min-height: 100vh; background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%); margin:0; }
        .hero-container { flex: 1; display: flex; align-items: center; justify-content: center; padding-top: 60px; }
        .hero { background: white; padding: 50px; border-radius: 12px; box-shadow: 0 10px 30px rgba(0,0,0,0.1); max-width:600px; text-align: center; }
        .hero h1 { font-size: 3rem; margin-bottom: 20px; color: #333; letter-spacing: -0.025em; font-weight: 700; }
        .hero p { font-size: 1.2rem; color: #666; margin-bottom: 30px; }
    </style>
</head>
<body>
    <nav class="navbar">
        <a href="index.jsp" class="navbar-brand">Portfolio Builder</a>
        <div class="navbar-nav">
            <a href="auth?action=login" class="btn btn-outline" style="padding: 0.4rem 1rem;">Login</a>
            <a href="auth?action=register" class="btn btn-primary" style="padding: 0.4rem 1rem;">Register</a>
        </div>
    </nav>
    <div class="hero-container">
        <div class="hero">
        <h1>Build Your Professional Portfolio</h1>
        <p>Create, customize, and share your resume & portfolio in minutes. Beautiful templates included.</p>
        <div>
            <a href="auth?action=login" class="btn btn-outline">Login</a>
            <a href="auth?action=register" class="btn">Get Started</a>
        </div>
        </div>
    </div>
    <script src="js/script.js"></script>
</body>
</html>
