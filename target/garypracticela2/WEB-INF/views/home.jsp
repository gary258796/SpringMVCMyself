
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

    <style>
        .main
        {
            margin-left: 200px;
        }

        .msgPost {
            margin-left: 300px;
            width: 50%;
            padding: 10px 20px;
            box-sizing: border-box;
        }

        .msgShow
        {
            margin-left: 300px;
        }
    </style>
</head>

<body>

    <c:import url="/WEB-INF/include/user_header.jsp"/>
    <c:import url="/WEB-INF/include/left_sidebar.jsp" />

    <div class="main">

        <div class="msgPost">

            <form:form action="${pageContext.request.contextPath}/message/submitMsg" modelAttribute="message" method="post" role="form">
                <div>
                    <div>
                        <form:textarea cols="50" rows="5" path="message" id="message" class="form-control" placeholder="Share something with others...."/>
                    </div>
                </div>

                <input type="submit"  value="Share"/>
            </form:form>

            <br><br>
            ------------------------------------------------------------------------------------
        </div>


        <div class="msgShow">
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
        </div>
    </div>
</body>
</html>
