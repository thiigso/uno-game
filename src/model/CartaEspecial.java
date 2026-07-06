package model;


public class CartaEspecial extends Carta{
    private String efeito;
    
    public CartaEspecial(String cor, String tipo, String efeito){
        super(cor, tipo);
        this.efeito = efeito;
    }
    
    public String aplicarEfeito(){
        //O método de aplicarEfeito ainda depende de algumas coisas mais fundamentais que faltam no código
        //como alguma forma de saber quem fez a jogada, qual o próximo jogador e talz, então por enquanto
        //o método ainda não foi implementado
        return null;
        
    }
    
    public String getEfeito() {
        return efeito;
    }
}
