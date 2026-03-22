<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.portfolio.model.*" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("auth?action=login");
        return;
    }
    Profile profile = (Profile) request.getAttribute("profile");
    List<Education> educationList = (List<Education>) request.getAttribute("educationList");
    List<Project> projectList = (List<Project>) request.getAttribute("projectList");
    List<Skill> skillList = (List<Skill>) request.getAttribute("skillList");
    List<Experience> experienceList = (List<Experience>) request.getAttribute("experienceList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Portfolio Builder</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <script src="js/script.js"></script>
    <div class="dashboard-wrapper">
        <div class="sidebar">
            <h2>Portfolio Builder</h2>
            <a href="#" id="nav-profile-section" class="active" onclick="showSection('profile-section')">Profile</a>
            <a href="#" id="nav-education-section" onclick="showSection('education-section')">Education</a>
            <a href="#" id="nav-experience-section" onclick="showSection('experience-section')">Experience</a>
            <a href="#" id="nav-projects-section" onclick="showSection('projects-section')">Projects</a>
            <a href="#" id="nav-skills-section" onclick="showSection('skills-section')">Skills</a>
            <hr style="border-color: #374151; margin: 20px 0;">
            <a href="view/<%= user.getId() %>" target="_blank" style="background:#2563eb; color:white; text-align:center;">View Live Portfolio</a>
            <a href="exportPdf?userId=<%= user.getId() %>" style="background:#10b981; color:white; text-align:center;">Download PDF</a>
            <a href="auth?action=logout" class="logout mt-3 text-center">Logout</a>
        </div>
        
        <div class="main-content">
            <div class="top-bar">
                <h3>Welcome, <%= user.getUsername() %>!</h3>
                <% String success = request.getParameter("success");
                   if (success != null) { out.print("<span class='alert alert-success' style='padding:5px 10px; margin:0;'>Successfully updated!</span>"); } 
                %>
            </div>

            <%@ include file="profile.jsp" %>
            <%@ include file="experience.jsp" %>
            <%@ include file="education.jsp" %>
            <%@ include file="projects.jsp" %>
            <%@ include file="skills.jsp" %>

        </div>
    </div>
</body>
</html>
