<%--
  Created by IntelliJ IDEA.
  User: liaoyushao
  Date: 2020-03-20
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


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

<script language="JavaScript" type="text/javascript">
    function Validate ( form ){
        if (form.message.value == "") {
            alert( "Please Enter message" );
            form.message.focus();
            return false ;
        }
        return true ;
    }
</script>

<nav role="navigation">
    <div class="main">

        <div class="msgPost">

            <form:form action="${pageContext.request.contextPath}/message/submitMsg" modelAttribute="message" method="post" role="form" onSubmit="return Validate(this);">
                <div>
                    <div>
                        <form:textarea cols="50" rows="5" path="message" name="message" id="message" class="form-control" placeholder="Share something..."/>
                    </div>
                </div>

                <input type="submit"  value="Share" />
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

                                <h1>User ${message.fromUserName} say : </h1><br>
                                <h2>  ${message.message} </h2><br>
                                <h4>at time : ${message.date}</h4>
                            </div>
                        </div>
                        <div>
                            <div>
                                <c:if test="${curUser == hostUser}">
                                    <a href="${pageContext.request.contextPath}/user/deleteMessage-${message.id}">删除留言</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</nav>


