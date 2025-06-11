<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.csi.model.Dirigente" %>
<%
    HttpSession sessao = request.getSession(false);
    Dirigente dirigente = (Dirigente) sessao.getAttribute("dirigenteLogado");

    if (dirigente == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Dirigente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container d-flex flex-column align-items-center justify-content-center" style="min-height: 100vh;">
    <div class="card shadow p-5 w-100" style="max-width: 600px;">
        <h2 class="mb-4 text-center">Bem-vindo, <strong><%= dirigente.getNome() %></strong>!</h2>

        <div class="d-grid gap-3">
            <a href="cadastrar-time.jsp" class="btn btn-primary btn-lg">Cadastrar Times</a>
            <a href="cadastrar-jogador.jsp" class="btn btn-success btn-lg">Cadastrar Jogadores</a>
            <a href="perfil-dirigente.jsp" class="btn btn-info btn-lg text-white">Ver Perfil</a>
            <a href="meu-time.jsp" class="btn btn-warning btn-lg">Meu Time</a>
        </div>

        <div class="mt-4 text-center">
            <a href="logout" class="btn btn-outline-danger btn-sm">Sair</a>
        </div>
    </div>
</div>

</body>
</html>
