<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<html>
<head>
    <title>Сеанс ${seance.getDate()}</title>
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
                        <li role="presentation" class="active"><a href="Controller?command=get_today_seances">Сеансы</a>
                        </li>
                        <li role="presentation"><a href="Controller?command=get_films_collection">Фильмы</a></li>
                        <li role="presentation"><a href="Controller?command=get_users_collection">Пользователи</a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="nav nav-pills pull-right">
                        <li role="presentation"><a href="index.jsp">Главная</a></li>
                        <li role="presentation" class="active"><a href="Controller?command=get_today_seances">Сеансы</a>
                        </li>
                        <li role="presentation"><a href="Controller?command=get_films_collection">Фильмы</a></li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </nav>
        <jsp:include page="included_user_profile.jsp"/>
    </div>

    <div class="jumbotron">

        <table class="table">
        <c:forEach var="i" begin="1" end="${seance.getHall().getCapacity()}" step="10">
            <tr>
            <c:forEach var="j" begin="${i}" end="${i+10}" step="1">
            <c:choose>
                <c:when test="${busyPlaces.contains(j)}">
                    <td> <button type="button" class=" btn-default btn-xs" disabled>${j}</button> </td>
                </c:when>
                <c:otherwise>
                    <td>
                    <a href="Controller?command=buy_ticket&place=${j}&seance_id=${seance.getId()}">
                        <button type="button" class=" btn-primary btn-xs">${j}</button>
                    </a>
                    </td>
                </c:otherwise>
            </c:choose>
            </c:forEach>
            </tr>
        </c:forEach>
        </table>
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
