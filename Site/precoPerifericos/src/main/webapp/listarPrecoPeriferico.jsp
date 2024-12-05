<%--
  Created by IntelliJ IDEA.
  User: Thiago Both
  Date: 11/06/2024
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="dao" class="dao.PrecoLojaDao"/>

<html>
<head>
    <title>Visualizar Produto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body{
            background-color: #a3b3ad;
        }

        .container{
            background-color: #4D6677;
            padding: 35px;
            border-radius: 20px;
        }
        .navbar {
            background-color: #4D6677;
        }
        .produto-container {
            display: flex;
            gap: 20px;
            align-items: flex-start; /* Ajusta alinhamento vertical */
        }
        .col{
            background-color: white;
            padding: 25px;
            border-radius: 15px;
        }
        .produto-imagem {
            flex: 0 0 auto;
        }
        .produto-info {
            flex: 1;
        }
        .produto-nome {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .produto-descricao {
            margin-bottom: 10px;
        }
        .produto-marca {
            font-style: italic;
        }
        #itens-row{
            background-color: #cccccc;
            border-radius: 10px;
            padding: 10px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg">
        <a class="navbar-brand" href="index.jsp">Home</a>
        <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
            <ul class="navbar-nav">
                <c:if test="${empty user.status}">
                    <li class="nav-item">
                        <a class="nav-link" href="Login.jsp">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Cadastro.jsp">Cadastro</a>
                    </li>
                </c:if>
                <c:if test="${not empty user.status}">
                    <li class="nav-item">
                        <span class="nav-link">${user.nome}</span>
                    </li>
                </c:if>
            </ul>
        </div>
</nav>

<div class="container mt-4">
    <%--<a class="btn btn-secondary mb-3" href="periferico">Voltar</a>
--%>
    <div class="row" id="itens-row">
        <div class="col-md-4">
            <div class="produto-imagem">
                <img src="data:image/jpeg;base64,${periferico.imagemBase64}" alt="${periferico.nome}" class="img-thumbnail" width="300">
            </div>
        </div>
        <div class="col-md-8">
            <div class="produto-info">
                <h3 class="produto-nome">${periferico.nome}</h3>
                <p class="produto-descricao"><strong>Descrição:</strong> ${periferico.descricao}</p>
                <p class="produto-marca"><strong>Marca:</strong> ${periferico.marca}</p>
            </div>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Lojas</th>
                    <th scope="col">Preços</th>
                    <th scope="col">Ações</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="p" items="${precos}">
                    <tr>
                        <td>${p.loja}</td>
                        <td>${p.preco}</td>
                        <td class="text-end">
                            <div class="d-flex justify-content-end">
                                <a href="${p.link}" type="submit" class="btn btn-success me-1">Comprar</a>

                                <c:if test="${user.status == true}">
                                    <form action="<c:url value='/admin/editarPrecoLoja?id=${p.id}' />" method="get" class="me-1">
                                        <input type="hidden" name="idPeriferico" value="${p.id}">
                                        <input type="hidden" name="idLoja" value="${p.idPrecoLoja}">
                                        <button type="submit" class="btn btn-primary">Editar</button>
                                    </form>
                                    <form action="precoloja" method="post">
                                        <input type="hidden" name="opcao" value="excluir">
                                        <input type="hidden" name="idPeriferico" value="${p.id}">
                                        <input type="hidden" name="idprecoloja" value="${p.idPrecoLoja}">
                                        <button type="submit" class="btn btn-danger">Excluir</button>
                                    </form>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>
