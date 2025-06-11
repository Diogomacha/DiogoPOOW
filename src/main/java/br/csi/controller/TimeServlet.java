package br.csi.controller;

import br.csi.model.Time;
import br.csi.service.TimeService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {

    private static TimeService service = new TimeService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String opcao = req.getParameter("opcao");
        System.out.println("-----> POST opcao: " + opcao);

        if (opcao != null) {

            if (opcao.equals("cadastrar")) {

                String nome = req.getParameter("nome");
                String cidade = req.getParameter("cidade");
                int dirigenteId = Integer.parseInt(req.getParameter("dirigenteId"));

                Time time = new Time();
                time.setNome(nome);
                time.setCidade(cidade);
                time.setDirigenteId(dirigenteId);

                service.inserir(time);

                resp.sendRedirect("meu-time.jsp");
                return;

            } else if (opcao.equals("alterar")) {

                String idStr = req.getParameter("id");
                if (idStr == null || idStr.isEmpty()) {
                    resp.sendRedirect("erro.jsp");
                    return;
                }

                int id = Integer.parseInt(idStr);
                String nome = req.getParameter("nome");
                String cidade = req.getParameter("cidade");
                String dirigenteIdStr = req.getParameter("dirigenteId");

                int dirigenteId = 0;
                if (dirigenteIdStr != null && !dirigenteIdStr.isEmpty()) {
                    dirigenteId = Integer.parseInt(dirigenteIdStr);
                }

                Time time = new Time();
                time.setId(id);
                time.setNome(nome);
                time.setCidade(cidade);
                time.setDirigenteId(dirigenteId);

                service.alterar(time);

                resp.sendRedirect("meu-time.jsp");
                return;

            } else if (opcao.equals("excluir")) {

                String idStr = req.getParameter("id");
                if (idStr == null || idStr.isEmpty()) {
                    resp.sendRedirect("erro.jsp");
                    return;
                }

                int id = Integer.parseInt(idStr);

                service.excluir(id);

                resp.sendRedirect("meu-time.jsp");
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
            }

            resp.sendRedirect("meu-time.jsp");
            return;
        }

        RequestDispatcher rd = req.getRequestDispatcher("meu-time.jsp");
        rd.forward(req, resp);
    }
}
