package bz.java.motoreasy.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ListaFavoritos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "lista")
    Usuario pertencente;

    @OneToMany
    List<Moto> motos = new ArrayList<Moto>();

    public ListaFavoritos() {
    }

    public ListaFavoritos(Long id, Usuario pertencente, List<Moto> motos) {
        this.id = id;
        this.pertencente = pertencente;
        this.motos = motos;
    }

    public ListaFavoritos(Usuario pertencente, List<Moto> motos) {
        this.pertencente = pertencente;
        this.motos = motos;
    }

    public void adicionarFavorita(Moto moto){
        motos.add(moto);
    }

    public ListaFavoritos removerFavorita(Moto moto){
        motos.remove(moto);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getPertencente() {
        return pertencente;
    }

    public void setPertencente(Usuario pertencente) {
        this.pertencente = pertencente;
    }

    public List<Moto> getMotos() {
        return motos;
    }

    public void setMotos(List<Moto> motos) {
        this.motos = motos;
    }
}
