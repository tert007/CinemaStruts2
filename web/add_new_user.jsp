<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<html>
<head>
    <title>Добовление новоого пользователя</title>
    <jsp:include page="included_head.jsp"/>
</head>
<body>
<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation"><a href="index.jsp">Главная</a></li>
                <li role="presentation"><a href="get_today_seances.action">Сеансы</a></li>
                <li role="presentation"><a href="get_films_collection.action">Фильмы</a></li>
                <li role="presentation" class="active"><a href="get_users_collection.action">Пользователи</a></li>
            </ul>
        </nav>
        <jsp:include page="included_user_profile.jsp"/>
    </div>


    <div class="jumbotron">
        <c:choose>
            <c:when test="${sessionScope.user.getUserType() eq 'ADMIN'}">
                <form class="form-horizontal" action="add_new_user.action" method="post">
                    <div class="form-group">
                        <label for="login" class="col-sm-2 control-label">Логин:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="login" value="" id="login">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">Пароль:</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" name="password" value="" id="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">Email:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="email" id="email" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bonus_count" class="col-sm-2 control-label">Бонусы:</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" name="bonus_count"  id="bonus_count" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="user_type" class="col-sm-2 control-label">Тип:</label>
                        <div class="col-sm-10">
                            <select name="user_type" id="user_type" class="form-control">
                                <option value="USER" selected>USER</option>
                                <option value="ADMIN">ADMIN</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary" value="Добавить!">
                    </div>
                    <s:actionerror/>
                </form>
            </c:when>
        </c:choose>
    </div>
</div>
</body>
</html>
