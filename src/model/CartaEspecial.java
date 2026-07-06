package model;


public class CartaEspecial extends Carta{
    private String efeito;
    
    public CartaEspecial(String cor, String tipo, String efeito){
        super(cor, tipo);
        this.efeito = efeito;
    }
        
    public String getEfeito() {
        return efeito;
    }
}
