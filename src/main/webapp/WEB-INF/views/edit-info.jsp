<%--
  Created by IntelliJ IDEA.
  User: liaoyushao
  Date: 2020-03-03
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.gary.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>



<!doctype html>
<html lang="en">

<head>

    <title>Update your profile</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Reference Bootstrap files -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

    <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        .error {color:red}
    </style>

</head>

<body>

<div>

    <div id="loginbox" style="margin-top: 50px;"
         class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

        <div class="panel panel-primary">

            <div class="panel-heading">
                <div class="panel-title">Update profile</div>
            </div>

            <div style="padding-top: 30px" class="panel-body">

                <!-- Registration Form -->
                <form:form action="${pageContext.request.contextPath}/editProfile/submitUpdate"
                        modelAttribute="curUser" class="form-horizontal" method="post" role="form">

                    <!-- Place for messages: error, alert etc ... -->
                    <div class="form-group">
                        <div class="col-xs-15">
                            <div>

                                <!-- Check for registration error -->
                                <c:if test="${registrationError != null}">

                                    <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                            ${registrationError}
                                    </div>

                                </c:if>

                            </div>
                        </div>
                    </div>

                    <form:hidden path="id" />
                    <form:hidden path="imgUrl" />

                    <!-- User name -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <label>User Name :</label><br>
                        <form:errors path="userName" cssClass="error" />
                        <form:input path="userName" class="form-control" />
                    </div>

                    <!-- Email -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <label>Email :</label><br>
                        <form:errors path="email" cssClass="error" />
                        <form:input path="email" class="form-control" id="email" />
                    </div>

                    <!-- Email -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <label>Password :</label><br>
                        <form:errors path="password" cssClass="error" />
                        <form:password path="password" class="form-control" id="password"/>
                    </div>

                    <!-- Register Button -->
                    <div style="margin-top: 10px" class="form-group">
                        <div class="col-sm-6 controls">
                            <button type="submit" class="btn btn-primary">Update</button>
                        </div>
                    </div>

                </form:form>

            </div>

        </div>

    </div>

</div>

</body>
</html>