//package bz.java.motoreasy.model;
//
//import bz.java.motoreasy.model.util.AdicaoLista;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//public class ListaFavoritos {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//
//    @OneToOne
//    Usuario pertencente;
//
//    @OneToMany(mappedBy = "lista")
//    List<AdicaoLista> adicoes = new ArrayList<>();
//
//    public ListaFavoritos() {
//    }
//
//    public ListaFavoritos(long id, Usuario pertencente, List<AdicaoLista> adicoes) {
//        this.id = id;
//        this.pertencente = pertencente;
//        this.adicoes = adicoes;
//    }
//
//    public ListaFavoritos(Usuario pertencente, List<AdicaoLista> adicoes) {
//        this.pertencente = pertencente;
//        this.adicoes = adicoes;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public Usuario getPertencente() {
//        return pertencente;
//    }
//
//    public void setPertencente(Usuario pertencente) {
//        this.pertencente = pertencente;
//    }
//
//    public List<AdicaoLista> getAdicoes() {
//        return adicoes;
//    }
//
//    public void setAdicoes(List<AdicaoLista> adicoes) {
//        this.adicoes = adicoes;
//    }
//}
