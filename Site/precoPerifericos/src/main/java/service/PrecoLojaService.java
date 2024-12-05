package service;

import dao.PrecoLojaDao;
import model.PrecoLoja;

public class PrecoLojaService {

    private PrecoLojaDao precoLojaDao = new PrecoLojaDao();

    public int cadastrarPrecoLoja(PrecoLoja precoLoja) {
        return precoLojaDao.cadastrarPrecoLoja(precoLoja);
    }

    public int editarPrecoLoja(PrecoLoja precoLoja) {
        return precoLojaDao.editarPrecoLoja(precoLoja);
    }

    public int excluirPrecoLoja(int id) {
        return precoLojaDao.excluirPrecoLoja(id);
    }
}
