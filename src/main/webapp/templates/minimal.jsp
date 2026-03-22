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
    <title><%= profile.getFullName() %> - Minimal Portfolio</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/minimal.css">
</head>
<body class="template-minimal">
    <header class="min-header">
        <h1><%= profile.getFullName() %></h1>
        <div style="font-size: 1.2rem; margin-top: 0.5rem;"><%= profile.getTitle() != null ? profile.getTitle() : "" %></div>
        <p style="margin-top: 1.5rem;"><%= profile.getAbout() != null ? profile.getAbout() : "" %></p>
        
        <div class="min-contact">
            <% if(profile.getPhone() != null && !profile.getPhone().isEmpty()) { %> <span>📞 <%= profile.getPhone() %></span> <% } %>
            <% if(profile.getLinkedinUrl() != null && !profile.getLinkedinUrl().isEmpty()) { %> <a href="<%= profile.getLinkedinUrl() %>" target="_blank">LinkedIn</a> <% } %>
            <% if(profile.getGithubUrl() != null && !profile.getGithubUrl().isEmpty()) { %> <a href="<%= profile.getGithubUrl() %>" target="_blank">GitHub</a> <% } %>
            <a href="${pageContext.request.contextPath}/exportPdf?userId=<%= request.getAttribute("userId") %>">Download PDF</a>
        </div>
    </header>

    <% if (expList != null && !expList.isEmpty()) { %>
    <section>
        <h2>Experience</h2>
        <% for(Experience exp : expList) { %>
            <div class="min-item">
                <div class="min-item-header">
                    <span class="min-item-title"><%= exp.getRole() %> at <%= exp.getCompany() %></span>
                    <span class="min-item-date"><%= exp.getDuration() %></span>
                </div>
                <p><%= exp.getDescription() %></p>
            </div>
        <% } %>
    </section>
    <% } %>

    <% if (eduList != null && !eduList.isEmpty()) { %>
    <section>
        <h2>Education</h2>
        <% for(Education edu : eduList) { %>
            <div class="min-item">
                <div class="min-item-header">
                    <span class="min-item-title"><%= edu.getDegree() %></span>
                    <span class="min-item-date"><%= edu.getPassingYear() %></span>
                </div>
                <div class="min-item-subtitle"><%= edu.getInstitution() %></div>
                <p><%= edu.getDescription() != null ? edu.getDescription() : "" %></p>
            </div>
        <% } %>
    </section>
    <% } %>

    <% if (projList != null && !projList.isEmpty()) { %>
    <section>
        <h2>Projects</h2>
        <% for(Project proj : projList) { %>
            <div class="min-item">
                <div class="min-item-header">
                    <span class="min-item-title">
                        <%= proj.getTitle() %> 
                        <% if(proj.getGithubLink() != null && !proj.getGithubLink().isEmpty()) { %>
                            <a href="<%= proj.getGithubLink() %>" target="_blank" style="font-size:0.9rem; font-weight:normal; margin-left:10px;">Link</a>
                        <% } %>
                    </span>
                </div>
                <div class="min-item-subtitle" style="margin-bottom:0.5rem;">Tech: <%= proj.getTechnologies() %></div>
                <p><%= proj.getDescription() %></p>
            </div>
        <% } %>
    </section>
    <% } %>

    <% if (skillList != null && !skillList.isEmpty()) { %>
    <section>
        <h2>Skills</h2>
        <div class="min-skills">
        <% for(Skill skill : skillList) { %>
            <span class="min-skill-tag"><%= skill.getSkillName() %></span>
        <% } %>
        </div>
    </section>
    <% } %>
</body>
</html>
