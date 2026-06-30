package model;


public class CartaEspecial extends Carta
                                implements EfeitoEspecial{
    private int efeito;
    
    public CartaEspecial(String cor, String tipo, int efeito){
        super(cor, tipo);
        this.efeito = efeito;
    }
    @Override
    public String aplicarEfeito(){
        
    }
    
}
