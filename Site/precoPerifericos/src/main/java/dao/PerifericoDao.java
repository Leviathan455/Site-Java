package dao;

import model.Periferico;
import model.PrecoLoja;
import util.ConexaoBanco;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class PerifericoDao {

    // Método para cadastrar um novo periférico no banco de dados
    public int cadastrarPeriferico(Periferico periferico, InputStream imagemStream) {
        try {
            ConexaoBanco c = new ConexaoBanco();
            String sql = "INSERT INTO periferico (nome, descricao, imagem, marca, idcategoria) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = c.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, periferico.getNome());
            stmt.setString(2, periferico.getDescricao());

            if (imagemStream != null) {
                stmt.setBinaryStream(3, imagemStream);
            } else {
                stmt.setNull(3, java.sql.Types.BLOB);
            }

            stmt.setString(4, periferico.getMarca());
            stmt.setInt(5, periferico.getIdCategoria());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    // Método para listar todos os periféricos do banco de dados
    public ArrayList<Periferico> listarPeriferico() {
        ArrayList<Periferico> ps = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            ConexaoBanco c = new ConexaoBanco();
            conn = c.getConexao();
            stmt = conn.prepareStatement("SELECT * FROM periferico");
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idperiferico");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                byte[] imagemBytes = rs.getBytes("imagem");
                String marca = rs.getString("marca");
                int idCategoria = rs.getInt("idcategoria");

                Periferico p = new Periferico(id, nome, descricao, imagemBytes, marca, idCategoria);
                ps.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return ps;
    }

    // Método para listar de acordo com categoria (id)
    public ArrayList<Periferico> listarPerifericos(Integer id_) {
        ArrayList<Periferico> ps = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            ConexaoBanco c = new ConexaoBanco();
            conn = c.getConexao();
            stmt = conn.prepareStatement("SELECT * FROM periferico WHERE idcategoria = ?");
            stmt.setInt(1, id_);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idperiferico");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                byte[] imagemBytes = rs.getBytes("imagem");
                String marca = rs.getString("marca");
                int idCategoria = rs.getInt("idcategoria");

                Periferico p = new Periferico(id, nome, descricao, imagemBytes, marca, idCategoria);
                ps.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return ps;
    }

    // Método para listar um periférico específico pelo ID
    public Periferico listarPeriferico(String id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            ConexaoBanco c = new ConexaoBanco();
            conn = c.getConexao();
            stmt = conn.prepareStatement("SELECT * FROM periferico WHERE idperiferico = ?");
            stmt.setInt(1, Integer.parseInt(id));
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id_ = rs.getInt("idperiferico");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                byte[] imagemBytes = rs.getBytes("imagem");
                String marca = rs.getString("marca");
                int idCategoria = rs.getInt("idcategoria");

                return new Periferico(id_, nome, descricao, imagemBytes, marca, idCategoria);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    // Método para listar os preços de um periférico específico
    public ArrayList<PrecoLoja> listarPrecoPeriferico(String id) {
        ArrayList<PrecoLoja> pl = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            ConexaoBanco c = new ConexaoBanco();
            conn = c.getConexao();
            stmt = conn.prepareStatement("SELECT * FROM precoloja WHERE fkidperiferico = ?");
            stmt.setInt(1, Integer.parseInt(id));
            rs = stmt.executeQuery();

            while (rs.next()) {
                PrecoLoja p = new PrecoLoja(rs.getInt("id"), rs.getInt("fkidperiferico"), rs.getFloat("preco"), rs.getString("loja"), rs.getString("link"));
                pl.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return pl;
    }

    // Método para editar um periférico existente no banco de dados
    public int editarPeriferico(Periferico p, InputStream imagemStream) {
        try (Connection conn = new ConexaoBanco().getConexao()) {
            // Buscar os dados atuais do periférico
            String sqlBusca = "SELECT nome, descricao, imagem, marca, idcategoria FROM periferico WHERE idperiferico = ?";
            try (PreparedStatement stmtBusca = conn.prepareStatement(sqlBusca)) {
                stmtBusca.setInt(1, p.getId());
                ResultSet rs = stmtBusca.executeQuery();

                if (rs.next()) {
                    // Preservar os dados antigos caso os novos sejam nulos
                    if (p.getNome() == null || p.getNome().isEmpty()) {
                        p.setNome(rs.getString("nome"));
                    }
                    if (p.getDescricao() == null || p.getDescricao().isEmpty()) {
                        p.setDescricao(rs.getString("descricao"));
                    }
                    // Não preservamos a imagem antiga neste caso
                    if (p.getMarca() == null || p.getMarca().isEmpty()) {
                        p.setMarca(rs.getString("marca"));
                    }
                    if (p.getIdCategoria() <= 0) {
                        p.setIdCategoria(rs.getInt("idcategoria"));
                    }
                }
            }

            // Atualizar os dados do periférico
            String sql = "UPDATE periferico SET nome = ?, descricao = ?, marca = ?, idcategoria = ?";
            if (imagemStream != null && imagemStream.available() > 0) {
                sql += ", imagem = ?";
                conn.setAutoCommit(false);
            }
            sql += " WHERE idperiferico = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, p.getNome());
                stmt.setString(2, p.getDescricao());
                stmt.setString(3, p.getMarca());
                stmt.setInt(4, p.getIdCategoria());

                int parameterIndex = 5;
                if (imagemStream != null && imagemStream.available() > 0) {
                    stmt.setBinaryStream(parameterIndex++, imagemStream);
                }

                stmt.setInt(parameterIndex, p.getId());
                int retorno = stmt.executeUpdate();

                if (!conn.getAutoCommit()) {
                    conn.commit();
                }

                return retorno;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Método para excluir um periférico do banco de dados
    public int excluirPeriferico(int id) {
        try {
            ConexaoBanco c = new ConexaoBanco();
            Statement stmt = c.getConexao().createStatement();
            String sql = "DELETE FROM periferico WHERE idperiferico = " + id;
            stmt.execute(sql);

            return 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
