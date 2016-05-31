<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 24.05.2016
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<c:choose>
    <c:when test="${sessionScope.user == null}">
        <p class="sign_in">
            Выполните  <a href="signin.jsp">Вход</a>
        </p>
    </c:when>
    <c:otherwise>

        <a href="find_user_by_id.action?user_id=${sessionScope.user.getId()}">
            ${sessionScope.user.getLogin()}
        </a>

        <br>
        <a href="logout_user.action">Выйти</a>
    </c:otherwise>
</c:choose>
