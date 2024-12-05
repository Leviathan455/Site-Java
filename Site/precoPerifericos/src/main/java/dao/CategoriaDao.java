package dao;

import model.Categoria;
import util.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {

    private Connection conn;

    public CategoriaDao() {
        ConexaoBanco conexao = new ConexaoBanco();
        this.conn = conexao.getConexao(); // Obtém a conexão usando a instância criada
    }

    // Método para inserir uma nova categoria no banco de dados
    public boolean inserirCategoria(Categoria categoria) {
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO categoria (nome) VALUES (?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoria.getNome());

            int rows = stmt.executeUpdate();

            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Método para listar todas as categorias
    public List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT idcategoria, nome FROM categoria";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria(
                        rs.getInt("idcategoria"),
                        rs.getString("nome")
                );
                categorias.add(categoria);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Tratar exceção de SQL, se necessário
        }

        return categorias;
    }

    public boolean excluircategoria(int categoriaId) {
        String sql = "DELETE FROM categoria WHERE idcategoria = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoriaId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("não excluiu uma categoria --------------------------------");
            e.printStackTrace();
            return false;
        }
    }

}
