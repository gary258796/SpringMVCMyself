<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<style>
    .sideNav {
        height: 100%;
        width: 200px;
        position: fixed;
        z-index: 1;
        top: 0;
        left: 0;
        background-color: #CCFF99;
        overflow-x: hidden;
        padding-top: 20px;
    }
</style>




<nav role="navigation">

    <div class="sideNav">

        <!-- 在自己頁面的時候 -->
        <c:if test="${curUser == hostUser}">
            <h4>${curUser.userName} Home Page</h4>

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
                    <div>Name：${curUser.userName}</div>
                </div>
                <div>
                    <div>Mail：${curUser.email}</div>
                </div>

                <div>
                    <a href="${pageContext.request.contextPath}/editProfile/edit-${curUser.id}">Update Profile</a>
                </div>
            </div>

            <br>
        </c:if>


        <!-- 在別人頁面的時候 -->
        <c:if test="${curUser != hostUser}">

            <h4>${hostUser.userName} Home Page</h4>

            <div>
                <c:if test="${hostUser.imgUrl == null}">
                    <img src="${pageContext.request.contextPath}/upload/nopic.jpg" width="100" height="100"/>
                </c:if>
                <c:if test="${hostUser.imgUrl != null}">
                    <img src="${pageContext.request.contextPath}/upload/${hostUser.imgUrl}" width="100" height="100"/>
                </c:if>
            </div>
            <div>

                <div>
                    <div>Name：${hostUser.userName}</div>
                </div>
                <div>
                    <div>Mail：${hostUser.email}</div>
                </div>

            </div>
            <br>
        </c:if>
    </div>
</nav>