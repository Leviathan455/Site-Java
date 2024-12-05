package service;

import dao.PerifericoDao;
import model.Periferico;
import model.PrecoLoja;

import java.io.InputStream;
import java.util.ArrayList;

public class PerifericoService {

    private PerifericoDao perifericoDao;

    public PerifericoService() {
        this.perifericoDao = new PerifericoDao();
    }

    public int cadastrarPeriferico(Periferico periferico, InputStream imagemStream) {
        return perifericoDao.cadastrarPeriferico(periferico, imagemStream);
    }

    public int editarPeriferico(Periferico p, InputStream imagemStream) {
        return perifericoDao.editarPeriferico(p, imagemStream);
    }

    public ArrayList<Periferico> listarPerifericos() {
        return perifericoDao.listarPeriferico();
    }

    public ArrayList<Periferico> listarPerifericos(Integer id) {
        return perifericoDao.listarPerifericos(id);
    }


    public ArrayList<PrecoLoja> listarPrecoPeriferico(String id) {
        return perifericoDao.listarPrecoPeriferico(id);
    }

    public int excluirPeriferico(int id) {
        return perifericoDao.excluirPeriferico(id);
    }

    public Periferico infoPeriferico(String id) {
        return perifericoDao.listarPeriferico(id);
    }


}
