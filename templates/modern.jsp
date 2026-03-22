<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.portfolio.model.*" %>
<%
    Profile profile = (Profile) request.getAttribute("profile");
    List<Education> eduList = (List<Education>) request.getAttribute("educationList");
    List<Project> projList = (List<Project>) request.getAttribute("projectList");
    List<Skill> skillList = (List<Skill>) request.getAttribute("skillList");
    List<Experience> expList = (List<Experience>) request.getAttribute("experienceList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><%= profile.getFullName() %> - Modern Portfolio</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modern.css">
</head>
<body class="template-modern">
    <div class="mod-container">
        <header class="mod-hero">
            <% if(profile.getProfileImagePath() != null && !profile.getProfileImagePath().isEmpty()) { %>
                <img src="${pageContext.request.contextPath}/<%= profile.getProfileImagePath() %>" alt="Profile Image" style="width:120px; height:120px; border-radius:50%; border:3px solid var(--mod-accent); margin-bottom:1.5rem; object-fit:cover;">
            <% } %>
            <h1><%= profile.getFullName() %></h1>
            <div style="font-size: 1.5rem; color: #fff; margin-bottom: 2rem; font-weight: 300; letter-spacing: 2px;"><%= profile.getTitle() != null ? profile.getTitle() : "" %></div>
            <p><%= profile.getAbout() != null ? profile.getAbout() : "" %></p>
            
            <div class="mod-contact-links">
                <% if(profile.getLinkedinUrl() != null && !profile.getLinkedinUrl().isEmpty()) { %> <a href="<%= profile.getLinkedinUrl() %>" target="_blank" class="mod-btn">LinkedIn</a> <% } %>
                <% if(profile.getGithubUrl() != null && !profile.getGithubUrl().isEmpty()) { %> <a href="<%= profile.getGithubUrl() %>" target="_blank" class="mod-btn">GitHub</a> <% } %>
                <a href="${pageContext.request.contextPath}/exportPdf?userId=<%= request.getAttribute("userId") %>" class="mod-btn" style="background:var(--mod-accent); border:none; color:white;">Download PDF</a>
            </div>
        </header>

        <% if (expList != null && !expList.isEmpty()) { %>
        <section class="mod-section">
            <h2 class="mod-section-title">Experience</h2>
            <div class="mod-grid">
                <% for(Experience exp : expList) { %>
                    <div class="mod-card">
                        <div class="mod-card-title"><%= exp.getRole() %></div>
                        <div class="mod-card-subtitle"><%= exp.getCompany() %> | <%= exp.getDuration() %></div>
                        <p style="color:var(--mod-muted); font-size:0.95rem;"><%= exp.getDescription() %></p>
                    </div>
                <% } %>
            </div>
        </section>
        <% } %>

        <% if (projList != null && !projList.isEmpty()) { %>
        <section class="mod-section">
            <h2 class="mod-section-title">Projects</h2>
            <div class="mod-grid">
                <% for(Project proj : projList) { %>
                    <div class="mod-card">
                        <div class="mod-card-title"><%= proj.getTitle() %></div>
                        <div class="mod-card-subtitle" style="color:#60a5fa;"><%= proj.getTechnologies() %></div>
                        <p style="color:var(--mod-muted); font-size:0.95rem; margin-bottom:1rem;"><%= proj.getDescription() %></p>
                        <% if(proj.getGithubLink() != null && !proj.getGithubLink().isEmpty()) { %>
                            <a href="<%= proj.getGithubLink() %>" target="_blank" style="color:#fff; text-decoration:none; font-size:0.9rem; font-weight:600; border-bottom:1px solid #60a5fa;">View Project ↗</a>
                        <% } %>
                    </div>
                <% } %>
            </div>
        </section>
        <% } %>

        <% if (eduList != null && !eduList.isEmpty()) { %>
        <section class="mod-section">
            <h2 class="mod-section-title">Education</h2>
            <div class="mod-grid">
                <% for(Education edu : eduList) { %>
                    <div class="mod-card">
                        <div class="mod-card-title"><%= edu.getDegree() %></div>
                        <div class="mod-card-subtitle"><%= edu.getInstitution() %> | <%= edu.getPassingYear() %></div>
                        <p style="color:var(--mod-muted); font-size:0.95rem;"><%= edu.getDescription() != null ? edu.getDescription() : "" %></p>
                    </div>
                <% } %>
            </div>
        </section>
        <% } %>

        <% if (skillList != null && !skillList.isEmpty()) { %>
        <section class="mod-section">
            <h2 class="mod-section-title">Skills & Tech Stack</h2>
            <div class="mod-skills-wrap">
            <% for(Skill skill : skillList) { %>
                <span class="mod-skill"><%= skill.getSkillName() %></span>
            <% } %>
            </div>
        </section>
        <% } %>
    </div>
</body>
</html>
