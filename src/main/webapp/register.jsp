<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register - Portfolio Builder</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="auth-page">
    <nav class="navbar">
        <a href="index.jsp" class="navbar-brand">Portfolio Builder</a>
        <div class="navbar-nav">
            <a href="auth?action=login" class="btn btn-outline" style="padding: 0.4rem 1rem;">Login</a>
        </div>
    </nav>
    <div class="auth-container">
        <h2>Create an Account</h2>
        <% 
            String error = request.getParameter("error");
            if ("failed".equals(error)) { out.print("<p class='alert alert-danger'>Registration failed. Username or email might be taken.</p>"); }
        %>
        <form action="auth?action=register" method="POST">
            <div class="form-group">
                <label>Username</label>
                <input type="text" name="username" required>
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="email" name="email" required>
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Register</button>
        </form>
        <p class="text-center mt-3">Already have an account? <a href="auth?action=login">Login</a></p>
    </div>
    <script src="js/script.js"></script>
</body>
</html>
