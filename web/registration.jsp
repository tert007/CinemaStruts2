<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<html>
<head>
    <title>Регистрация</title>
    <jsp:include page="included_head.jsp"/>
</head>

<body>

<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation"><a href="index.jsp">Главная</a></li>
                <li role="presentation"><a href="Controller?command=get_today_seances">Сеансы</a></li>
                <li role="presentation"><a href="Controller?command=get_films_collection">Фильмы</a></li>
            </ul>
        </nav>
        <h3 class="text-muted">Cinemator</h3>
    </div>

    <div class="jumbotron">
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="registration_user"/>
            <table>
                <tr>
                    <td> Введите логин:</td>
                    <td><input type="text" required name="login"></td>
                </tr>
                <tr>
                    <td> Введите пароль:</td>
                    <td><input type="password" required name="password"></td>
                </tr>
                <tr>
                    <td> Введите email:</td>
                    <td><input type="email" required name="email"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="button" value="Зарегестрироваться"/></td>
                </tr>
                <c:out value="${errorMessage}"/>
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
