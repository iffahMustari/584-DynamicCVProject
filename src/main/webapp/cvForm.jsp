<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dynamic CV Builder</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%-- d = draft dari session (kosong kalau user belum submit apa-apa) --%>
<c:set var="d" value="${sessionScope.cvDraft}"/>

<div class="container">
    <h1>Dynamic Online CV Builder</h1>
    <p>Fields marked <span class="required">*</span> are mandatory.</p>

    <p>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/CVServlet?action=sample">Preview Sample CV</a>
        <a class="btn btn-grey" href="${pageContext.request.contextPath}/CVServlet?action=new">Clear Form</a>
    </p>

    <form action="${pageContext.request.contextPath}/CVServlet" method="post">

        <fieldset>
            <legend>Personal Information</legend>
            <label>Full Name <span class="required">*</span></label>
            <input type="text" name="fullName" value="<c:out value="${d.fullName}"/>" required>
            <label>Professional Title</label>
            <input type="text" name="professionalTitle" value="<c:out value="${d.professionalTitle}"/>">
            <label>Email <span class="required">*</span></label>
            <input type="email" name="email" value="<c:out value="${d.email}"/>" required>
            <label>Phone Number</label>
            <input type="tel" name="phone" value="<c:out value="${d.phone}"/>">
            <label>LinkedIn / GitHub URL</label>
            <input type="url" name="profileUrl" placeholder="https://" value="<c:out value="${d.profileUrl}"/>">
            <label>Profile Summary</label>
            <textarea name="profileSummary" rows="4"><c:out value="${d.profileSummary}"/></textarea>
        </fieldset>

        <!-- education -->
        <fieldset>
            <legend>Education</legend>
            <div id="eduList">
                <c:forEach var="e" items="${d.educations}">
                    <div class="entry">
                        <div class="entry-bar"><strong>Education</strong>
                            <button type="button" class="btn-remove" onclick="removeEntry(this)">Remove</button></div>
                        <label>Degree / Qualification <span class="required">*</span></label>
                        <input type="text" name="degree" value="<c:out value="${e.degree}"/>">
                        <label>Institution Name</label>
                        <input type="text" name="institution" value="<c:out value="${e.institution}"/>">
                        <label>Year of Graduation</label>
                        <input type="text" name="graduationYear" maxlength="4" placeholder="2025" value="<c:out value="${e.graduationYear}"/>">
                        <label>Major / Field</label>
                        <input type="text" name="major" value="<c:out value="${e.major}"/>">
                        <label>Additional Coursework / Achievements (satu baris satu point)</label>
                        <textarea name="coursework" rows="3" placeholder="CGPA 3.70&#10;Dean's List: Sem 1, 2, 3"><c:out value="${e.coursework}"/></textarea>
                    </div>
                </c:forEach>
            </div>
            <button type="button" class="btn btn-add" onclick="addEntry('eduList','eduTemplate')">+ Add Education</button>
        </fieldset>

        <!-- experience -->
        <fieldset>
            <legend>Experience</legend>
            <div id="expList">
                <c:forEach var="x" items="${d.experiences}">
                    <div class="entry">
                        <div class="entry-bar"><strong>Experience</strong>
                            <button type="button" class="btn-remove" onclick="removeEntry(this)">Remove</button></div>
                        <label>Job Title</label>
                        <input type="text" name="jobTitle" value="<c:out value="${x.jobTitle}"/>">
                        <label>Company Name</label>
                        <input type="text" name="company" value="<c:out value="${x.company}"/>">
                        <label>Location</label>
                        <input type="text" name="location" placeholder="Kuala Lumpur, Malaysia" value="<c:out value="${x.location}"/>">
                        <label>Duration</label>
                        <input type="text" name="duration" placeholder="Jan 2024 - Dec 2024" value="<c:out value="${x.duration}"/>">
                        <label>Key Responsibilities / Achievements (satu baris satu point)</label>
                        <textarea name="responsibilities" rows="4"><c:out value="${x.responsibilities}"/></textarea>
                    </div>
                </c:forEach>
            </div>
            <button type="button" class="btn btn-add" onclick="addEntry('expList','expTemplate')">+ Add Experience</button>
        </fieldset>

        <fieldset>
            <legend>Skills</legend>
            <label>Technical Skills (comma-separated) <span class="required">*</span></label>
            <input type="text" name="technicalSkills" placeholder="Java, SQL, HTML" value="<c:out value="${d.technicalSkills}"/>">
            <label>Soft Skills (comma-separated)</label>
            <input type="text" name="softSkills" placeholder="Teamwork, Communication" value="<c:out value="${d.softSkills}"/>">
        </fieldset>

        <button type="submit" class="btn btn-primary">Generate CV</button>
    </form>
</div>

<!-- Template kosong untuk butang "+ Add" (tak dihantar bersama form) -->
<template id="eduTemplate">
    <div class="entry">
        <div class="entry-bar"><strong>Education</strong>
            <button type="button" class="btn-remove" onclick="removeEntry(this)">Remove</button></div>
        <label>Degree / Qualification <span class="required">*</span></label>
        <input type="text" name="degree">
        <label>Institution Name</label>
        <input type="text" name="institution">
        <label>Year of Graduation</label>
        <input type="text" name="graduationYear" maxlength="4" placeholder="2025">
        <label>Major / Field</label>
        <input type="text" name="major">
        <label>Additional Coursework / Achievements (satu baris satu point)</label>
        <textarea name="coursework" rows="3" placeholder="CGPA 3.70&#10;Dean's List: Sem 1, 2, 3"></textarea>
    </div>
</template>

<template id="expTemplate">
    <div class="entry">
        <div class="entry-bar"><strong>Experience</strong>
            <button type="button" class="btn-remove" onclick="removeEntry(this)">Remove</button></div>
        <label>Job Title</label>
        <input type="text" name="jobTitle">
        <label>Company Name</label>
        <input type="text" name="company">
        <label>Location</label>
        <input type="text" name="location" placeholder="Kuala Lumpur, Malaysia">
        <label>Duration</label>
        <input type="text" name="duration" placeholder="Jan 2024 - Dec 2024">
        <label>Key Responsibilities / Achievements (satu baris satu point)</label>
        <textarea name="responsibilities" rows="4"></textarea>
    </div>
</template>

<script>
    // Tambah satu entry kosong dari template
    function addEntry(listId, templateId) {
        var list = document.getElementById(listId);
        var tpl = document.getElementById(templateId);
        list.appendChild(tpl.content.cloneNode(true));
    }
    // Buang entry
    function removeEntry(btn) {
        btn.closest('.entry').remove();
    }
    // Kalau takde entry langsung (form baru), bagi satu kosong selaluh
    if (!document.querySelector('#eduList .entry')) addEntry('eduList', 'eduTemplate');
    if (!document.querySelector('#expList .entry')) addEntry('expList', 'expTemplate');
</script>
</body>
</html>