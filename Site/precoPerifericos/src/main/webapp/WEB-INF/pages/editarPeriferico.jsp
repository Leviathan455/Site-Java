<%--
  Created by IntelliJ IDEA.
  User: Thiago Both
  Date: 11/06/2024
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="dao" class="dao.PerifericoDao"/>
<html>
<head>
    <title>Editar Periférico</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<style>
    body {
        background-color: #a3b3ad;
    }
    .navbar {
        background-color: #4D6677;
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

                // Selecionar a categoria atual do periférico
                const categoriaAtual = "<c:out value='${param.categoriaId}' />";
                if (categoriaAtual) {
                    categoriaSelect.value = categoriaAtual;
                }
            })
            .catch(error => console.error('Erro ao carregar categorias:', error));
    });
</script>
<body>

<nav class="navbar navbar-expand-lg navbar-custom">
    <a class="navbar-brand" href="<c:url value='/index.jsp' />">Home</a>
</nav>

<div class="container mt-4">
    <form method="POST" action="../periferico" enctype="multipart/form-data">
        <input type="hidden" id="id" name="id" value="<c:out value='${param.id}' />">

        <div class="mb-3">
            <label for="nome" class="form-label">Nome do Periférico:</label>
            <input type="text" class="form-control" id="nome" name="nome" value="<c:out value='${param.nome}' />">
        </div>

        <div class="mb-3">
            <label for="descricao" class="form-label">Descrição:</label>
            <textarea class="form-control" id="descricao" name="descricao"><c:out value='${param.descricao}' /></textarea>
        </div>

        <div class="mb-3">
            <label for="imagem" class="form-label">Imagem:</label>
            <input type="file" class="form-control" id="imagem" name="imagem">
        </div>

        <div class="mb-3">
            <label for="categoria" class="form-label">Categoria:</label>
            <select id="categoria" name="categoria" class="form-select" required>
                <!-- Opções serão carregadas dinamicamente -->
            </select>
        </div>

        <div class="mb-3">
            <label for="marca" class="form-label">Marca:</label>
            <input type="text" class="form-control" id="marca" name="marca" value="<c:out value='${param.marca}' />">
        </div>

        <button type="submit" class="btn btn-primary" name="opcao" value="editar">Editar</button>
        <a class="btn btn-danger" href="<c:url value='/index.jsp' />">Cancelar</a>
        <c:if test="${not empty retorno}">
            <div class="alert alert-info mt-3" role="alert">
                <p>${retorno}</p>
            </div>
        </c:if>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>
