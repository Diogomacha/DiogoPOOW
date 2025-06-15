package br.csi.controller;

import br.csi.model.Jogador;
import br.csi.service.JogadorService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/jogador")
public class JogadorServlet extends HttpServlet {

    private static JogadorService service = new JogadorService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String opcao = req.getParameter("opcao");
        System.out.println("-----> POST opcao: " + opcao);

        if (opcao != null) {

            if (opcao.equals("cadastrar")) {

                String nome = req.getParameter("nome");
                String posicao = req.getParameter("posicao");
                String idadeStr = req.getParameter("idade");
                String numeroStr = req.getParameter("numero");
                String timeIdStr = req.getParameter("time_id");

                int idade = (idadeStr != null && !idadeStr.isEmpty()) ? Integer.parseInt(idadeStr) : 0;
                int numero = (numeroStr != null && !numeroStr.isEmpty()) ? Integer.parseInt(numeroStr) : 0;
                int timeId = (timeIdStr != null && !timeIdStr.isEmpty()) ? Integer.parseInt(timeIdStr) : 0;

                Jogador jogador = new Jogador();
                jogador.setNome(nome);
                jogador.setPosicao(posicao);
                jogador.setIdade(idade);
                jogador.setNumero(numero);
                jogador.setTimeId(timeId);

                service.inserir(jogador);

                resp.sendRedirect("lista-jogadores.jsp");
                return;

            } else if (opcao.equals("alterar")) {

                String idStr = req.getParameter("id");
                if (idStr == null || idStr.isEmpty()) {
                    resp.sendRedirect("erro.jsp");
                    return;
                }

                int id = Integer.parseInt(idStr);
                String nome = req.getParameter("nome");
                String posicao = req.getParameter("posicao");
                String idadeStr = req.getParameter("idade");
                String numeroStr = req.getParameter("numero");
                String timeIdStr = req.getParameter("time_id");

                int idade = (idadeStr != null && !idadeStr.isEmpty()) ? Integer.parseInt(idadeStr) : 0;
                int numero = (numeroStr != null && !numeroStr.isEmpty()) ? Integer.parseInt(numeroStr) : 0;
                int timeId = (timeIdStr != null && !timeIdStr.isEmpty()) ? Integer.parseInt(timeIdStr) : 0;

                Jogador jogador = new Jogador();
                jogador.setId(id);
                jogador.setNome(nome);
                jogador.setPosicao(posicao);
                jogador.setIdade(idade);
                jogador.setNumero(numero);
                jogador.setTimeId(timeId);

                service.alterar(jogador);

                resp.sendRedirect("perfil-jogador.jsp?id=" + id);
                return;

            } else if (opcao.equals("excluir")) {

                String idStr = req.getParameter("id");
                if (idStr == null || idStr.isEmpty()) {
                    resp.sendRedirect("erro.jsp");
                    return;
                }

                int id = Integer.parseInt(idStr);

                service.excluir(id);

                resp.sendRedirect("lista-jogadores.jsp");
                return;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String opcao = req.getParameter("opcao");
        System.out.println("-----> GET opcao: " + opcao);

        if (opcao != null && opcao.equals("excluir")) {

            String idStr = req.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr);

                service.excluir(id);

                resp.sendRedirect("lista-jogadores.jsp");
                return;
            } else {
                resp.sendRedirect("erro.jsp");
                return;
            }
        }


        String idStr = req.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            Jogador jogador = service.buscarPorId(id);
            req.setAttribute("jogador", jogador);
            RequestDispatcher rd = req.getRequestDispatcher("perfil-jogador.jsp");
            rd.forward(req, resp);
        } else {
            resp.sendRedirect("lista-jogadores.jsp");
        }
    }
}

