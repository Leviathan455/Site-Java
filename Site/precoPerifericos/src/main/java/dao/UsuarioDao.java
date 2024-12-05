package dao;

import model.Usuario;
import util.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDao {

    private Connection conexao;

    public UsuarioDao() {
        this.conexao = new ConexaoBanco().getConexao(); // Inicialização da conexão com o banco
    }

    // Método para inserir um novo usuário no banco de dados
    public boolean inserir(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha, status) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setBoolean(4, usuario.getStatus());
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para buscar um usuário pelo email
    public Optional<Usuario> buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_user"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setStatus(rs.getBoolean("status"));
                return Optional.of(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // Método para autenticar usuário por email e senha
    public Optional<Usuario> autenticar(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_user"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setStatus(rs.getBoolean("status"));
                return Optional.of(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // Outros métodos conforme necessidade: atualizar, excluir, listar todos, etc.

    public void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
