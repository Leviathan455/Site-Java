package controller;

import dao.UsuarioDao;
import model.Usuario;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDao usuarioDao;

    @Override
    public void init() throws ServletException {
        super.init();
        usuarioDao = new UsuarioDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("username");
        String senha = request.getParameter("password");

        Optional<Usuario> usuarioOptional = usuarioDao.autenticar(email, senha);


        if (usuarioOptional.isPresent()) {
            request.getSession().setAttribute("user", usuarioOptional.get());
            response.sendRedirect(request.getContextPath() + "/periferico");
        } else {
            request.setAttribute("mensagem", "Credenciais inválidas. Verifique seu usuário e senha.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request, response);
        }
    }



    protected void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("logout".equals(action)) {



            logout(request, response);
            try {

                request.getSession().invalidate(); // Invalida a sessão atual
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } catch (IllegalStateException e) {
                // Trata a exceção caso ocorra ao invalidar a sessão
                throw new ServletException("Erro ao invalidar a sessão: " + e.getMessage(), e);
            } catch (IOException e) {
                // Trata a exceção caso ocorra ao redirecionar para a página inicial
                throw new ServletException("Erro ao redirecionar para a página inicial: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        usuarioDao.fecharConexao();
    }
}
