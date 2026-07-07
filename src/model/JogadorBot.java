package model;

public class JogadorBot extends Jogador {
    
    public JogadorBot(String nome){
        super(nome);
    }
    
    public Carta escolherCartaParaJogar(Mesa mesa){
        Carta cartaAtual = mesa.getCartaAtual();

        //1. Prioridade: Carta Especial (Bloquear, +2, etc) que combina com a cor/tipo
        for (Carta c : getMao()){
            if (c instanceof CartaEspecial){
                if (c.getCor().equals(cartaAtual.getCor()) || c.getTipo().equals(cartaAtual.getTipo())){
                    return c;
                }
            }
        }

        //2. Prioridade: Carta Numerada que combina
        for (Carta c : getMao()){
            if (c.getCor().equals(cartaAtual.getCor()) || c.getTipo().equals(cartaAtual.getTipo())){
                return c;
            }
        }

        //3. Caso não tenha nada que combine, tenta jogar uma carta "Preta" (+4 ou MudaCor)
        for (Carta c : getMao()){
            if (c.getCor().equals("Preto")){
                return c;
            }
        }

        return null; // Se deu mal vai ter que comprarkk
    }
    
    public String escolherMelhorCor(){
        int[] contagem = new int[4]; // 0: Amarelo, 1: Azul, 2: Verde, 3: Vermelho
        String[] cores = {"Amarelo", "Azul", "Verde", "Vermelho"};

        for (Carta c : getMao()){
            for(int i = 0; i < cores.length; i++){
                if(c.getCor().equals(cores[i])){
                    contagem[i]++;
                }
            }
        }

        // daq ele acha qual o indice da melhor cor pra ele e escolge ela
        int indiceDaMelhor = 0;
        for(int i = 1; i < contagem.length; i++){
            if(contagem[i] > contagem[indiceDaMelhor]){
                indiceDaMelhor = i;
            }
        }
        return cores[indiceDaMelhor];
    }
}
