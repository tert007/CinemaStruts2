<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<html>
<head>
    <title>Вход на сайт</title>
    <jsp:include page="included_head.jsp"/>
    <link href="css/signin.css" rel="stylesheet" type="text/css">
</head>

<body>

<div class="container">

    <form class="form-signin" action="login_user.action" method="post">
        <h2 class="form-signin-heading">Выполните вход</h2>

        <label for="inputEmail" class="sr-only">Логин</label>
        <input type="text" id="inputEmail" name="login" class="form-control" placeholder="Логин" required autofocus>

        <label for="inputPassword" class="sr-only">Пароль</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Пароль" required>

        <s:actionerror/>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Вход</button>
        <a class="btn btn-lg btn-primary btn-block" href="registration.jsp">Регистрация</a>
    </form>


</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

</body>
</html>
