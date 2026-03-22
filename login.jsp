<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - Portfolio Builder</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="auth-page">
    <nav class="navbar">
        <a href="index.jsp" class="navbar-brand">Portfolio Builder</a>
        <div class="navbar-nav">
            <a href="auth?action=register" class="btn btn-primary" style="padding: 0.4rem 1rem;">Register</a>
        </div>
    </nav>
    <div class="auth-container">
        <h2>Welcome Back</h2>
        <% 
            String error = request.getParameter("error");
            if ("invalid".equals(error)) { out.print("<p class='alert alert-danger'>Invalid username or password.</p>"); }
            String success = request.getParameter("success");
            if ("registered".equals(success)) { out.print("<p class='alert alert-success'>Registration successful. Please log in.</p>"); }
        %>
        <form action="auth?action=login" method="POST">
            <div class="form-group">
                <label>Username</label>
                <input type="text" name="username" required>
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Login</button>
        </form>
        <p class="text-center mt-3">Don't have an account? <a href="auth?action=register">Register</a></p>
    </div>
    <script src="js/script.js"></script>
</body>
</html>
