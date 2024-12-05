package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PrecoLoja;
import service.PrecoLojaService;

import java.io.IOException;

@WebServlet("/precoloja")
public class PrecoLojaServlet extends HttpServlet {

    private final PrecoLojaService precoLojaService = new PrecoLojaService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opcao = req.getParameter("opcao");

        switch (opcao) {
            case "cadastrar":
                cadastrarPreco(req, resp);
                break;
            case "editar":
                editarPreco(req, resp);
                break;
            case "excluir":
                excluirPreco(req, resp);
                break;
            default:
                req.setAttribute("retorno", "Opção inválida");
                RequestDispatcher r = req.getRequestDispatcher("editarPrecoLoja.jsp");
                r.forward(req, resp);
        }
    }

    private void cadastrarPreco(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int idPeriferico = Integer.parseInt(req.getParameter("idperiferico"));
            String loja = req.getParameter("loja");
            float preco = Float.parseFloat(req.getParameter("preco"));
            String link = req.getParameter("link");

            System.out.println("passou servlett 1");
            PrecoLoja precoLoja = new PrecoLoja(idPeriferico, preco, loja, link);
            int resultadoCadastro = precoLojaService.cadastrarPrecoLoja(precoLoja);


            System.out.println("passou servlett 2");
            req.setAttribute("retorno", resultadoCadastro == 1 ? "Preço da loja cadastrado com sucesso." : "Erro ao cadastrar preço da loja.");
        } catch (NumberFormatException e) {
            req.setAttribute("retorno", "Erro: ID ou preço inválidos");
        }

        RequestDispatcher r = req.getRequestDispatcher("index.jsp");
        r.forward(req, resp);
    }

    private void editarPreco(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int idPrecoLoja = Integer.parseInt(req.getParameter("idprecoloja"));
            int idPeriferico = Integer.parseInt(req.getParameter("idperiferico"));
            String loja = req.getParameter("loja");
            float preco = Float.parseFloat(req.getParameter("preco"));
            String link = req.getParameter("link");

            PrecoLoja precoLoja = new PrecoLoja(idPrecoLoja, idPeriferico, preco, loja, link);
            int resultadoEdicao = precoLojaService.editarPrecoLoja(precoLoja);

            req.setAttribute("retorno", resultadoEdicao == 1 ? "Preço da loja editado com sucesso." : "Erro ao editar preço da loja.");
        } catch (NumberFormatException e) {
            req.setAttribute("retorno", "Erro: ID ou preço inválidos");
            e.printStackTrace();
        }

        RequestDispatcher r = req.getRequestDispatcher("index.jsp");
        r.forward(req, resp);
    }

    private void excluirPreco(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int idPrecoLoja = Integer.parseInt(req.getParameter("idprecoloja"));
            int resultadoExclusao = precoLojaService.excluirPrecoLoja(idPrecoLoja);

            req.setAttribute("retorno", resultadoExclusao == 1 ? "Preço da loja excluído com sucesso." : "Falha ao excluir preço da loja.");
        } catch (NumberFormatException e) {
            req.setAttribute("retorno", "Erro: ID inválido ao excluir preço da loja");
        }

        RequestDispatcher r = req.getRequestDispatcher("index.jsp");
        r.forward(req, resp);
    }
}
