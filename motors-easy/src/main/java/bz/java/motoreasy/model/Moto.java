package bz.java.motoreasy.model;

import bz.java.motoreasy.model.dto.MotoDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String marca;
    private String modelo;
    private int cilindradas;
    private double preco;
    private boolean automatica;
    private boolean abs;
    private String terreno;
    private boolean anuncioAtivo = true;

    @OneToMany
    private List<Usuario> usuarios;


    public Moto() {
    }

    public Moto(long id, String marca, String modelo, int cilindradas, double preco, boolean automatica, boolean abs, String terreno, boolean anuncioAtivo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.cilindradas = cilindradas;
        this.preco = preco;
        this.automatica = automatica;
        this.abs = abs;
        this.terreno = terreno;
        this.anuncioAtivo = anuncioAtivo;
    }

    public Moto(String marca, String modelo, int cilindradas, double preco, boolean automatica, boolean abs, String terreno, boolean anuncioAtivo) {
        this.marca = marca;
        this.modelo = modelo;
        this.cilindradas = cilindradas;
        this.preco = preco;
        this.automatica = automatica;
        this.abs = abs;
        this.terreno = terreno;
        this.anuncioAtivo = anuncioAtivo;
    }

    public Moto(MotoDTO dto){
        this.marca = dto.getMarca();
        this.modelo = dto.getModelo();
        this.cilindradas = dto.getCilindradas();
        this.preco = dto.getPreco();
        this.automatica = dto.isAutomatica();
        this.abs = dto.isAbs();
        this.terreno = dto.getTerreno();
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean isAbs() {
        return abs;
    }

    public void setAbs(boolean abs) {
        this.abs = abs;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public boolean isAnuncioAtivo() {
        return anuncioAtivo;
    }

    public void setAnuncioAtivo(boolean anuncioAtivo) {
        this.anuncioAtivo = anuncioAtivo;
    }
}
