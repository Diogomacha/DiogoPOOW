package br.csi.controller;

import br.csi.model.Dirigente;
import br.csi.service.DirigenteService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/dirigente")
public class DirigenteServlet extends HttpServlet {

    private static DirigenteService service = new DirigenteService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String opcao = req.getParameter("opcao");
        System.out.println("-----> POST opcao: " + opcao);

        if (opcao != null) {

            if (opcao.equals("cadastrar")) {

                String nome = req.getParameter("nome");
                String email = req.getParameter("email");
                String senha = req.getParameter("senha");

                Dirigente dirigente = new Dirigente();
                dirigente.setNome(nome);
                dirigente.setEmail(email);
                dirigente.setSenha(senha);

                service.inserir(dirigente);

                resp.sendRedirect("index.jsp");
                return;

            } else if (opcao.equals("alterar")) {

                String idStr = req.getParameter("id");
                if (idStr == null || idStr.isEmpty()) {
                    resp.sendRedirect("erro.jsp");
                    return;
                }

                int id = Integer.parseInt(idStr);
                String nome = req.getParameter("nome");
                String email = req.getParameter("email");
                String senha = req.getParameter("senha");

                Dirigente dirigente = new Dirigente();
                dirigente.setId(id);
                dirigente.setNome(nome);
                dirigente.setEmail(email);
                dirigente.setSenha(senha);

                service.alterar(dirigente);


                req.getSession().setAttribute("dirigenteLogado", dirigente);

                resp.sendRedirect("perfil-dirigente.jsp");
                return;

            } else if (opcao.equals("excluir")) {

                String idStr = req.getParameter("id");
                if (idStr == null || idStr.isEmpty()) {
                    resp.sendRedirect("erro.jsp");
                    return;
                }

                int id = Integer.parseInt(idStr);

                service.excluir(id);


                req.getSession().invalidate();
                resp.sendRedirect("index.jsp");
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

                
                req.getSession().invalidate();

                resp.sendRedirect("index.jsp");
                return;
            } else {
                resp.sendRedirect("erro.jsp");
                return;
            }
        }


        RequestDispatcher rd = req.getRequestDispatcher("perfil-dirigente.jsp");
        rd.forward(req, resp);
    }
}
