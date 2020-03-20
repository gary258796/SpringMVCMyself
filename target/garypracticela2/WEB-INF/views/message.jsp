<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <title>所有留言</title>
    <meta charset="UTF-8">
    <c:import url="/WEB-INF/include/inc.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/include/user_header.jsp"/>
<c:import url="/WEB-INF/include/left_sidebar.jsp" />
<h1>用戶留言</h1>
<div>
    <div>
        <c:forEach var="message" items="${messages}">
            <div>
                <div>
                    <div>

                        <h1>User ${message.fromusername} say :  ${message.message} </h1><br>
                        <h3>at time : ${message.date}</h3>
                    </div>
                </div>
                <div>
                    <div>
                        <a href="${pageContext.request.contextPath}/user/deleteMessage-${message.id}">删除留言</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div></div>

</div>

</body>
</html>
