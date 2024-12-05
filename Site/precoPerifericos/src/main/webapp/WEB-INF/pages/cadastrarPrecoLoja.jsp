<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Cadastro de Preço da Loja</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .btn-secondary {
            margin-bottom: 20px;
        }

        h2 {
            font-size: 1.5rem;
            margin-bottom: 20px;
        }

        .form-label {
            font-weight: bold;
        }

        .form-control {
            margin-bottom: 15px;
        }

        .btn-primary {
            display: block;
            width: 100%;
            margin-top: 20px;
        }

        .btn-danger {
            display: block;
            width: 100%;
            margin-top: 10px;
        }

        [role="alert"] {
            margin-top: 20px;
            padding: 10px;
            border-radius: 5px;
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

        body {
            background-color: #f0f2f5;
            font-family: Arial, sans-serif;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="periferico">Home</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h2>Cadastro de Preço da Loja para o Periférico: <c:out value="${param.nome}" /></h2>

    <form method="POST" action="../precoloja">
        <input type="hidden" name="idperiferico" value="<c:out value="${param.id}" />">

        <div class="mb-3">
            <label for="loja" class="form-label">Nome da Loja:</label>
            <input type="text" class="form-control" id="loja" name="loja" required>
        </div>

        <div class="mb-3">
            <label for="preco" class="form-label">Preço:</label>
            <input type="text" class="form-control" id="preco" name="preco" required>
        </div>

        <div class="mb-3">
            <label for="link" class="form-label">Link do Produto:</label>
            <input type="text" class="form-control" id="link" name="link" required>
        </div>

        <input class="btn btn-primary" type="submit" value="cadastrar" name="opcao">
        <a class="btn btn-danger" href="<c:url value='/index.jsp' />">Cancelar</a>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
