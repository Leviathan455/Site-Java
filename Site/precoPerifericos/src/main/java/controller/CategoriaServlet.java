package controller;

import model.Categoria;
import service.CategoriaService;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import sun.jvm.hotspot.types.CIntegerType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoriaServlet", urlPatterns = {"/categoria"})
public class CategoriaServlet extends HttpServlet {

    private CategoriaService categoriaService;

    @Override
    public void init() throws ServletException {
        super.init();
        categoriaService = new CategoriaService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if (acao != null) {
            switch (acao) {
                case "cadastrar":
                    cadastrarCategoria(request, response);
                    break;
                case "excluir":
                    excluircategoria(request,response);
                default:
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                    break;
            }
        } else {
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }



    private void cadastrarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeCategoria = request.getParameter("nome");

        Categoria categoria = new Categoria(nomeCategoria);

        if (categoriaService.cadastrarCategoria(categoria)) {
            response.sendRedirect("index.jsp");
        } else {
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Categoria> categorias = categoriaService.listarCategorias();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");


            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(categorias);


            response.getWriter().write(json);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private void excluircategoria(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String nomeCategoria = request.getParameter("nome");
            try {
                boolean sucesso = categoriaService.excluircategoria(Integer.parseInt(request.getParameter("id")));

                if (sucesso) {
                    response.sendRedirect("index.jsp");
                } else {
                    response.sendRedirect("ExcluirCategoria.jsp");
                }
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }
}
