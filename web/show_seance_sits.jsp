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
                        <li role="presentation" class="active"><a href="get_today_seances.action">Сеансы</a></li>
                        <li role="presentation"><a href="get_films_collection.action">Фильмы</a></li>
                        <li role="presentation"><a href="get_users_collection.action">Пользователи</a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="nav nav-pills pull-right">
                        <li role="presentation"><a href="index.jsp">Главная</a></li>
                        <li role="presentation" class="active"><a href="get_today_seances.action">Сеансы</a></li>
                        <li role="presentation"><a href="get_films_collection.action">Фильмы</a></li>
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
                    <a href="buy_ticket.action?place=${j}&seance_id=${seance.getId()}">
                        <button type="button" class=" btn-primary btn-xs">${j}</button>
                    </a>
                    </td>
                </c:otherwise>
            </c:choose>
            </c:forEach>
            </tr>
        </c:forEach>
        </table>

        <div class="save" style="margin-top: 50px">
            <h2>Сохранить зал</h2>
            <form action="hall_generation.action" method="post" class="form-horizontal" role="form">
                <div class="form-group">
                    <label for="doc_type" class="col-sm-2 control-label">Выбирите формат сохранения</label>
                    <div class="col-sm-10">
                        <select name="doc_type" class="form-control" id="doc_type">
                            <option name="PDF">PDF</option>
                            <option name="EXCEL">EXCEL</option>
                            <option name="CSV">CSV</option>
                        </select>
                    </div>
                </div>
                <input type="hidden" name="hall_id" value="${seance.getHall().getId()}">
                <input type="submit" value="Сохранить"/>
            </form>
        </div>
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
