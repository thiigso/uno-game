package model;

public class JogadorBot extends Jogador {
    
    public JogadorBot(String nome){
        super(nome);
    }
    
    public Carta escolherCartaParaJogar(Mesa mesa) {
        //o bot testa carta por carta da mão dele
        for(Carta carta : getMao()){
            //aq ele pergunta para a mesa: "Posso jogar esta carta?"
            if(mesa.verificarJogada(carta)){
                return carta; // Se a mesa aprovar, ele joga!
            }
        }
        return null; // Se a mesa rejeitar todas, ele desiste e retorna null
    }


}
