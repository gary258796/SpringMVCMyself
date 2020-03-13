<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<nav role="navigation">
    <div>
        <div>
            <a>Message Board</a>
        </div>
        <div>
            <ul>

                <c:if test="${curUser != hostUser }">
                <li><a href="${pageContext.request.contextPath}/message/submitMsg">Leave Message</a></li>
                </c:if>

                <c:if test="${curUser == hostUser }">
                <li><a href="${pageContext.request.contextPath}/message/seeMsg-1">See comment</a></li>
                <li><a href="${pageContext.request.contextPath}/home">Back to Home Page</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>