package dao;

import model.PrecoLoja;
import util.ConexaoBanco;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrecoLojaDao {

    public int cadastrarPrecoLoja(PrecoLoja pl) {
        System.out.println("passou aqui DAO1");
        try {
            System.out.println("passou aqui DAO2");
            ConexaoBanco c = new ConexaoBanco();
            PreparedStatement stmt = c.getConexao().prepareStatement(
                    "INSERT INTO precoloja (fkidperiferico, preco, loja, link) VALUES (?, ?, ?, ?)");
            System.out.println("passou aqui DAO3");
            stmt.setInt(1, pl.getId());
            stmt.setFloat(2, pl.getPreco());
            stmt.setString(3, pl.getLoja());
            stmt.setString(4, pl.getLink());

            return stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public int editarPrecoLoja(PrecoLoja pl) {
        try {
            ConexaoBanco c = new ConexaoBanco();
            PreparedStatement stmt = c.getConexao().prepareStatement(
                    "UPDATE precoloja SET preco = ?, loja = ?, link = ? WHERE id = ?");
            stmt.setFloat(1, pl.getPreco());
            stmt.setString(2, pl.getLoja());
            stmt.setString(3, pl.getLink());
            stmt.setInt(4, pl.getIdPrecoLoja());

            return stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public int excluirPrecoLoja(int id) {
        try {
            ConexaoBanco c = new ConexaoBanco();
            PreparedStatement stmt = c.getConexao().prepareStatement("DELETE FROM precoloja WHERE id = ?");
            stmt.setInt(1, id);

            return stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
