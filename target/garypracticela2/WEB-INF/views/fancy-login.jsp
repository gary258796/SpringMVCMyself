<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">

<head>
	
	<title>Login Page</title>
	<meta charset="UTF-8">
	<c:import url="/WEB-INF/include/inc.jsp"/>

</head>

<body>
<%--<c:if test="${ifLogin==true}">--%>
	<%--<c:import url="/WEB-INF/include/user_header.jsp"/>--%>
<%--</c:if>--%>
<%--<c:if test="${ifLogin==false}">--%>
	<%--<c:import url="/WEB-INF/include/login_header.jsp"/>--%>
<%--</c:if>--%>

	<div>

			<div id="loginbox" style="margin-top: 50px;"
				 class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="panel-title">Login to GaryProjects</div>
					</div>
					<div style="padding-top: 30px" class="panel-body">
						<!-- Login Form -->
						<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST" class="form-horizontal">


							<!-- Place for messages: error, alert etc ... -->
							<div class="form-group">
								<div class="col-xs-15">
									<div>

										<c:if test="${param.error != null}" >
											<div class="alert alert-danger col-xs-offset-1 col-xs-10">
												Invalid username and password.
											</div>
										</c:if>
										<c:if test="${param.logout != null}" >
											<div class="alert alert-success col-xs-offset-1 col-xs-10">
												You have been logged out.
											</div>
										</c:if>
									</div>
								</div>
							</div>

							<!-- User name -->
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>

								<input type="text" name="email" placeholder="email" class="form-control">
							</div>

							<!-- Password -->
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>

								<input type="password" name="password" placeholder="password" class="form-control" >
							</div>

							<!-- Login/Submit Button -->
							<div style="margin-top: 10px" class="form-group">
								<div class="col-sm-6 controls">
									<button type="submit" class="btn btn-success">Login</button>
								</div>
							</div>

						</form:form>

					</div>

				</div>


				<div>
					<a href="${pageContext.request.contextPath}/register/showRegistrationForm" class="btn btn-primary" role="button" aria-pressed="true">Register New User</a>
				</div>


			</div>


	</div>
</body>
</html>