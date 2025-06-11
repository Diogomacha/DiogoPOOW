<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.csi.model.Dirigente" %>
<%@ page session="true" %>
<%
    Dirigente dirigente = (Dirigente) session.getAttribute("dirigenteLogado");

    if (dirigente == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Perfil do Dirigente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">


<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <a class="navbar-brand" href="home.jsp">Sistema de Dirigentes</a>
        <div class="d-flex">
            <a href="logout" class="btn btn-outline-light">Sair</a>
        </div>
    </div>
</nav>


<div class="container d-flex justify-content-center align-items-center" style="min-height: 90vh;">
    <div class="card shadow p-4 w-100" style="max-width: 600px;">
        <h2 class="text-center mb-4">Perfil do Dirigente</h2>

        <div class="mb-4">
            <p><strong>Nome:</strong> <%= dirigente.getNome() %></p>
            <p><strong>Email:</strong> <%= dirigente.getEmail() %></p>
        </div>


        <div class="d-grid gap-2">

            <button class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#alterarModal">Alterar Dados</button>


            <form action="dirigente" method="get" onsubmit="return confirm('Tem certeza que deseja excluir sua conta?')">
                <input type="hidden" name="opcao" value="excluir">
                <input type="hidden" name="id" value="<%= dirigente.getId() %>">
                <button type="submit" class="btn btn-danger">Excluir Conta</button>
            </form>

            <a href="home.jsp" class="btn btn-secondary">Voltar</a>
        </div>
    </div>
</div>


<div class="modal fade" id="alterarModal" tabindex="-1" aria-labelledby="alterarModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="dirigente" method="post" class="modal-content">

            <input type="hidden" name="opcao" value="alterar">

            <div class="modal-header">
                <h5 class="modal-title" id="alterarModalLabel">Alterar Dados</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
            </div>
            <div class="modal-body">
                <input type="hidden" name="id" value="<%= dirigente.getId() %>">
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" class="form-control" name="nome" id="nome" value="<%= dirigente.getNome() %>" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" name="email" id="email" value="<%= dirigente.getEmail() %>" required>
                </div>
                <div class="mb-3">
                    <label for="senha" class="form-label">Senha</label>
                    <input type="password" class="form-control" name="senha" id="senha" value="<%= dirigente.getSenha() %>" required>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="submit" class="btn btn-success">Salvar Alterações</button>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
