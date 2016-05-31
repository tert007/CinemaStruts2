<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<html>
<head>
    <title>Сеансы</title>
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

            <form action="get_seances_by_date.action" method="post">

                <div class="container">
                    <div class="col-sm-6">
                        <div class="form-group">
                            <div class='input-group date' id='datetimepicker1'>
                                <input type='text' class="form-control" name="start_day"/>
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar">
                                    </span>
                                </span>
                            </div>
                        </div>
                    </div>

                    <script type="text/javascript">
                        $(function () {
                            $('#datetimepicker1').datetimepicker(
                                    {pickTime: false, language: 'ru'}
                            );
                        });
                    </script>

                    <div class="container">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <div class='input-group date' id='datetimepicker2'>
                                    <input type='text' class="form-control" name="finish_day"/>
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar">
                                        </span>
                                    </span>
                                </div>
                            </div>
                        </div>

                        <script type="text/javascript">
                            $(function () {
                                $('#datetimepicker2').datetimepicker(
                                        {pickTime: false, language: 'ru'}
                                );
                            });
                        </script>
                    </div>
                </div>
                <input type="submit" value="Найти"/>
            </form>

            <caption>Сеансы</caption>
            <tr>
                <td>Название фильма</td>
                <td>Дата</td>
                <td>Время</td>
                <td>Стоимость</td>
                <td></td>
            </tr>

            <c:forEach var="seance" items="${seances}">
                <tr>
                    <td>${seance.getFilm().getTitle()}</td>
                    <td>${seance.getDateByString()}</td>
                    <td>${seance.getTimeByString()}</td>
                    <td>${seance.getPrice()}</td>
                    <td>
                        <a class="btn-primary btn-sm"
                           href="show_seance_sits.action?seance_id=${seance.getId()}">Купить</a>
                    </td>
                    <td>
                        <c:if test="${sessionScope.user.getUserType().toString() eq 'ADMIN'}">
                            <a href="find_seance_by_id.action?seance_id=${seance.getId()}" ><span
                                    class="glyphicon glyphicon-edit"></span></a>

                            <a href="javascript: confirmMessage('remove_seance.action?seance_id=${seance.getId()}')"><span
                                    class="glyphicon glyphicon-remove"></span></a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${sessionScope.user.getUserType() eq 'ADMIN'}">
            <a href="show_add_new_seance.action">Добавить сеанс</a>
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
