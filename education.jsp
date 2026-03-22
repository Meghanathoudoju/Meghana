<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- EDUCATION SECTION -->
<section id="education-section" class="content-section" style="display:none;">
    <div class="card">
        <div class="card-header">
            <h3>Add Education</h3>
        </div>
        <form action="education" method="POST">
            <input type="hidden" name="action" value="add">
            <div style="display:flex; gap:15px;">
                <div class="form-group" style="flex:1;">
                    <label>Degree/Certification</label>
                    <input type="text" name="degree" required>
                </div>
                <div class="form-group" style="flex:1;">
                    <label>Institution</label>
                    <input type="text" name="institution" required>
                </div>
            </div>
            <div class="form-group">
                <label>Passing Year</label>
                <input type="number" name="passingYear" required>
            </div>
            <div class="form-group">
                <label>Description (Optional)</label>
                <textarea name="description" rows="2"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Add Education</button>
        </form>
    </div>
    
    <div class="card">
        <div class="card-header">
            <h3>Your Education</h3>
        </div>
        <ul class="item-list">
            <% if (educationList != null && !educationList.isEmpty()) { 
                for (Education edu : educationList) { %>
                <li>
                    <div class="item-content">
                        <h4><%= edu.getDegree() %></h4>
                        <p><%= edu.getInstitution() %> - <%= edu.getPassingYear() %></p>
                        <p style="margin-top:5px;"><%= edu.getDescription() != null ? edu.getDescription() : "" %></p>
                    </div>
                    <div class="item-actions">
                        <form action="education" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= edu.getId() %>">
                            <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure?')">Delete</button>
                        </form>
                    </div>
                </li>
            <%  } 
               } else { out.print("<p>No education added yet.</p>"); } %>
        </ul>
    </div>
</section>
