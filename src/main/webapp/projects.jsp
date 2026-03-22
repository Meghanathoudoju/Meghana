<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- PROJECTS SECTION -->
<section id="projects-section" class="content-section" style="display:none;">
    <div class="card">
        <div class="card-header">
            <h3>Add Project</h3>
        </div>
        <form action="project" method="POST">
            <input type="hidden" name="action" value="add">
            <div class="form-group">
                <label>Project Title</label>
                <input type="text" name="title" required>
            </div>
            <div class="form-group">
                <label>Technologies Used (comma separated)</label>
                <input type="text" name="technologies" required>
            </div>
            <div class="form-group">
                <label>GitHub or Live Link</label>
                <input type="text" name="githubLink">
            </div>
            <div class="form-group">
                <label>Description</label>
                <textarea name="description" rows="3"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Add Project</button>
        </form>
    </div>
    
    <div class="card">
        <div class="card-header">
            <h3>Your Projects</h3>
        </div>
        <ul class="item-list">
            <% if (projectList != null && !projectList.isEmpty()) { 
                for (Project proj : projectList) { %>
                <li>
                    <div class="item-content">
                        <h4><%= proj.getTitle() %></h4>
                        <p>Tech: <%= proj.getTechnologies() %></p>
                        <p style="margin-top:5px;"><%= proj.getDescription() %></p>
                        <% if(proj.getGithubLink() != null && !proj.getGithubLink().isEmpty()) { %>
                            <p><a href="<%= proj.getGithubLink() %>" target="_blank">View Link</a></p>
                        <% } %>
                    </div>
                    <div class="item-actions">
                        <form action="project" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= proj.getId() %>">
                            <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure?')">Delete</button>
                        </form>
                    </div>
                </li>
            <%  } 
               } else { out.print("<p>No projects added yet.</p>"); } %>
        </ul>
    </div>
</section>
