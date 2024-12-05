package model;

import java.io.Serializable;
import java.util.Objects;

public class Favorito implements Serializable {

    private int usuarioId;
    private int perifericoId;
    private Usuario usuario;
    private Periferico periferico;

    public Favorito() {}

    public Favorito(Usuario usuario, Periferico periferico) {
        this.usuario = usuario;
        this.periferico = periferico;
        this.usuarioId = usuario.getId();
        this.perifericoId = periferico.getId();
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getPerifericoId() {
        return perifericoId;
    }

    public void setPerifericoId(int perifericoId) {
        this.perifericoId = perifericoId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Periferico getPeriferico() {
        return periferico;
    }

    public void setPeriferico(Periferico periferico) {
        this.periferico = periferico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorito favorito = (Favorito) o;
        return usuarioId == favorito.usuarioId && perifericoId == favorito.perifericoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, perifericoId);
    }

    @Override
    public String toString() {
        return "Favorito{" +
                "usuarioId=" + usuarioId +
                ", perifericoId=" + perifericoId +
                ", usuario=" + usuario +
                ", periferico=" + periferico +
                '}';
    }
}
