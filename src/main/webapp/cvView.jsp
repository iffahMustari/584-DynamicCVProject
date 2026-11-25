<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><c:out value="${cvData.fullName}"/> - CV</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<!-- Pilihan warna -->
<div class="theme-bar">
    <span>Colour theme:</span>
    <button type="button" class="active" data-theme="bw" onclick="setTheme('bw')">Black &amp; White</button>
    <button type="button" data-theme="blue" onclick="setTheme('blue')">Dark Blue</button>
</div>

<div class="cv theme-bw" id="cv">

    <div class="cv-header">
        <h1><c:out value="${cvData.fullName}"/></h1>
        <c:if test="${not empty cvData.professionalTitle}">
            <div class="title"><c:out value="${cvData.professionalTitle}"/></div>
        </c:if>
        <div class="contact">
            <c:out value="${cvData.email}"/>
            <c:if test="${not empty cvData.phone}"> | <c:out value="${cvData.phone}"/></c:if>
            <c:if test="${not empty cvData.profileUrl}"> | <c:out value="${cvData.profileUrl}"/></c:if>
        </div>
    </div>

    <c:if test="${not empty cvData.profileSummary}">
        <h2>Profile</h2>
        <p><c:out value="${cvData.profileSummary}"/></p>
    </c:if>

    <c:if test="${not empty cvData.educations}">
        <h2>Education</h2>
        <c:forEach var="ed" items="${cvData.educations}">
            <div class="cv-entry">
                <div class="entry-head">
                    <span><c:out value="${ed.degree}"/></span>
                    <span><c:out value="${ed.graduationYear}"/></span>
                </div>
                <div class="muted">
                    <c:out value="${ed.institution}"/>
                    <c:if test="${not empty ed.major}"> &mdash; <c:out value="${ed.major}"/></c:if>
                </div>
                <c:if test="${not empty ed.courseworkLines}">
                    <ul>
                        <c:forEach var="line" items="${ed.courseworkLines}">
                            <li><c:out value="${line}"/></li>
                        </c:forEach>
                    </ul>
                </c:if>
            </div>
        </c:forEach>
    </c:if>

    <c:if test="${not empty cvData.experiences}">
        <h2>Experience</h2>
        <c:forEach var="ex" items="${cvData.experiences}">
            <div class="cv-entry">
                <div class="entry-head">
                    <span><c:out value="${ex.jobTitle}"/></span>
                    <span><c:out value="${ex.duration}"/></span>
                </div>
                <div class="muted">
                    <c:out value="${ex.company}"/>
                    <c:if test="${not empty ex.location}"> &mdash; <c:out value="${ex.location}"/></c:if>
                </div>
                <c:if test="${not empty ex.responsibilityLines}">
                    <ul>
                        <c:forEach var="line" items="${ex.responsibilityLines}">
                            <li><c:out value="${line}"/></li>
                        </c:forEach>
                    </ul>
                </c:if>
            </div>
        </c:forEach>
    </c:if>

    <h2>Skills</h2>
    <c:if test="${not empty cvData.technicalSkillList}">
        <p><strong>Technical:</strong></p>
        <div class="skills">
            <c:forEach var="s" items="${cvData.technicalSkillList}"><span><c:out value="${s}"/></span></c:forEach>
        </div>
    </c:if>
    <c:if test="${not empty cvData.softSkillList}">
        <p><strong>Soft Skills:</strong></p>
        <div class="skills">
            <c:forEach var="s" items="${cvData.softSkillList}"><span><c:out value="${s}"/></span></c:forEach>
        </div>
    </c:if>
</div>

<div class="actions">
    <button class="btn btn-primary" onclick="window.print()">Print CV</button>
    <a class="btn btn-grey" href="${pageContext.request.contextPath}/cvForm.jsp">Back to Form (Edit)</a>
</div>

<script>
    function setTheme(theme) {
        document.getElementById('cv').className = 'cv theme-' + theme;
        var buttons = document.querySelectorAll('.theme-bar button');
        for (var i = 0; i < buttons.length; i++) {
            buttons[i].classList.toggle('active', buttons[i].getAttribute('data-theme') === theme);
        }
    }
</script>
</body>
</html>