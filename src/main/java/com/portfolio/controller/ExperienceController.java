package com.portfolio.controller;

import com.portfolio.dao.ExperienceDao;
import com.portfolio.model.Experience;
import com.portfolio.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/experience")
public class ExperienceController extends HttpServlet {
    private ExperienceDao experienceDao;

    @Override
    public void init() {
        experienceDao = new ExperienceDao();
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

        Experience exp = new Experience();
        exp.setUserId(userId);
        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
            exp.setId(Integer.parseInt(request.getParameter("id")));
        }
        
        if ("delete".equals(action)) {
            experienceDao.deleteExperience(exp.getId(), userId);
        } else {
            exp.setCompany(request.getParameter("company"));
            exp.setRole(request.getParameter("role"));
            exp.setDuration(request.getParameter("duration"));
            exp.setDescription(request.getParameter("description"));

            if ("add".equals(action)) {
                experienceDao.addExperience(exp);
            } else if ("update".equals(action)) {
                experienceDao.updateExperience(exp);
            }
        }
        response.sendRedirect("dashboard?success=experience_updated");
    }
}
