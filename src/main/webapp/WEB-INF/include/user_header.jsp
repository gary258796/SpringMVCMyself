<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>

    .topnav {
        overflow: hidden;
        background-color: #333;
        margin-left: 200px;
    }

    .topnav a {
        float: left;
        color: #f2f2f2;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        font-size: 17px;
    }

    .topnav a:hover {
        background-color: #ddd;
        color: black;
    }

</style>

<nav role="navigation">
        <div class="topnav" >
            <ul>

                <a>
                <form id="message_form" action="${pageContext.request.contextPath}/searchOthers" method="GET">

                    <input type="text" id="findName" placeholder="Search other user by username.." name="findName" >
                    <input type="submit" id="submit_message" value="送出" />

                </form>
                </a>


                <a href="${pageContext.request.contextPath}/home">Home</a>

                <a>
                <form:form action="${pageContext.request.contextPath}/logout" method="POST" >
                    <input type="submit" value="logout">
                </form:form>
                </a>

            </ul>
        </div>
</nav>