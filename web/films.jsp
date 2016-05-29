<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<html>
<head>
    <title>Фильмы</title>
    <jsp:include page="included_head.jsp"/>
    <script src="js/included_confirm_script.js" type="text/javascript"></script>
</head>

<body>

<div class="container">
    <div class="header clearfix">
        <nav>
            <c:choose>
                <c:when test="${sessionScope.user.getUserType() eq 'ADMIN'}">
                    <ul class="nav nav-pills pull-right">
                        <li role="presentation"><a href="index.jsp">Главная</a></li>
                        <li role="presentation"><a href="Controller?command=get_today_seances">Сеансы</a></li>
                        <li role="presentation" class="active"><a
                                href="Controller?command=get_films_collection">Фильмы</a></li>
                        <li role="presentation"><a href="Controller?command=get_users_collection">Пользователи</a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="nav nav-pills pull-right">
                        <li role="presentation"><a href="index.jsp">Главная</a></li>
                        <li role="presentation"><a href="Controller?command=get_today_seances">Сеансы</a></li>
                        <li role="presentation" class="active"><a
                                href="Controller?command=get_films_collection">Фильмы</a></li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </nav>
        <jsp:include page="included_user_profile.jsp"/>
    </div>

    <div class="jumbotron" id="main">
        <table class="table">
            <caption>Фильмы</caption>
            <tr>
                <td>Название</td>
                <td>Жанр</td>
                <td>Дата выпуска</td>
                <td>Режиссер</td>
                <td>Возрастные ограничения</td>
                <td></td>
            </tr>

            <c:forEach var="film" items="${films}">
                <tr>
                    <td>${film.getTitle()}</td>
                    <td>${film.getGenre()}</td>
                    <td>${film.getDateByString()}</td>
                    <td>${film.getDirector()}</td>
                    <td>${film.getAgeLimitation()}</td>
                    <td>
                        <a class="btn-primary btn-sm"
                           href="Controller?command=find_seances_by_film&film_id=${film.getId()}">Сеансы</a>
                        <c:choose>
                            <c:when test="${sessionScope.user.getUserType() eq 'ADMIN'}">
                                <div class="tools">
                                    <a href="Controller?command=find_film_by_id&film_id=${film.getId()}"><span
                                            class="glyphicon glyphicon-edit"></span></a>

                                    <a href="javascript: confirmMessage('Controller?command=remove_film&film_id=${film.getId()}')"><span
                                            class="glyphicon glyphicon-remove"></span></a>
                                </div>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>



        <c:if test="${sessionScope.user.getUserType() eq 'ADMIN'}">
            <a href="add_new_film.jsp" class="btn btn-primary">Добавить фильм</a>
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

