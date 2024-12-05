package service;

import dao.CategoriaDao;
import model.Categoria;

import java.sql.SQLException;
import java.util.List;

public class CategoriaService {

    private CategoriaDao categoriaDao;

    public CategoriaService() {
        this.categoriaDao = new CategoriaDao();
    }

    public boolean cadastrarCategoria(Categoria categoria) {
        return categoriaDao.inserirCategoria(categoria);
    }

    public List<Categoria> listarCategorias() throws SQLException {
        return categoriaDao.listarCategorias();
    }
    public boolean excluircategoria(int id) throws SQLException {
        return categoriaDao.excluircategoria(id);
    }

}
