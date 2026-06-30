package model;


public abstract class Carta {
    private String cor;
    private String tipo;
    
    public Carta(String cor, String tipo){
        this.cor = cor;
        this.tipo = tipo;
    }
    
    public abstract String aplicarEfeito();
}
