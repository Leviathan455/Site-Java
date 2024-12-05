<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastro de Usuário</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background-color: #a3b3ad;
            color: white;
        }
        .IdConteiner {
            background-color: #4D6677;
            width: 700px;
            padding: 20px;
            border-radius: 8px;
            color: white;
        }
        .navbar-custom {
            background-color: #4D6677;
        }
        .navbar-custom .navbar-brand,
        .navbar-custom .nav-link {
            color: white;
        }
        .mensagem-confirmacao {
            display: none; /* Inicialmente oculta */
            background-color: #dff0d8; /* Cor de fundo verde do Bootstrap para sucesso */
            border-color: #d6e9c6; /* Cor da borda do Bootstrap para sucesso */
            color: #3c763d; /* Cor do texto do Bootstrap para sucesso */
            padding: 15px;
            margin-top: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
        }

        .mensagem-confirmacao.show {
            display: block; /* Exibe a mensagem */
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-custom">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">Seu Site</a>
    </div>
</nav>

<div class="container mt-4 IdConteiner">
    <h2>Cadastro de Usuário</h2>
    <form action="cadastro" method="post">
        <div class="mb-3">
            <label for="nome" class="form-label">Nome</label>
            <input type="text" class="form-control" id="nome" name="nome" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="mb-3">
            <label for="senha" class="form-label">Senha</label>
            <input type="password" class="form-control" id="senha" name="senha" required>
        </div>
        <!-- Botão Cadastrar -->
        <div class="text-end">
            <button type="submit" class="btn btn-primary">Cadastrar</button>
        </div>
        <!-- Mensagem de confirmação -->
        <div class="mensagem-confirmacao" id="mensagemConfirmacao">
            Cadastro efetuado com sucesso! Redirecionando para a página de login...
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>
