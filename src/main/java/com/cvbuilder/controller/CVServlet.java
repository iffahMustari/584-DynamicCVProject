package com.cvbuilder.controller;

import com.cvbuilder.model.CVBean;
import com.cvbuilder.model.Education;
import com.cvbuilder.model.Experience;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** Controller: GET (?action=sample / ?action=new) dan POST (form submission). */
public class CVServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("sample".equals(action)) {
            // Sample tak disimpan dalam session supaya tak overwrite draft user
            request.setAttribute("cvData", createSampleCV());
            request.getRequestDispatcher("cvView.jsp").forward(request, response);

        } else if ("new".equals(action)) {
            // Kosongkan draft, mula form baru
            request.getSession().removeAttribute("cvDraft");
            response.sendRedirect(request.getContextPath() + "/cvForm.jsp");

        } else {
            response.sendRedirect(request.getContextPath() + "/cvForm.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 1. Populate bean dari form
        CVBean cv = new CVBean();
        cv.setFullName(request.getParameter("fullName"));
        cv.setProfessionalTitle(request.getParameter("professionalTitle"));
        cv.setEmail(request.getParameter("email"));
        cv.setPhone(request.getParameter("phone"));
        cv.setProfileUrl(request.getParameter("profileUrl"));
        cv.setProfileSummary(request.getParameter("profileSummary"));
        cv.setTechnicalSkills(request.getParameter("technicalSkills"));
        cv.setSoftSkills(request.getParameter("softSkills"));

        // Education (banyak entry: parameter sama nama, ambil sebagai array)
        String[] degree = request.getParameterValues("degree");
        String[] institution = request.getParameterValues("institution");
        String[] gradYear = request.getParameterValues("graduationYear");
        String[] major = request.getParameterValues("major");
        String[] coursework = request.getParameterValues("coursework");
        int eduCount = (degree == null) ? 0 : degree.length;
        for (int i = 0; i < eduCount; i++) {
            Education e = new Education();
            e.setDegree(at(degree, i));
            e.setInstitution(at(institution, i));
            e.setGraduationYear(at(gradYear, i));
            e.setMajor(at(major, i));
            e.setCoursework(at(coursework, i));
            if (!e.isBlankEntry()) cv.addEducation(e);   // skip entry kosong
        }

        // Experience (banyak entry)
        String[] jobTitle = request.getParameterValues("jobTitle");
        String[] company = request.getParameterValues("company");
        String[] location = request.getParameterValues("location");
        String[] duration = request.getParameterValues("duration");
        String[] resp = request.getParameterValues("responsibilities");
        int expCount = (jobTitle == null) ? 0 : jobTitle.length;
        for (int i = 0; i < expCount; i++) {
            Experience x = new Experience();
            x.setJobTitle(at(jobTitle, i));
            x.setCompany(at(company, i));
            x.setLocation(at(location, i));
            x.setDuration(at(duration, i));
            x.setResponsibilities(at(resp, i));
            if (!x.isBlankEntry()) cv.addExperience(x);
        }

        // 2. Simpan draft dalam session (supaya form auto-isi bila user back/edit)
        request.getSession().setAttribute("cvDraft", cv);

        // 3. Validate & forward
        if (cv.validate()) {
            request.setAttribute("cvData", cv);
            request.getRequestDispatcher("cvView.jsp").forward(request, response);
        } else {
            request.setAttribute("errors", cv.getErrors());
            request.setAttribute("errorMessage", "Please correct the following problems:");
            request.getRequestDispatcher("cvError.jsp").forward(request, response);
        }
    }

    /** Ambil elemen array dengan selamat. */
    private String at(String[] arr, int i) {
        return (arr != null && i < arr.length) ? arr[i] : "";
    }

    // ---------- Sample data ----------
    private CVBean createSampleCV() {
        CVBean cv = new CVBean();
        cv.setFullName("Aisyah Binti Rahman");
        cv.setProfessionalTitle("Junior Software Engineer");
        cv.setEmail("aisyah.rahman@example.com");
        cv.setPhone("+60 12-345 6789");
        cv.setProfileUrl("https://github.com/aisyahrahman");
        cv.setProfileSummary("Motivated computer science graduate with hands-on experience in "
                + "Java EE web development and a strong interest in building clean, "
                + "maintainable enterprise applications.");

        Education e1 = new Education();
        e1.setDegree("Bachelor of Computer Science (Hons.)");
        e1.setInstitution("Universiti Teknologi MARA");
        e1.setGraduationYear("2025");
        e1.setMajor("Netcentric Computing");
        e1.setCoursework("CGPA 3.72 / 4.00\nDean's List: Semester 2, 3, 4 and 6\n"
                + "Final Year Project: Online Learning Platform");
        cv.addEducation(e1);

        Education e2 = new Education();
        e2.setDegree("Diploma in Computer Science");
        e2.setInstitution("Universiti Teknologi MARA");
        e2.setGraduationYear("2022");
        e2.setMajor("Computer Science");
        e2.setCoursework("CGPA 3.55 / 4.00\nDean's List: Semester 3 and 4");
        cv.addEducation(e2);

        Experience x1 = new Experience();
        x1.setJobTitle("Software Engineer Intern");
        x1.setCompany("TechNusantara Sdn. Bhd.");
        x1.setLocation("Kuala Lumpur, Malaysia");
        x1.setDuration("Mar 2025 - Aug 2025");
        x1.setResponsibilities("Developed web modules using Java Servlets and JSP\n"
                + "Reduced page load time by 30% through query optimisation\n"
                + "Collaborated with a team of 5 using Git and Agile methodology");
        cv.addExperience(x1);

        Experience x2 = new Experience();
        x2.setJobTitle("Part-time Web Assistant");
        x2.setCompany("Kedai Digital Terengganu");
        x2.setLocation("Kuala Terengganu, Malaysia");
        x2.setDuration("Jun 2023 - Dec 2023");
        x2.setResponsibilities("Maintained the company website and product listings\n"
                + "Assisted customers with online order enquiries");
        cv.addExperience(x2);

        cv.setTechnicalSkills("Java, JSP, Servlet, MySQL, HTML, CSS, Git");
        cv.setSoftSkills("Communication, Teamwork, Problem Solving, Time Management");
        return cv;
    }
}