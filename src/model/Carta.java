package model;


public abstract class Carta {
    private String cor;
    private String tipo;
    
    public Carta(String cor, String tipo){
        this.cor = cor;
        this.tipo = tipo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
}
