package com.cvbuilder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Model: data CV user + validation logic. */
public class CVBean implements Serializable {
    private static final long serialVersionUID = 2L;

    // Personal Info
    private String fullName;
    private String professionalTitle;
    private String email;
    private String phone;
    private String profileUrl;
    private String profileSummary;

    // Education & Experience (boleh banyak)
    private List<Education> educations = new ArrayList<>();
    private List<Experience> experiences = new ArrayList<>();

    // Skills
    private String technicalSkills;
    private String softSkills;

    // Error hasil validate()
    private List<String> errors = new ArrayList<>();

    public CVBean() { }

    // validation 
    public boolean validate() {
        errors.clear();

        if (isEmpty(fullName)) {
            errors.add("Full Name is required.");
        }
        if (isEmpty(email)) {
            errors.add("Email is required.");
        } else if (!email.trim().matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$")) {
            errors.add("Email format is invalid (e.g. name@example.com).");
        }

        if (educations.isEmpty()) {
            errors.add("At least one Education entry (with a Degree) is required.");
        }
        int i = 1;
        for (Education e : educations) {
            if (isEmpty(e.getDegree())) {
                errors.add("Education #" + i + ": Degree is required.");
            }
            if (!isEmpty(e.getGraduationYear())
                    && !e.getGraduationYear().trim().matches("^(19|20)\\d{2}$")) {
                errors.add("Education #" + i + ": Year of Graduation must be a valid 4-digit year.");
            }
            i++;
        }

        i = 1;
        for (Experience x : experiences) {
            if (isEmpty(x.getJobTitle())) {
                errors.add("Experience #" + i + ": Job Title is required.");
            }
            i++;
        }

        if (getTechnicalSkillList().isEmpty() && getSoftSkillList().isEmpty()) {
            errors.add("At least one skill (technical or soft) is required.");
        }
        return errors.isEmpty();
    }

    private boolean isEmpty(String s) { return s == null || s.trim().isEmpty(); }

    //Helpers 
    public void addEducation(Education e) { educations.add(e); }
    public void addExperience(Experience x) { experiences.add(x); }

    public List<String> getTechnicalSkillList() { return split(technicalSkills); }
    public List<String> getSoftSkillList()      { return split(softSkills); }

    private List<String> split(String csv) {
        List<String> list = new ArrayList<>();
        if (csv != null) {
            for (String s : csv.split(",")) {
                if (!s.trim().isEmpty()) list.add(s.trim());
            }
        }
        return list;
    }

    //Getters & Setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getProfessionalTitle() { return professionalTitle; }
    public void setProfessionalTitle(String professionalTitle) { this.professionalTitle = professionalTitle; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getProfileUrl() { return profileUrl; }
    public void setProfileUrl(String profileUrl) { this.profileUrl = profileUrl; }
    public String getProfileSummary() { return profileSummary; }
    public void setProfileSummary(String profileSummary) { this.profileSummary = profileSummary; }

    public List<Education> getEducations() { return educations; }
    public void setEducations(List<Education> educations) { this.educations = educations; }
    public List<Experience> getExperiences() { return experiences; }
    public void setExperiences(List<Experience> experiences) { this.experiences = experiences; }

    public String getTechnicalSkills() { return technicalSkills; }
    public void setTechnicalSkills(String technicalSkills) { this.technicalSkills = technicalSkills; }
    public String getSoftSkills() { return softSkills; }
    public void setSoftSkills(String softSkills) { this.softSkills = softSkills; }

    public List<String> getErrors() { return errors; }
}