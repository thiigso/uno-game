package model;

public class JogadorBot extends Jogador {
    
    public JogadorBot(String nome){
        super(nome);
    }
    


    public Carta escolherCartaParaJogar(Carta cartaAtual){

        for(Carta carta : getMao()){
            if(carta.getCor().equals(cartaAtual.getCor()) || carta.getTipo().equals(cartaAtual.getTipo())){
                return carta;
            }
        }
        return null; // Retorna null se não houver carta válida para jogar

    }


}
