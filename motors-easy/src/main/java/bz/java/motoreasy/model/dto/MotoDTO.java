package bz.java.motoreasy.model.dto;

public class MotoDTO {
    private String modelo;
    private int cilindradas;
    private double preco;
    private boolean automatica;

    public MotoDTO() {
    }

    public MotoDTO(String modelo, int cilindradas, double preco, boolean automatica) {
        this.modelo = modelo;
        this.cilindradas = cilindradas;
        this.preco = preco;
        this.automatica = automatica;
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
}
