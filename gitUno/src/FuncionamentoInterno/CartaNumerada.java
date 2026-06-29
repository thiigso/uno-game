
package FuncionamentoInterno;

public class CartaNumerada extends Carta{
    private int numero;
    
    public CartaNumerada(String cor, String tipo, int numero){
        super(cor, tipo);
        this.numero = numero;
    }
    
    @Override
    public String aplicarEfeito(){
        
    }
    
}
