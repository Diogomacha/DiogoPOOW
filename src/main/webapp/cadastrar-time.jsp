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
    <title>Cadastrar Time</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow p-4">
        <h2 class="mb-4">Cadastrar Novo Time</h2>


        <form action="time" method="post">

            <input type="hidden" name="opcao" value="cadastrar">

            <div class="mb-3">
                <label for="nome" class="form-label">Nome do Time</label>
                <input type="text" class="form-control" id="nome" name="nome" required>
            </div>

            <div class="mb-3">
                <label for="cidade" class="form-label">Cidade</label>
                <input type="text" class="form-control" id="cidade" name="cidade" required>
            </div>

            <div class="mb-3">
                <label for="dirigente" class="form-label">Dirigente Responsável</label>
                <input type="text" class="form-control" id="dirigente" value="<%= dirigente.getNome() %>" readonly>
                <input type="hidden" name="dirigenteId" value="<%= dirigente.getId() %>">
            </div>

            <div class="d-flex justify-content-between mt-4">
                <a href="home.jsp" class="btn btn-outline-secondary">← Voltar</a>
                <button type="submit" class="btn btn-primary">Cadastrar Time</button>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
