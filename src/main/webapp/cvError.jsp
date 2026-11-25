<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Validation Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1>Oops, something is missing</h1>
    <div class="error-box">
        <p><strong><c:out value="${errorMessage}"/></strong></p>
        <ul>
            <c:forEach var="err" items="${errors}">
                <li><c:out value="${err}"/></li>
            </c:forEach>
        </ul>
    </div>
    <p>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/cvForm.jsp">Go Back &amp; Correct</a>
        <a class="btn btn-grey" href="${pageContext.request.contextPath}/CVServlet?action=new">Start New Form</a>
    </p>
</div>
</body>
</html>