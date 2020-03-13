
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: liaoyushao
  Date: 2020-03-01
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>


<html>

<head>
    <title> Home Page</title>
    <c:import url="/WEB-INF/include/inc.jsp"/>
</head>


<body>
<c:import url="/WEB-INF/include/user_header.jsp"/>


<div>

    <h1>${hostUser.userName} Home Page</h1>
    <div>
        <div>
            <c:if test="${hostUser.imgUrl == null}">
                <img src="${pageContext.request.contextPath}/upload/nopic.jpg" width="100" height="100"/>
            </c:if>
            <c:if test="${hostUser.imgUrl != null}">
                <img src="${pageContext.request.contextPath}/upload/${hostUser.imgUrl}" width="100" height="100"/>
            </c:if>

        </div>
        <div>

            <div>
                <div>Name：${hostUser.userName}</div>
            </div>
            <div>
                <div>Mail：${hostUser.email}</div>
            </div>

        </div>

    </div>

</div>

<a href="${pageContext.request.contextPath}/search/returnHomePage" >Return to my home page!</a>


<form:form action="${pageContext.request.contextPath}/logout" method="POST" >
    <input type="submit" value="logout">
</form:form>

</body>
</html>
