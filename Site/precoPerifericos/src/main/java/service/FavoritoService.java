package service;

import model.Favorito;
import dao.FavoritoDao;

import java.util.List;

public class FavoritoService {
    private FavoritoDao favoritoDao;

    public FavoritoService() {
        this.favoritoDao = new FavoritoDao();
    }

    public void addFavorito(Favorito favorito) {
        favoritoDao.salvar(favorito);
    }

    public List<Favorito> buscarFavoritosPorUsuarioId(int usuarioId) {
        return favoritoDao.listarFavoritosPorUsuario(usuarioId);
    }

    public void deleteFavorito(Favorito favorito) {
        favoritoDao.deletar(favorito.getUsuarioId(), favorito.getPerifericoId());
    }
}
