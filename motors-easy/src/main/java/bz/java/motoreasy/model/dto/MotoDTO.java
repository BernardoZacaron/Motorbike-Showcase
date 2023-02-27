package bz.java.motoreasy.model.dto;

public class MotoDTO {
    private String marca, modelo, terreno;
    private int cilindradas;
    private double preco;
    private boolean automatica, abs;

    public MotoDTO() {
    }

    public MotoDTO(String marca, String modelo, int cilindradas, double preco, boolean automatica, boolean abs, String terreno) {
        this.marca = marca;
        this.modelo = modelo;
        this.terreno = terreno;
        this.cilindradas = cilindradas;
        this.preco = preco;
        this.automatica = automatica;
        this.abs = abs;
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
}
