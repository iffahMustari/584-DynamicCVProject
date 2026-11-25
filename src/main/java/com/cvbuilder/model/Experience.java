package com.cvbuilder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Model: satu entry pengalaman kerja. */
public class Experience implements Serializable {
    private static final long serialVersionUID = 1L;

    private String jobTitle;
    private String company;
    private String location;          // contoh: Kuala Lumpur, Malaysia
    private String duration;
    private String responsibilities;  // satu baris = satu point

    public Experience() { }

    public boolean isBlankEntry() {
        return blank(jobTitle) && blank(company) && blank(location)
                && blank(duration) && blank(responsibilities);
    }

    public List<String> getResponsibilityLines() {
        List<String> list = new ArrayList<>();
        if (responsibilities != null) {
            for (String s : responsibilities.split("\\r?\\n")) {
                if (!s.trim().isEmpty()) list.add(s.trim());
            }
        }
        return list;
    }

    private boolean blank(String s) { return s == null || s.trim().isEmpty(); }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public String getResponsibilities() { return responsibilities; }
    public void setResponsibilities(String responsibilities) { this.responsibilities = responsibilities; }
}