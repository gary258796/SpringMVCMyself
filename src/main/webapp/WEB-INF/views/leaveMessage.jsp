<%--
  Created by IntelliJ IDEA.
  User: liaoyushao
  Date: 2020-03-09
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>留言</title>
    <meta charset="UTF-8">
    <c:import url="/WEB-INF/include/inc.jsp"/>
</head>
<body>

<h1>留個言給這個user</h1>
<c:if test="${ifLogin==true}">
    <form:form action="${pageContext.request.contextPath}/message/submitMsg" modelAttribute="message" method="post" role="form">
        <div>
            <div>
                <form:textarea path="message" id="message" class="form-control" rows="3"/>
            </div>
        </div>



        <input type="submit"  value="Send Message"/>
    </form:form>
</c:if>

<a href="${pageContext.request.contextPath}/search/returnHomePage">Return to my Home !</a>



</body>
</html>
