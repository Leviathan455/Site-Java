package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Periferico;
import model.PrecoLoja;
import service.PerifericoService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/periferico")
@MultipartConfig
public class PerifericoServlet extends HttpServlet {

    private PerifericoService perifericoService;

    @Override
    public void init() throws ServletException {
        super.init();
        perifericoService = new PerifericoService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String opcao = req.getParameter("opcao");

        try {
            if ("editar".equals(opcao)) {
                editarPeriferico(req, resp);
            } else if ("cadastrar".equals(opcao)) {
                cadastrarPeriferico(req, resp);
            } else {
                System.out.println("Opção inválida: " + opcao);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("erro", "ID inválido");
            RequestDispatcher r = req.getRequestDispatcher("erro.jsp");
            r.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");

        try {
            if ("verprecos".equals(opcao)) {
                verPrecos(req, resp);
            } else if ("excluir".equals(opcao)) {
                excluirPeriferico(req, resp);
            } else {
                listarPerifericos(req, resp);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("erro", "ID inválido");
            RequestDispatcher r = req.getRequestDispatcher("erro.jsp");
            r.forward(req, resp);
        }
    }

    private void editarPeriferico(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        String marca = req.getParameter("marca");
        int categoria = Integer.parseInt(req.getParameter("categoria"));

        InputStream imagemStream = null;
        if (req.getPart("imagem") != null && req.getPart("imagem").getSize() > 0) {
            imagemStream = req.getPart("imagem").getInputStream();
        }

        Periferico p = new Periferico(id, nome, descricao, null, marca, categoria);

        if (perifericoService.editarPeriferico(p, imagemStream) == 1) {
            req.setAttribute("retorno", "Sucesso");
        } else {
            req.setAttribute("retorno", "Tente Novamente");
        }

        RequestDispatcher r = req.getRequestDispatcher("editarPeriferico.jsp");
        r.forward(req, resp);
    }

    private void cadastrarPeriferico(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        String marca = req.getParameter("marca");
        int categoria = Integer.parseInt(req.getParameter("categoria"));

        InputStream imagemStream = null;
        if (req.getPart("imagem") != null && req.getPart("imagem").getSize() > 0) {
            imagemStream = req.getPart("imagem").getInputStream();
        }

        Periferico p = new Periferico(nome, descricao, null, marca, categoria);

        if (perifericoService.cadastrarPeriferico(p, imagemStream) == 1) {
            req.setAttribute("retorno", "Sucesso");
        } else {
            req.setAttribute("retorno", "Tente Novamente");
        }

        resp.sendRedirect("periferico");
    }

    private void verPrecos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nome = req.getParameter("nome");

        Periferico periferico = perifericoService.infoPeriferico(String.valueOf(id));

        ArrayList<PrecoLoja> pl = perifericoService.listarPrecoPeriferico(String.valueOf(id));

        req.setAttribute("periferico", periferico);
        req.setAttribute("precos", pl);
        req.setAttribute("nome", nome);

        RequestDispatcher r = req.getRequestDispatcher("listarPrecoPeriferico.jsp");
        r.forward(req, resp);
    }

    private void excluirPeriferico(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        if (perifericoService.excluirPeriferico(id) == 1) {
            resp.sendRedirect("periferico");
        } else {
            req.setAttribute("retorno", "Não é possível excluir");
            resp.sendRedirect("periferico?erro=ok");
        }
    }

    private void listarPerifericos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cat = req.getParameter("categoria");

        ArrayList<Periferico> ps;

        if(cat != null) {
             ps = perifericoService.listarPerifericos(Integer.parseInt(cat));
        }else {
             ps = perifericoService.listarPerifericos();
        }

        req.setAttribute("perifericos", ps);

        RequestDispatcher r = req.getRequestDispatcher("listarPerifericos.jsp");
        r.forward(req, resp);
    }
}
