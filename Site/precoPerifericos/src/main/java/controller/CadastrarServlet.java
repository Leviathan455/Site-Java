package controller;

import dao.UsuarioDao;
import model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CadastrarServlet", urlPatterns = {"/cadastro"})
public class CadastrarServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");


        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setStatus(false);


        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.inserir(usuario);


        response.sendRedirect("Login.jsp");
    }
}
