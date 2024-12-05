package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        String jspPage;

        if (path == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        switch (path) {
            case "/cadastrarCategoria":
                jspPage = "/WEB-INF/pages/CadastrarCategoria.jsp";
                break;
            case "/cadastrarPeriferico":
                jspPage = "/WEB-INF/pages/cadastrarPerifericos.jsp";
                break;
            case "/cadastrarPrecoLoja":
                jspPage = "/WEB-INF/pages/cadastrarPrecoLoja.jsp";
                break;
            case "/editarPeriferico":
                jspPage = "/WEB-INF/pages/editarPeriferico.jsp";
                break;
            case "/editarPrecoLoja":
                jspPage = "/WEB-INF/pages/editarPrecoLoja.jsp";
                break;
            case "/excluirCategoria":
                jspPage = "/WEB-INF/pages/ExcluirCategoria.jsp";
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(jspPage);
        dispatcher.forward(request, response);
    }
}

