<%@ page import="model.Usuario, model.Favorito, java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.FavoritoService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="dao" class="dao.PerifericoDao"/>

<%
    // Obtendo o usuário da sessão
    Usuario user = (Usuario) session.getAttribute("user");

    if (user != null) {

        Integer userId = user != null ? user.getId() : null;

        FavoritoService favServ = new FavoritoService();

        // Obter lista de favoritos da requisição
        List<Favorito> favoritos = favServ.buscarFavoritosPorUsuarioId(user.getId());
        List<Integer> perifericosFavoritados = new ArrayList<>();

        if (favoritos != null) {
            for (Favorito favorito : favoritos) {
                perifericosFavoritados.add(favorito.getPeriferico().getId());
            }
        }

        request.setAttribute("perifericosFavoritados", perifericosFavoritados);

    }


%>

<html>
<head>
    <title>Visualizar Periféricos</title>
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
            margin-top: 10px;
            margin-bottom: 10px;
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
        .periferico-card {
            display: flex;
            align-items: center;
            padding: 10px;
            border: 1px solid #ccc;
            margin-bottom: 15px;
            border-radius: 8px;
        }
        .periferico-card img {
            width: 150px;
            height: auto;
            margin-right: 20px;
            border-radius: 8px;
        }
        .periferico-card-info {
            flex: 1;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .button-container {
            display: flex;
            gap: 10px;
        }
        .button-container .btn {
            flex: 1;
        }
        .btn-secondary {
            margin-bottom: 20px;
        }
        .categorias-list {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
            justify-content: center; /* Centraliza os itens horizontalmente */

            border-radius: 15px;
            background-color: #4D6677;
            color: white;
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .categoria-item {
            margin: 5px;
            background-color: #6c7b8a;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
            flex: 1;
            text-align: center;
        }
        .categoria-item:hover {
            text-decoration: underline;
        }
    </style>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            fetch('http://localhost:8080/precoPerifericos/categoria?acao=listar')
                .then(response => response.json())
                .then(data => {
                    let categoriaList = document.getElementById('categoria-list');
                    data.forEach(categoria => {
                        let listItem = document.createElement('div');
                        listItem.textContent = categoria.nome;
                        listItem.classList.add('categoria-item');
                        listItem.addEventListener('click', function() {
                            // Ação ao clicar na categoria, se necessário
                            console.log('Categoria selecionada:', categoria.nome);
                            window.location.replace('http://localhost:8080/precoPerifericos/periferico?categoria='+categoria.id)

                            // Adicione aqui a ação desejada ao clicar na categoria
                        });
                        categoriaList.appendChild(listItem);
                    });
                })
                .catch(error => console.error('Erro ao carregar categorias:', error));
        });
    </script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-custom">
    <a class="navbar-brand" href="index.jsp">Home</a>
    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav">
            <c:if test="${user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="Login.jsp">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Cadastro.jsp">Cadastro</a>
                </li>
            </c:if>

            <c:if test="${user != null}">
                <li class="nav-item">
                    <span class="nav-link">Bem-vindo, ${user.nome}</span>
                </li>
                <li class="nav-item">
                    <form action="login">
                        <input type="hidden" id="action" name="action" value="logout">
                        <button class="nav-link" type="submit">Logout</button>
                    </form>
                </li>
            </c:if>
        </ul>
    </div>
</nav>
<div class="container">
    <h4 class="form-label">Categorias:</h4>
    <div class="categorias-list">
        <div id="categoria-list" class="d-flex flex-wrap justify-content-center">
            <div class="categoria-item" onclick="window.location.replace('http://localhost:8080/precoPerifericos/periferico?')">Todos</div>
            <!-- Itens de categoria serão adicionados dinamicamente pelo script -->
        </div>
    </div>
</div>
<div class="container">
    <h2 class="mb-4">Lista de Periféricos</h2>


    <c:forEach var="p" items="${perifericos}">
        <div class="periferico-card">
            <img src="data:image/jpeg;base64,${p.imagemBase64}" alt="${p.nome}" class="img-thumbnail">
            <div class="periferico-card-info">
                <div>
                    <h5>${p.nome}</h5>
                    <p>${p.marca}</p>
                </div>
                <div class="button-container">
                    <a href="periferico?opcao=verprecos&id=${p.id}&nome=${p.nome}" class="btn btn-primary">Ver Preços</a>

                    <c:if test="${user != null && user.status == true}">
                        <a class="btn btn-warning" href="<c:url value='/admin/cadastrarPrecoLoja?id=${p.id}' />">Cadastrar Loja</a>
                        <a class="btn btn-success" href="<c:url value='/admin/editarPeriferico?id=${p.id}' />">Editar</a>
                        <a href="periferico?opcao=excluir&id=${p.id}" class="btn btn-danger">Excluir</a>
                    </c:if>

                    <c:if test="${user != null}">
                        <c:choose>
                            <c:when test="${fn:contains(perifericosFavoritados, p.id)}">
                                <form action="favorito" method="post">
                                    <input type="hidden" name="_method" value="delete">
                                    <input type="hidden" name="usuarioId" value="${user.id}">
                                    <input type="hidden" name="perifericoId" value="${p.id}">
                                    <button type="submit" class="btn btn-danger">Remover dos Favoritos</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="${pageContext.request.contextPath}/favorito" method="post">
                                    <input type="hidden" name="userId" value="${user.id}" />
                                    <input type="hidden" name="perifericoId" value="${p.id}" />
                                    <button type="submit" class="btn btn-success">Adicionar aos Favoritos</button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>

    <c:if test="${not empty retorno}">
        <div class="alert alert-info" role="alert">${retorno}</div>
    </c:if>

    <c:if test="${user != null && user.status == true}">
        <div class="row mt-3">
            <div class="col">
                <a class="btn btn-secondary" href="<c:url value='/admin/cadastrarPeriferico' />">Cadastrar Periferico</a>
                <a class="btn btn-secondary" href="<c:url value='/admin/excluirCategoria' />">Excluir Categoria</a>
                <a class="btn btn-secondary" href="<c:url value='/admin/cadastrarCategoria' />">Cadastrar Categoria</a>
            </div>
        </div>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
