<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<html>
<head>
    <title>Главная страница</title>
    <jsp:include page="included_head.jsp"/>
</head>

<body>

<div class="container">
    <div class="header clearfix">
        <nav>
            <c:choose>
                <c:when test="${sessionScope.user.getUserType() eq 'ADMIN'}">
                    <ul class="nav nav-pills pull-right">
                        <li role="presentation" class="active"><a href="index.jsp">Главная</a></li>
                        <li role="presentation"><a href="get_today_seances.action">Сеансы</a></li>
                        <li role="presentation"><a href="get_films_collection.action">Фильмы</a></li>
                        <li role="presentation"><a href="Controller?command=get_users_collection">Пользователи</a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="nav nav-pills pull-right">
                        <li role="presentation" class="active"><a href="index.jsp">Главная</a></li>
                        <li role="presentation"><a href="get_today_seances.action">Сеансы</a></li>
                        <li role="presentation"><a href="get_films_collection.action">Фильмы</a></li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </nav>
        <jsp:include page="included_user_profile.jsp"/>
    </div>

    <div class="jumbotron">
        <h1>Комфорт обеспечен</h1>
        <p class="lead">Несколько залов, современное оборудование, отзывчивый и дружелюбный персонал. Все это и многое
            другое вы найдете в нашем кинотеатре. Не упустите возможность по максимому насладиться фильмом</p>
        <p>
            <a class="btn btn-lg btn-success" href="Controller?command=get_today_seances" role="button">Приобрести
                билет</a>
        </p>
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
