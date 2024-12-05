package controller;

import jakarta.servlet.http.HttpSession;
import model.Favorito;
import model.Periferico;
import model.Usuario;
import service.FavoritoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/favorito")
public class FavoritoServlet extends HttpServlet {
    private FavoritoService favoritoService;

    @Override
    public void init() throws ServletException {
        super.init();
        favoritoService = new FavoritoService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("user");
        Integer userId = user != null ? user.getId() : null;
        String perifericoIdStr = request.getParameter("perifericoId");

        if (userId != null) {
            try {
                if (perifericoIdStr == null || perifericoIdStr.isEmpty()) {
                    throw new IllegalArgumentException("ID do periférico não pode estar vazio");
                }

                int perifericoId = Integer.parseInt(perifericoIdStr);

                Usuario usuario = new Usuario();
                usuario.setId(userId);
                Periferico periferico = new Periferico();
                periferico.setId(perifericoId);

                Favorito favorito = new Favorito(usuario, periferico);

                String method = request.getParameter("_method");
                if (method == null || method.isEmpty()) {
                    favoritoService.addFavorito(favorito);
                } else if (method.equals("delete")) {
                    favoritoService.deleteFavorito(favorito);
                }

                response.sendRedirect("index.jsp");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "IDs inválidos fornecidos");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não autenticado");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("user");
        Integer userId = user != null ? user.getId() : null;

        if (userId != null) {
            try {
                List<Favorito> favoritos = favoritoService.buscarFavoritosPorUsuarioId(userId);
                request.setAttribute("favoritos", favoritos);
                request.getRequestDispatcher("/WEB-INF/pages/Favorito.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao listar favoritos");
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não autenticado");
        }
    }
}
