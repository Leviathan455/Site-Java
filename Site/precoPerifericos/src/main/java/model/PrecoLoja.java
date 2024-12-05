package model;

public class PrecoLoja {

    private int idPrecoLoja;
    private int id;
    private float preco;
    private String loja;
    private String link;

    public PrecoLoja(int id, float preco, String loja) {
        this.id = id;
        this.preco = preco;
        this.loja = loja;
    }

    public PrecoLoja(int id, float preco, String loja, String link) {
        this.id = id;
        this.preco = preco;
        this.loja = loja;
        this.link = link;
    }

    public PrecoLoja(int idPrecoLoja, int id, float preco, String loja, String link) {
        this.idPrecoLoja = idPrecoLoja;
        this.id = id;
        this.preco = preco;
        this.loja = loja;
        this.link = link;
    }

    public int getIdPrecoLoja() {
        return idPrecoLoja;
    }

    public void setIdPrecoLoja(int idPrecoLoja) {
        this.idPrecoLoja = idPrecoLoja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "PrecoLoja{" +
                "idPrecoLoja=" + idPrecoLoja +
                ", id=" + id +
                ", preco=" + preco +
                ", loja='" + loja + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
