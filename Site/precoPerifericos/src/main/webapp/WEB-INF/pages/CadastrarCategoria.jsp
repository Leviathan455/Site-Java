<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Categoria</title>
    <!-- Bootstrap CSS link (adjust the path according to your setup) -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <!-- Custom styles -->
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
            margin-top: 20px;
            padding: 35px;
            width: 800px;
            background-color: #4D6677;
            border-radius: 15px;
        }
    </style>
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
<div class="container">
    <h2>Cadastrar Categoria</h2>
    <form action="../categoria" method="post">
        <div class="form-group">
            <label for="nome">Nome da Categoria:</label>
            <input type="text" class="form-control" id="nome" name="nome" required>
        </div>
        <input type="hidden" name="acao" value="cadastrar">
        <button type="submit" class="btn btn-primary">Cadastrar</button>
    </form>
</div>

<!-- Bootstrap JS and dependencies (adjust the path according to your setup) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-3VIhL3muFv3o4HfP4U5Fw2PxzP5tF8kMQHtoCJY7+4SAqD7Fww/GX4bXjEwEJxsc"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO/efcVAjBDZ9aw7WOmAjG9P3XfFNETFv3fjzoCXL"
        crossorigin="anonymous"></script>
</body>
</html>
