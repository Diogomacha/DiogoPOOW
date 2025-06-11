package br.csi.controller;

import br.csi.dao.DirigenteDAO;
import br.csi.model.Dirigente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String email = request.getParameter("email");
        String senha = request.getParameter("senha");


        if (email == null || senha == null || email.isEmpty() || senha.isEmpty()) {
            request.setAttribute("msg", "Preencha todos os campos.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }


        Dirigente dirigente = DirigenteDAO.buscarPorEmail(email);


        if (dirigente != null && dirigente.getSenha().equals(senha)) {

            HttpSession session = request.getSession();
            session.setAttribute("dirigenteLogado", dirigente);


            response.sendRedirect("home.jsp");
        } else {

            request.setAttribute("msg", "Email ou senha incorretos.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
