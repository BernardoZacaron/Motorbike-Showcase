package bz.java.motoreasy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String modelo;
    private int cilindradas;
    private double preco;
    private boolean automatica;
    private boolean favorita; //provisorio

    public Moto() {
    }

    public Moto(long id, String modelo, int cilindradas, double preco, boolean automatica) {
        this.id = id;
        this.modelo = modelo;
        this.cilindradas = cilindradas;
        this.preco = preco;
        this.automatica = automatica;
    }

    public Moto(String modelo, int cilindradas, double preco, boolean automatica) {
        this.modelo = modelo;
        this.cilindradas = cilindradas;
        this.preco = preco;
        this.automatica = automatica;
    }

    public Moto(long id, String modelo, int cilindradas, double preco, boolean automatica, boolean favorita) {
        this.id = id;
        this.modelo = modelo;
        this.cilindradas = cilindradas;
        this.preco = preco;
        this.automatica = automatica;
        this.favorita = favorita;
    }

    public void toggleFavorito(){
        setFavorita(!isFavorita());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(int cilindradas) {
        this.cilindradas = cilindradas;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isAutomatica() {
        return automatica;
    }

    public void setAutomatica(boolean automatica) {
        this.automatica = automatica;
    }

    public boolean isFavorita() {
        return favorita;
    }

    public void setFavorita(boolean favorita) {
        this.favorita = favorita;
    }
}
