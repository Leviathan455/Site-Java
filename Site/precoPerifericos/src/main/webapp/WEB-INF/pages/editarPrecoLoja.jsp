<%--
  Created by IntelliJ IDEA.
  User: Thiago Both
  Date: 11/06/2024
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<jsp:useBean	id="dao" class="dao.PerifericoDao"/>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>


<div class = "container">

<a class = "btn btn-secondary" href="<c:url value='/index.jsp' />">Home</a>

    <form method="POST" action="../precoloja">


        <input  hidden class="form-control"  type = "number"name = "idperiferico" value = "<c:out value="${param.idPeriferico}" />">
        <input  hidden class="form-control"  type = "number"name = "idprecoloja" value = "<c:out value="${param.idLoja}" />">

        <label for="loja" class="form-label">Loja::</label>
        <input type="text" class="form-control" id="loja" name = "loja" value = "<c:out value="${param.loja}" />">


        <label for="preco" class="form-label">Preco::</label>
        <input type="text" class="form-control" id="preco" name = "preco" value = "<c:out value="${param.preco}" />">

        <label for="link" class="form-label">Link do Produto:</label>
        <input type="text" class="form-control" id="link" name="link" required>

        <input class = "btn btn-primary" type = "submit" value="editar" name="opcao">
    </form>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>
