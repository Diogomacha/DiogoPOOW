<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.csi.model.Dirigente, br.csi.model.Time, br.csi.dao.TimeDAO" %>
<%@ page session="true" %>
<%
    Dirigente dirigente = (Dirigente) session.getAttribute("dirigenteLogado");
    if (dirigente == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    Time time = TimeDAO.buscarPorDirigente(dirigente.getId());
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Meu Time</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">


<nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
    <div class="container">
        <a class="navbar-brand" href="home.jsp">Sistema de Dirigentes</a>
        <div class="d-flex">
            <a href="logout" class="btn btn-outline-light">Sair</a>
        </div>
    </div>
</nav>


<div class="container">
    <h2 class="text-center mb-4">Meu Time</h2>

    <% if (time != null) { %>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow rounded">
                <div class="card-body">
                    <p><strong>Nome:</strong> <%= time.getNome() %></p>
                    <p><strong>Cidade:</strong> <%= time.getCidade() %></p>
                    <p><strong>Dirigente:</strong> <%= dirigente.getNome() %></p>

                    <div class="d-grid gap-2 mt-4">

                        <button class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editarModal">Alterar</button>


                        <form action="time" method="get" onsubmit="return confirm('Tem certeza que deseja excluir seu time?')">
                            <input type="hidden" name="opcao" value="excluir">
                            <input type="hidden" name="id" value="<%= time.getId() %>">
                            <button type="submit" class="btn btn-danger">Excluir</button>
                        </form>
                    </div>
                </div>
            </div>


            <div class="d-grid mt-3">
                <a href="home.jsp" class="btn btn-secondary">Voltar</a>
            </div>
        </div>
    </div>


    <div class="modal fade" id="editarModal" tabindex="-1" aria-labelledby="editarModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <form action="time" method="post" class="modal-content">

                <input type="hidden" name="opcao" value="alterar">
                <input type="hidden" name="id" value="<%= time.getId() %>">

                <div class="modal-header">
                    <h5 class="modal-title" id="editarModalLabel">Editar Time</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" class="form-control" name="nome" id="nome" value="<%= time.getNome() %>" required>
                    </div>
                    <div class="mb-3">
                        <label for="cidade" class="form-label">Cidade</label>
                        <input type="text" class="form-control" name="cidade" id="cidade" value="<%= time.getCidade() %>" required>
                    </div>
                    <input type="hidden" name="dirigenteId" value="<%= dirigente.getId() %>">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-success">Salvar Alterações</button>
                </div>
            </form>
        </div>
    </div>

    <% } else { %>
    <div class="alert alert-info text-center">Você ainda não tem um time cadastrado.</div>
    <div class="text-center mt-3">
        <a href="home.jsp" class="btn btn-secondary">Voltar</a>
    </div>
    <% } %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
