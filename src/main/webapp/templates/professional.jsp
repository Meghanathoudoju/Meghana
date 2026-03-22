<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List, com.portfolio.model.*" %>
        <% Profile profile=(Profile) request.getAttribute("profile"); List<Education> eduList = (List<Education>)
                request.getAttribute("educationList");
                List<Project> projList = (List<Project>) request.getAttribute("projectList");
                        List<Skill> skillList = (List<Skill>) request.getAttribute("skillList");
                                List<Experience> expList = (List<Experience>) request.getAttribute("experienceList");
                                        %>
                                        <!DOCTYPE html>
                                        <html lang="en">

                                        <head>
                                            <meta charset="UTF-8">
                                            <title>
                                                <%= profile.getFullName() %> - Professional
                                            </title>
                                            <link rel="stylesheet"
                                                href="${pageContext.request.contextPath}/css/professional.css">
                                        </head>

                                        <body class="template-professional">
                                            <div class="prof-header-wrap">
                                                <div class="prof-container">
                                                    <div class="prof-header-content">
                                                        <div>
                                                            <h1 class="prof-name">
                                                                <%= profile.getFullName() %>
                                                            </h1>
                                                            <div class="prof-title">
                                                                <%= profile.getTitle() !=null ? profile.getTitle() : ""
                                                                    %>
                                                            </div>
                                                        </div>
                                                        <div class="prof-contact">
                                                            <% if(profile.getPhone() !=null &&
                                                                !profile.getPhone().isEmpty()) { %>
                                                                <div>
                                                                    <%= profile.getPhone() %>
                                                                </div>
                                                                <% } %>
                                                                    <% if(profile.getLinkedinUrl() !=null &&
                                                                        !profile.getLinkedinUrl().isEmpty()) { %> <a
                                                                            href="<%= profile.getLinkedinUrl() %>"
                                                                            target="_blank">LinkedIn Profile</a>
                                                                        <% } %>
                                                                            <% if(profile.getGithubUrl() !=null &&
                                                                                !profile.getGithubUrl().isEmpty()) { %>
                                                                                <a href="<%= profile.getGithubUrl() %>"
                                                                                    target="_blank">GitHub Profile</a>
                                                                                <% } %>
                                                                                    <a href="${pageContext.request.contextPath}/exportPdf?userId=<%= request.getAttribute("
                                                                                        userId") %>"
                                                                                        style="display:inline-block;
                                                                                        margin-top:10px;
                                                                                        background:#fff;
                                                                                        color:var(--prof-primary);
                                                                                        padding:5px 15px;
                                                                                        border-radius:4px;
                                                                                        font-weight:600;">Download
                                                                                        Resume</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="prof-container">
                                                <div class="prof-main">
                                                    <!-- Left Column: Experience, Projects -->
                                                    <div>
                                                        <section class="prof-section">
                                                            <h2 class="prof-section-title">Executive Summary</h2>
                                                            <p
                                                                style="font-size: 1.1rem; line-height: 1.8; color: #4a5568;">
                                                                <%= profile.getAbout() !=null ? profile.getAbout() : ""
                                                                    %>
                                                            </p>
                                                        </section>

                                                        <% if (expList !=null && !expList.isEmpty()) { %>
                                                            <section class="prof-section">
                                                                <h2 class="prof-section-title">Professional Experience
                                                                </h2>
                                                                <% for(Experience exp : expList) { %>
                                                                    <div class="prof-item">
                                                                        <div class="prof-item-header">
                                                                            <span class="prof-item-title">
                                                                                <%= exp.getRole() %>
                                                                            </span>
                                                                            <span class="prof-item-date">
                                                                                <%= exp.getDuration() %>
                                                                            </span>
                                                                        </div>
                                                                        <div class="prof-item-subtitle">
                                                                            <%= exp.getCompany() %>
                                                                        </div>
                                                                        <p class="prof-item-desc">
                                                                            <%= exp.getDescription() %>
                                                                        </p>
                                                                    </div>
                                                                    <% } %>
                                                            </section>
                                                            <% } %>

                                                                <% if (projList !=null && !projList.isEmpty()) { %>
                                                                    <section class="prof-section">
                                                                        <h2 class="prof-section-title">Selected Projects
                                                                        </h2>
                                                                        <% for(Project proj : projList) { %>
                                                                            <div class="prof-item">
                                                                                <div class="prof-item-header">
                                                                                    <span class="prof-item-title">
                                                                                        <%= proj.getTitle() %>
                                                                                            <% if(proj.getGithubLink()
                                                                                                !=null &&
                                                                                                !proj.getGithubLink().isEmpty())
                                                                                                { %>
                                                                                                <a href="<%= proj.getGithubLink() %>"
                                                                                                    target="_blank"
                                                                                                    style="font-size:0.9rem; color:var(--prof-secondary); margin-left:10px;">[Link]</a>
                                                                                                <% } %>
                                                                                    </span>
                                                                                </div>
                                                                                <div class="prof-item-subtitle">
                                                                                    Environment: <%=
                                                                                        proj.getTechnologies() %>
                                                                                </div>
                                                                                <p class="prof-item-desc">
                                                                                    <%= proj.getDescription() %>
                                                                                </p>
                                                                            </div>
                                                                            <% } %>
                                                                    </section>
                                                                    <% } %>
                                                    </div>

                                                    <!-- Right Column: Sidebar (Skills, Education) -->
                                                    <div>
                                                        <div class="prof-sidebar">
                                                            <% if (skillList !=null && !skillList.isEmpty()) { %>
                                                                <div style="margin-bottom: 3rem;">
                                                                    <h3 class="prof-section-title"
                                                                        style="font-size: 1.25rem;">Core Competencies
                                                                    </h3>
                                                                    <ul class="prof-skills-list">
                                                                        <% for(Skill skill : skillList) { %>
                                                                            <li>
                                                                                <span class="prof-skill-name">
                                                                                    <%= skill.getSkillName() %>
                                                                                </span>
                                                                                <span class="prof-skill-level">
                                                                                    <%= skill.getSkillLevel() %>
                                                                                </span>
                                                                            </li>
                                                                            <% } %>
                                                                    </ul>
                                                                </div>
                                                                <% } %>

                                                                    <% if (eduList !=null && !eduList.isEmpty()) { %>
                                                                        <div>
                                                                            <h3 class="prof-section-title"
                                                                                style="font-size: 1.25rem;">Education
                                                                            </h3>
                                                                            <% for(Education edu : eduList) { %>
                                                                                <div style="margin-bottom: 1.5rem;">
                                                                                    <div
                                                                                        style="font-weight:700; color:var(--prof-text); margin-bottom:0.25rem;">
                                                                                        <%= edu.getDegree() %>
                                                                                    </div>
                                                                                    <div
                                                                                        style="color:var(--prof-secondary); font-weight:600; font-size:0.9rem; margin-bottom:0.25rem;">
                                                                                        <%= edu.getInstitution() %>
                                                                                    </div>
                                                                                    <div
                                                                                        style="color:var(--prof-text-light); font-size:0.9rem;">
                                                                                        <%= edu.getPassingYear() %>
                                                                                    </div>
                                                                                </div>
                                                                                <% } %>
                                                                        </div>
                                                                        <% } %>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </body>

                                        </html>