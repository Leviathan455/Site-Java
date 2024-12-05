<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="dao" class="dao.CategoriaDao"/>

<html>
<head>
    <title>Excluir Categoria</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background-color: #a3b3ad;
            color: white;
        }
        .container {
            background-color: #4D6677;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: auto;
            margin-top: 50px;
        }

        .navbar-toggler {
            border-bottom-left-radius: 8px;
            border-bottom-right-radius: 8px;
        }

        .navbar-custom {
            background-color: #4D6677;
            border-bottom-left-radius: 8px;
            border-bottom-right-radius: 8px;
            margin-bottom: 20px;
            margin-top: 0;
        }
        .navbar-custom .navbar-brand,
        .navbar-custom .nav-link {
            color: white !important;
            background-color: #4D6677;
        }
        .btn-secondary {
            margin-bottom: 20px;
        }
        .form-select {
            background-color: #6c7b8a;
            color: white;
            border: none;
            border-radius: 5px;
        }
        .btn-danger {
            background-color: #ff4d4d;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<c:if test="${user.status == null}">
<meta http-equiv="refresh" content="0; URL='http://localhost:8080/precoPerifericos/periferico"/>
</c:if>


<nav class="navbar navbar-expand-lg navbar-custom">
    <a class="navbar-brand" href="<c:url value='/index.jsp' />"">Home</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav">
            <c:if test="${user.status == null}">
                <li class="nav-item">
                    <a class="nav-link" href="../../Login.jsp">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../../Cadastro.jsp">Cadastro</a>
                </li>
            </c:if>

            <c:if test="${user.status != null}">
                <li class="nav-item">
                    <span class="nav-link">Bem-vindo, ${user.nome}</span>
                </li>
                <li class="nav-item">
                    <form action="../login">
                        <input type="hidden" id="action" name="action" value="logout">
                        <button class="nav-link" type="submit">
                            Logout
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-right" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M6.364 12.414a.5.5 0 0 0 .707-.708L4.707 10H13.5a.5.5 0 0 0 0-1H4.707l2.364-2.364a.5.5 0 1 0-.707-.708l-3.5 3.5a.5.5 0 0 0 0 .708l3.5 3.5z"/>
                                <path fill-rule="evenodd" d="M13.5 15a.5.5 0 0 1-.5.5h-10a.5.5 0 0 1-.5-.5V1a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 .5.5v1a.5.5 0 0 0 1 0V1a1.5 1.5 0 0 0-1.5-1.5h-10A1.5 1.5 0 0 0 1.5 1v14A1.5 1.5 0 0 0 3 16h10a1.5 1.5 0 0 0 1.5-1.5v-1a.5.5 0 0 0-1 0v1z"/>
                            </svg>
                        </button>
                    </form>
                </li>
            </c:if>
        </ul>
    </div>
</nav>

<div class="container">
    <h2 class="mb-4">Excluir Categoria</h2>
    <form action="../categoria" method="post">
        <input type="hidden" name="acao" value="excluir">
        <div class="mb-3">
            <label for="id" class="form-label">Selecione a Categoria</label>
            <select class="form-select" id="id" name="id">
                <c:forEach var="categoria" items="${dao.listarCategorias()}">
                    <option value="${categoria.id}">${categoria.nome}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-danger">Excluir</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
