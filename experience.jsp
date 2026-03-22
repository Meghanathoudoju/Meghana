<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- EXPERIENCE SECTION -->
<section id="experience-section" class="content-section" style="display:none;">
    <div class="card">
        <div class="card-header">
            <h3>Add Experience</h3>
        </div>
        <form action="experience" method="POST">
            <input type="hidden" name="action" value="add">
            <div style="display:flex; gap:15px;">
                <div class="form-group" style="flex:1;">
                    <label>Company</label>
                    <input type="text" name="company" required>
                </div>
                <div class="form-group" style="flex:1;">
                    <label>Role/Job Title</label>
                    <input type="text" name="role" required>
                </div>
            </div>
            <div class="form-group">
                <label>Duration (e.g. Jan 2020 - Present)</label>
                <input type="text" name="duration" required>
            </div>
            <div class="form-group">
                <label>Description</label>
                <textarea name="description" rows="3"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Add Experience</button>
        </form>
    </div>
    
    <div class="card">
        <div class="card-header">
            <h3>Your Experience</h3>
        </div>
        <ul class="item-list">
            <% if (experienceList != null && !experienceList.isEmpty()) { 
                for (Experience exp : experienceList) { %>
                <li>
                    <div class="item-content">
                        <h4><%= exp.getRole() %> at <%= exp.getCompany() %></h4>
                        <p><%= exp.getDuration() %></p>
                        <p style="margin-top:5px;"><%= exp.getDescription() %></p>
                    </div>
                    <div class="item-actions">
                        <form action="experience" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= exp.getId() %>">
                            <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure?')">Delete</button>
                        </form>
                    </div>
                </li>
            <%  } 
               } else { out.print("<p>No experience added yet.</p>"); } %>
        </ul>
    </div>
</section>
