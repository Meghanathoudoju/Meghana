package com.portfolio.controller;

import com.portfolio.dao.ProjectDao;
import com.portfolio.model.Project;
import com.portfolio.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/project")
public class ProjectController extends HttpServlet {
    private ProjectDao projectDao;

    @Override
    public void init() {
        projectDao = new ProjectDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("auth?action=login");
            return;
        }

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String action = request.getParameter("action");

        Project proj = new Project();
        proj.setUserId(userId);
        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
            proj.setId(Integer.parseInt(request.getParameter("id")));
        }
        
        if ("delete".equals(action)) {
            projectDao.deleteProject(proj.getId(), userId);
        } else {
            proj.setTitle(request.getParameter("title"));
            proj.setDescription(request.getParameter("description"));
            proj.setTechnologies(request.getParameter("technologies"));
            proj.setGithubLink(request.getParameter("githubLink"));

            if ("add".equals(action)) {
                projectDao.addProject(proj);
            } else if ("update".equals(action)) {
                projectDao.updateProject(proj);
            }
        }
        response.sendRedirect("dashboard?success=project_updated");
    }
}
