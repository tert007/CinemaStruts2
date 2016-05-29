<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<html>
<head>
    <title>Профиль пользователя</title>
    <jsp:include page="included_head.jsp"/>
    <script src="js/included_confirm_script.js" type="text/javascript"></script>
</head>

<body>

<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation"><a href="index.jsp">Главная</a></li>
                <li role="presentation"><a href="Controller?command=get_today_seances">Сеансы</a></li>
                <li role="presentation"><a href="Controller?command=get_films_collection">Фильмы</a></li>
                <li role="presentation" class="active"><a
                        href="Controller?command=get_users_collection">Пользователи</a></li>
            </ul>
        </nav>
        <jsp:include page="included_user_profile.jsp"/>
    </div>


    <div class="jumbotron">
        <table class="table">
            <caption>Все пользователи</caption>
            <tr>
                <td>login</td>
                <td>Email</td>
                <td>Количество бонусов</td>
                <td>Тип пользователя</td>
                <td></td>

            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.getLogin()}</td>
                    <td>${user.getEmail()}</td>
                    <td>${user.getBonusCount()}</td>
                    <td>${user.getUserType()} </td>
                    <td>
                        <c:if test="${sessionScope.user.getUserType().toString() eq 'ADMIN'}">
                            <a href="Controller?command=find_user_by_id&user_id=${user.getId()}" style="float: left"><span
                                    class="glyphicon glyphicon-edit"></span></a>
                            <a href="javascript: confirmMessage('Controller?command=remove_user&user_id=${user.getId()}')" class="glyphicon glyphicon-remove remove_btn"></a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

        </table>
        <c:if test="${sessionScope.user.getUserType().toString() eq 'ADMIN'}">
            <a href="add_user.jsp" class="btn btn-primary">Добавить пользователя</a>
        </c:if>
    </div>



    <footer class="footer">
        <p>&copy; 2016 Cinemator, Inc.</p>
    </footer>

</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
</body>

<!-- Mirrored from getbootstrap.com/examples/jumbotron-narrow/ by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 21 May 2016 20:34:48 GMT -->
</html>
