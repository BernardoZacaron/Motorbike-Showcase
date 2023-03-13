package bz.java.motoreasy.model.dto;

import bz.java.motoreasy.model.Moto;

public class MotoDTO {
    private long id;
    private String marca, modelo, terreno;
    private int cilindradas;
    private double preco;
    private boolean automatica, abs;
    private boolean favoritada;
    private boolean visivel;
    private String imagemUrl;

    public MotoDTO() {
    }

    public MotoDTO(long id, String marca, String modelo, String terreno, int cilindradas, double preco, boolean automatica, boolean abs, boolean favoritada, boolean visivel, String imagemUrl) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.terreno = terreno;
        this.cilindradas = cilindradas;
        this.preco = preco;
        this.automatica = automatica;
        this.abs = abs;
        this.favoritada = favoritada;
        this.visivel = visivel;
        this.imagemUrl = imagemUrl;
    }

    public MotoDTO(String marca, String modelo, String terreno, int cilindradas, double preco, boolean automatica, boolean abs, boolean favoritada, boolean visivel, String imagemUrl) {
        this.marca = marca;
        this.modelo = modelo;
        this.terreno = terreno;
        this.cilindradas = cilindradas;
        this.preco = preco;
        this.automatica = automatica;
        this.abs = abs;
        this.favoritada = favoritada;
        this.visivel = visivel;
        this.imagemUrl = imagemUrl;
    }

    public MotoDTO(String marca, String modelo, String terreno, int cilindradas, double preco, boolean automatica, boolean abs, boolean favoritada, boolean visivel) {
        this.marca = marca;
        this.modelo = modelo;
        this.terreno = terreno;
        this.cilindradas = cilindradas;
        this.preco = preco;
        this.automatica = automatica;
        this.abs = abs;
        this.favoritada = favoritada;
        this.visivel = visivel;
    }

    public MotoDTO(Moto moto, boolean favoritada){
        this.id = moto.getId();
        this.marca = moto.getMarca();
        this.modelo = moto.getModelo();
        this.terreno = moto.getTerreno();
        this.cilindradas = moto.getCilindradas();
        this.preco = moto.getPreco();
        this.automatica = moto.isAutomatica();
        this.abs = moto.isAbs();
        this.favoritada = favoritada;
        this.visivel = moto.isAnuncioAtivo();
        this.imagemUrl = moto.getImagemUrl();
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

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public boolean isAbs() {
        return abs;
    }

    public void setAbs(boolean abs) {
        this.abs = abs;
    }

    public boolean isFavoritada() {
        return favoritada;
    }

    public void setFavoritada(boolean favoritada) {
        this.favoritada = favoritada;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
}
