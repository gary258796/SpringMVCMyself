
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
    <title>Home Page</title>
    <c:import url="/WEB-INF/include/inc.jsp"/>
</head>




<body>
<c:import url="/WEB-INF/include/user_header.jsp"/>
<div>

    <div>

        <form id="message_form" action="${pageContext.request.contextPath}/searchOthers" method="GET">

            <input type="text" id="findName" placeholder="Search other user by username.." name="findName" >

            <input type="submit" id="submit_message" value="送出" />

        </form>


    </div>

    <h1>${curUser.userName} Home Page</h1>
    <div>
        <div>
            <c:if test="${curUser.imgUrl == null}">
                <img src="${pageContext.request.contextPath}/upload/nopic.jpg" width="100" height="100"/>
            </c:if>
            <c:if test="${curUser.imgUrl != null}">
                <img src="${pageContext.request.contextPath}/upload/${curUser.imgUrl}" width="100" height="100"/>
            </c:if>
            <div>
                <div>
                    <a href="${pageContext.request.contextPath}/editProfile/edit-img">Change your photo!</a>
                </div>
            </div>
        </div>
        <div>
            <div>
                <div>User id：${curUser.id}</div>
            </div>
            <div>
                <div>Name：${curUser.userName}</div>
            </div>
            <div>
                <div>Mail：${curUser.email}</div>
            </div>
            <%--<div>--%>
                <%--<div>Password：${curUser.password}</div>--%>
            <%--</div>--%>
            <div>
                <a href="${pageContext.request.contextPath}/editProfile/edit-${curUser.id}">Update Profile</a>
            </div>
        </div>

    </div>
</div>


<form:form action="${pageContext.request.contextPath}/logout" method="POST" >
    <input type="submit" value="logout">
</form:form>

</body>
</html>
