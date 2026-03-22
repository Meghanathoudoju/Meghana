package com.portfolio.controller;

import com.portfolio.dao.EducationDao;
import com.portfolio.model.Education;
import com.portfolio.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/education")
public class EducationController extends HttpServlet {
    private EducationDao educationDao;

    @Override
    public void init() {
        educationDao = new EducationDao();
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

        Education edu = new Education();
        edu.setUserId(userId);
        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
            edu.setId(Integer.parseInt(request.getParameter("id")));
        }
        
        if ("delete".equals(action)) {
            educationDao.deleteEducation(edu.getId(), userId);
        } else {
            edu.setDegree(request.getParameter("degree"));
            edu.setInstitution(request.getParameter("institution"));
            edu.setPassingYear(Integer.parseInt(request.getParameter("passingYear")));
            edu.setDescription(request.getParameter("description"));

            if ("add".equals(action)) {
                educationDao.addEducation(edu);
            } else if ("update".equals(action)) {
                educationDao.updateEducation(edu);
            }
        }
        response.sendRedirect("dashboard?success=education_updated");
    }
}
