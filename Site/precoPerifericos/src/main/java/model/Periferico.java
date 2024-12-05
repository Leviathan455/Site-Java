package model;

import java.util.Base64;

public class Periferico {
    private int id;
    private String nome;
    private String descricao;
    private byte[] imagem;
    private String imagemBase64;
    private String marca;
    private int idCategoria;

    // Construtor para criar um novo periférico sem ID (para inserção no banco)
    public Periferico(String nome, String descricao, byte[] imagem, String marca, int idCategoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.marca = marca;
        this.idCategoria = idCategoria;
        this.imagemBase64 = imagem != null ? Base64.getEncoder().encodeToString(imagem) : null;
    }

    // Construtor para recuperar periférico do banco (com ID)
    public Periferico(int id, String nome, String descricao, byte[] imagem, String marca, int idCategoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.marca = marca;
        this.idCategoria = idCategoria;
        this.imagemBase64 = imagem != null ? Base64.getEncoder().encodeToString(imagem) : null;
    }

    public Periferico() {
    }

    // Construtor simplificado
    public Periferico(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
        this.imagemBase64 = imagem != null ? Base64.getEncoder().encodeToString(imagem) : null;
    }

    public String getImagemBase64() {
        return imagemBase64;
    }

    public void setImagemBase64(String imagemBase64) {
        this.imagemBase64 = imagemBase64;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "Periferico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", marca='" + marca + '\'' +
                ", idCategoria=" + idCategoria +
                '}';
    }
}
