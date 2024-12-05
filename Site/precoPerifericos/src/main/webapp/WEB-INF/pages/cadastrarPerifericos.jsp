<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<jsp:useBean id="categoriaService" class="service.CategoriaService" scope="request"/>
<jsp:useBean id="dao" class="dao.PerifericoDao"/>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Periférico</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background-color: #a3b3ad;
        }
        .navbar-custom {
            background-color: #343a40;
        }
        .navbar-custom .navbar-brand, .navbar-custom .nav-link {
            color: #ffffff;
        }
        .container {
            padding: 35px;
            width: 800px;
            background-color: #4D6677;
            border-radius: 15px;
        }
    </style>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            fetch('http://localhost:8080/precoPerifericos/categoria?acao=listar')
                .then(response => response.json())
                .then(data => {
                    let categoriaSelect = document.getElementById('categoria');
                    data.forEach(categoria => {
                        let option = document.createElement('option');
                        option.value = categoria.id;
                        option.text = categoria.nome;
                        categoriaSelect.add(option);
                    });
                })
                .catch(error => console.error('Erro ao carregar categorias:', error));
        });
    </script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark navbar-custom">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value='/index.jsp' />">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
</nav>

<div class="container mt-5">
    <form method="POST" action="../periferico" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="nome" class="form-label">Nome do periférico:</label>
            <input type="text" class="form-control" id="nome" name="nome">
        </div>

        <div class="mb-3">
            <label for="descricao" class="form-label">Descrição:</label>
            <input type="text" class="form-control" id="descricao" name="descricao">
        </div>

        <div class="mb-3">
            <label for="imagem" class="form-label">Imagem:</label>
            <input type="file" class="form-control" id="imagem" name="imagem">
        </div>

        <div class="mb-3">
            <label for="marca" class="form-label">Marca:</label>
            <input type="text" class="form-control" id="marca" name="marca">
        </div>

        <div class="mb-3">
            <label for="categoria" class="form-label">Categoria:</label>
            <select id="categoria" name="categoria" class="form-select" required>

            </select>
        </div>

        <input type="hidden" name="opcao" value="cadastrar">

        <button type="submit" class="btn btn-primary">Cadastrar</button>
        <a class="btn btn-danger" href="<c:url value='/index.jsp' />">Cancelar</a>

        <c:if test="${not empty retorno}">
            <div role="alert" class="alert alert-success mt-3">${retorno}</div>
        </c:if>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

