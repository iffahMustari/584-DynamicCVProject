package com.cvbuilder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Model: satu entry pendidikan. */
public class Education implements Serializable {
    private static final long serialVersionUID = 1L;

    private String degree;
    private String institution;
    private String graduationYear;
    private String major;
    private String coursework;   // satu baris = satu point

    public Education() { }

    /** True kalau semua field kosong (entry kosong akan diabaikan). */
    public boolean isBlankEntry() {
        return blank(degree) && blank(institution) && blank(graduationYear)
                && blank(major) && blank(coursework);
    }

    public List<String> getCourseworkLines() {
        List<String> list = new ArrayList<>();
        if (coursework != null) {
            for (String s : coursework.split("\\r?\\n")) {
                if (!s.trim().isEmpty()) list.add(s.trim());
            }
        }
        return list;
    }

    private boolean blank(String s) { return s == null || s.trim().isEmpty(); }

    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }
    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }
    public String getGraduationYear() { return graduationYear; }
    public void setGraduationYear(String graduationYear) { this.graduationYear = graduationYear; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public String getCoursework() { return coursework; }
    public void setCoursework(String coursework) { this.coursework = coursework; }
}