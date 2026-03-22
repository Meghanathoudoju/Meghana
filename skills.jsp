<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- SKILLS SECTION -->
<section id="skills-section" class="content-section" style="display:none;">
    <div class="card">
        <div class="card-header">
            <h3>Add Skill</h3>
        </div>
        <form action="skill" method="POST">
            <input type="hidden" name="action" value="add">
            <div style="display:flex; gap:15px;">
                <div class="form-group" style="flex:2;">
                    <label>Skill Name (e.g. Java, HTML)</label>
                    <input type="text" name="skillName" required>
                </div>
                <div class="form-group" style="flex:1;">
                    <label>Skill Level</label>
                    <select name="skillLevel">
                        <option value="Beginner">Beginner</option>
                        <option value="Intermediate">Intermediate</option>
                        <option value="Advanced">Advanced</option>
                        <option value="Expert">Expert</option>
                    </select>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Add Skill</button>
        </form>
    </div>
    
    <div class="card">
        <div class="card-header">
            <h3>Your Skills</h3>
        </div>
        <ul class="item-list">
            <% if (skillList != null && !skillList.isEmpty()) { 
                for (Skill skill : skillList) { %>
                <li>
                    <div class="item-content">
                        <h4><%= skill.getSkillName() %></h4>
                        <p>Level: <%= skill.getSkillLevel() %></p>
                    </div>
                    <div class="item-actions">
                        <form action="skill" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= skill.getId() %>">
                            <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure?')">Delete</button>
                        </form>
                    </div>
                </li>
            <%  } 
               } else { out.print("<p>No skills added yet.</p>"); } %>
        </ul>
    </div>
</section>
