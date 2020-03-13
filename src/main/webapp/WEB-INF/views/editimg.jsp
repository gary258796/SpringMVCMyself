<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <title>register</title>
    <meta charset="UTF-8">
</head>
<body>

<SCRIPT LANGUAGE="JavaScript">
    function loadImageFile(event){
        var image = document.getElementById('image');
        image.src = URL.createObjectURL(event.target.files[0]);
    };
    //  onchange="loadImageFile(event)"
</SCRIPT>

<h1>Update Your Photo!</h1>
<div>
    <div>
        <c:if test="${user.imgUrl == null}">
            <img id="image" src="${pageContext.request.contextPath}/upload/nopic.jpg" width="100" height="100">
        </c:if>
        <c:if test="${user.imgUrl != null}">
            <img id="image" src="${pageContext.request.contextPath}/upload/${curUser.imgUrl}" width="100" height="100">
        </c:if>
    </div>
    <div>

        <form:form action="${pageContext.request.contextPath}/editProfile/uploadImage?${_csrf.parameterName}=${_csrf.token}"
                   enctype="multipart/form-data" method="post" role="form">
            <div>
                <label>File to upload: </label>
                <input type="file" name="uploadfile" accept="image/png,image/jpeg" onchange="loadImageFile(event)" /> <br>
            </div>
            <div>
                <input type="submit" value="Upload">
            </div>
        </form:form>

    </div>
</div>
</body>
</html>
