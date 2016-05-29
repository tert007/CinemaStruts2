<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<html>
<head>
    <title>Добавление нового фильма</title>
    <jsp:include page="included_head.jsp"/>
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

    <div class="jumbotron">
        <c:out value="${statusMessage}"/>
        <form action="Controller" method="GET">
            <table>
                <tr>
                    <td>Выберите фильм</td>
                    <td>
                        <select name="film_id">
                            <c:forEach var="film" items="${films}">
                                <option value="${film.getId()}">
                                        ${film.getTitle()}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Выберите Зал</td>
                    <td>
                        <select name="hall_id">
                            <c:forEach var="hall" items="${halls}">
                                <option value="${hall.getId()}">
                                        ${hall.getId()}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <div class="form-group">
                        <label for="select_date" class="col-sm-2 control-label">Дата</label>
                        <div class="col-sm-10">
                            <div class='input-group date' id='datetimepicker1'>
                                <input type='text' class="form-control" name="date" id="select_date"/>
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar">
                                </span>
                            </span>
                            </div>
                        </div>
                        <script type="text/javascript">
                            $(function () {
                                $('#datetimepicker1').datetimepicker({
                                    pickTime: false,
                                    language: 'ru'
                                });
                            });
                        </script>
                    </div>
                </tr>
                <tr>
                    <td>Выбирите время</td>
                    <td><input type="text" name="time"></td>
                </tr>
                <tr>
                    <td>Выбирите стоимость</td>
                    <td><input type="number" name="price"></td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="command" value="add_new_seance">
                        <input type="submit" value="Добавить">
                    </td>
                </tr>
            </table>
        </form>
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
