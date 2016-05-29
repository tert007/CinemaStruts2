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
            <c:choose>
                <c:when test="${sessionScope.user.getUserType() eq 'ADMIN'}">
                    <ul class="nav nav-pills pull-right">
                        <li role="presentation"><a href="index.jsp">Главная</a></li>
                        <li role="presentation"><a href="Controller?command=get_today_seances">Сеансы</a></li>
                        <li role="presentation"><a href="Controller?command=get_films_collection">Фильмы</a></li>
                        <li role="presentation"><a href="Controller?command=get_users_collection">Пользователи</a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="nav nav-pills pull-right">
                        <li role="presentation"><a href="index.jsp">Главная</a></li>
                        <li role="presentation"><a href="Controller?command=get_today_seances">Сеансы</a></li>
                        <li role="presentation"><a href="Controller?command=get_films_collection">Фильмы</a></li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </nav>
        <jsp:include page="included_user_profile.jsp"/>
    </div>


    <div class="jumbotron">

        <c:choose>
            <c:when test="${user == null}">
                <p>Ошибка, пользователь ${error_login} не найден</p>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${sessionScope.user.getUserType() eq 'ADMIN'}">
                        <form class="form-horizontal" action="/Controller">
                            <div class="form-group">
                                <input type="text" class="form-control" name="login" value="${user.getLogin()}">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" name="email" value="${user.getEmail()}">
                            </div>
                            <div class="form-group">
                                <input type="number" class="form-control" name="bonus_count" value="${user.getBonusCount()}">
                            </div>
                            <div class="form-group">
                                <select name="user_type" class="form-control">
                                    <c:choose>
                                        <c:when test="${user.getUserType() eq 'USER'}">
                                            <option value="USER" selected>USER</option>
                                            <option value="ADMIN">ADMIN</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="USER">USER</option>
                                            <option value="ADMIN" selected >ADMIN</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>
                            <div class="form-group">
                                <input type="hidden" name="command" value="update_user">
                                <input type="hidden" name="password" value="${user.getPassword()}">
                                <input type="hidden" name="user_id" value="${user.getId()}">
                                <c:out value="${errorMessage}"/>
                                <input type="submit" class="form-control" name="submit" value="Изменить данные">
                            </div>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <p>Логин: ${user.getLogin()}
                        <p>Email: ${user.getEmail()}</p>
                    </c:otherwise>
                </c:choose>
                </p>
                <p> Остаток на счету: ${user.getBonusCount()}</p>

                <table class="table">
                    <caption>Билеты</caption>
                    <tr>
                        <td>Название Фильма</td>
                        <td>Место</td>
                        <td>Зал</td>
                        <td>Дата</td>
                        <td>Время сеанса</td>
                    </tr>

                    <c:forEach var="ticket" items="${tickets}">
                        <tr>
                            <td>${ticket.getFilm().getTitle()}</td>
                            <td>${ticket.getPlace()}</td>
                            <td>${ticket.getHall().getId()}</td>
                            <td>${ticket.getDateByString()}</td>
                            <td>${ticket.getTimeByString()}</td>
                            <td>${ticket.getPrice()}</td>
                            <td>
                                <c:if test="${sessionScope.user.getUserType().toString() eq 'ADMIN'}">
                                <a href="Controller?command=find_ticket_by_id&ticket_id=${ticket.getId()}"><span
                                        class="glyphicon glyphicon-edit"></span></a>
                                <a href="javascript: confirmMessage('Controller?command=remove_ticket&ticket_id=${ticket.getId()}')"><span
                                        class="glyphicon glyphicon-remove"></span></a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </c:otherwise>
        </c:choose>
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
