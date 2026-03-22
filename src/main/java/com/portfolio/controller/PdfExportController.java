package com.portfolio.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.portfolio.dao.*;
import com.portfolio.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/exportPdf")
public class PdfExportController extends HttpServlet {
    private ProfileDao profileDao;
    private EducationDao educationDao;
    private ProjectDao projectDao;
    private SkillDao skillDao;
    private ExperienceDao experienceDao;

    @Override
    public void init() {
        profileDao = new ProfileDao();
        educationDao = new EducationDao();
        projectDao = new ProjectDao();
        skillDao = new SkillDao();
        experienceDao = new ExperienceDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");
        if (userIdStr == null || userIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is required");
            return;
        }

        try {
            int userId = Integer.parseInt(userIdStr);
            Profile profile = profileDao.getProfileByUserId(userId);
            
            if (profile == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Profile not found");
                return;
            }

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" + profile.getFullName().replaceAll("\\s+", "_") + "_Portfolio.pdf");

            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

            Paragraph name = new Paragraph(profile.getFullName() != null ? profile.getFullName() : "Name Not Set", titleFont);
            name.setAlignment(Element.ALIGN_CENTER);
            document.add(name);

            Paragraph title = new Paragraph(profile.getTitle() != null ? profile.getTitle() : "Title Not Set", headerFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            document.add(new Paragraph(" "));
            
            List<Experience> expList = experienceDao.getAllExperienceByUserId(userId);
            if (!expList.isEmpty()) {
                document.add(new Paragraph("Experience", headerFont));
                for (Experience exp : expList) {
                    document.add(new Paragraph("- " + exp.getRole() + " at " + exp.getCompany() + " (" + exp.getDuration() + ")", normalFont));
                }
                document.add(new Paragraph(" "));
            }

            List<Education> eduList = educationDao.getAllEducationByUserId(userId);
            if (!eduList.isEmpty()) {
                document.add(new Paragraph("Education", headerFont));
                for (Education edu : eduList) {
                    document.add(new Paragraph("- " + edu.getDegree() + " from " + edu.getInstitution() + " (" + edu.getPassingYear() + ")", normalFont));
                }
                document.add(new Paragraph(" "));
            }

            List<Project> projList = projectDao.getAllProjectsByUserId(userId);
            if (!projList.isEmpty()) {
                document.add(new Paragraph("Projects", headerFont));
                for (Project proj : projList) {
                    document.add(new Paragraph("- " + proj.getTitle() + ": " + proj.getTechnologies(), normalFont));
                }
                document.add(new Paragraph(" "));
            }

            List<Skill> skillList = skillDao.getAllSkillsByUserId(userId);
            if (!skillList.isEmpty()) {
                document.add(new Paragraph("Skills", headerFont));
                for (Skill skill : skillList) {
                    document.add(new Paragraph("- " + skill.getSkillName() + " (" + skill.getSkillLevel() + ")", normalFont));
                }
            }

            document.close();
            
        } catch (Exception e) {
            throw new ServletException("Error exporting PDF", e);
        }
    }
}
