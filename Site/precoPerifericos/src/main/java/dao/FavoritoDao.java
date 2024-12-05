package dao;

import model.Favorito;
import model.Usuario;
import model.Periferico;
import util.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoritoDao {

    public FavoritoDao() {
    }

    public void salvar(Favorito favorito) {
        String sql = "INSERT INTO favorito (id_usuario, id_periferico) VALUES (?, ?)";
        ConexaoBanco c = new ConexaoBanco();

        try (Connection connection = c.getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, favorito.getUsuario().getId());
            statement.setInt(2, favorito.getPeriferico().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar favorito", e);
        }
    }

    public void deletar(int usuarioId, int perifericoId) {
        String sql = "DELETE FROM favorito WHERE id_usuario = ? AND id_periferico = ?";

        ConexaoBanco c = new ConexaoBanco();
        try (Connection connection =  c.getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, usuarioId);
            statement.setInt(2, perifericoId);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar favorito", e);
        }
    }

    public List<Favorito> listarFavoritosPorUsuario(int usuarioId) {
        String sql = "SELECT * FROM favorito WHERE id_usuario = ?";
        List<Favorito> favoritos = new ArrayList<>();

        ConexaoBanco c = new ConexaoBanco();

        try (Connection connection = c.getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, usuarioId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Favorito favorito = new Favorito();
                    Usuario usuario = new Usuario();
                    Periferico periferico = new Periferico();

                    usuario.setId(resultSet.getInt("id_usuario"));
                    periferico.setId(resultSet.getInt("id_periferico"));

                    favorito.setUsuario(usuario);
                    favorito.setPeriferico(periferico);

                    favoritos.add(favorito);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar favoritos", e);
        }
        return favoritos;
    }

    public Favorito buscarFavorito(int usuarioId, int perifericoId) {
        String sql = "SELECT * FROM favorito WHERE id_usuario = ? AND id_periferico = ?";
        ConexaoBanco c = new ConexaoBanco();

        try (Connection connection = c.getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, usuarioId);
            statement.setInt(2, perifericoId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Favorito favorito = new Favorito();
                    Usuario usuario = new Usuario();
                    Periferico periferico = new Periferico();

                    usuario.setId(resultSet.getInt("id_usuario"));
                    periferico.setId(resultSet.getInt("id_periferico"));

                    favorito.setUsuario(usuario);
                    favorito.setPeriferico(periferico);

                    return favorito;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar favorito", e);
        }
        return null;
    }
}
