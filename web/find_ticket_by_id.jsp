<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<html>
<head>
    <title>Билет</title>
    <jsp:include page="included_head.jsp"/>
</head>

<body>

<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation"><a href="index.jsp">Главная</a></li>
                <li role="presentation" class="active"><a href="get_today_seances.action">Сеансы</a></li>
                <li role="presentation"><a href="get_films_collection.action">Фильмы</a></li>
                <li role="presentation"><a href="get_users_collection.action">Пользователи</a></li>
            </ul>
        </nav>
        <jsp:include page="included_user_profile.jsp"/>
    </div>

    <div class="jumbotron">
        <s:actionerror/>
        <s:actionmessage/>
        <form action="update_ticket.action" method="post" class="form-horizontal" role="form">

            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Название фильма</label>
                <div class="col-sm-10">
                    <input type="text" value="${ticket.getFilm().getTitle()}" id="name" class="form-control"></td>
                </div>
            </div>

            <div class="form-group">
                <label for="place" class="col-sm-2 control-label">Место</label>
                <div class="col-sm-10">
                    <select name="place" id="place" class="form-control">
                        <c:forEach var="i" begin="1" end="${ticket.getSeance().getHall().getCapacity()}" step="1">

                            <c:if test="${!busyPlaces.contains(i)}">
                                <option value="${i}">${i}</option>
                            </c:if>

                            <c:if test="${ticket.getPlace() eq i}">
                                <option selected value="${i}">${i}</option>
                            </c:if>

                        </c:forEach>
                    </select>
                </div>
            </div>

            <input type="hidden" name="ticket_id" value="${ticket.getId()}">
            <input type="submit" value="Изменить билет"/>

        </form>
        <div class="save" style="margin-top: 50px">
            <h2>Сохранить билет</h2>
            <form action="ticket_generation.action" method="post" class="form-horizontal" role="form">
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
                <input type="hidden" name="ticket_id" value="${ticket.getId()}">
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
