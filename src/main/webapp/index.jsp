<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Login - Sistema de Dirigentes</title>


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow-sm p-4" style="max-width: 400px; width: 100%;">
        <h2 class="mb-4 text-center">Login de Dirigente</h2>

        <% String msg = (String) request.getAttribute("msg"); %>
        <% if (msg != null) { %>
        <div class="alert alert-danger" role="alert">
            <%= msg %>
        </div>
        <% } %>

        <form action="login" method="post" novalidate>
            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" id="email" name="email" class="form-control" required autofocus />
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Senha:</label>
                <input type="password" id="password" name="senha" class="form-control" required />
            </div>

            <button type="submit" class="btn btn-primary w-100">Entrar</button>
        </form>

        <hr />

        <div class="text-center">
            <a href="cadastro-dirigente.jsp" class="btn btn-outline-secondary btn-sm">Cadastrar novo dirigente</a>
        </div>
    </div>
</div>

</body>
</html>
