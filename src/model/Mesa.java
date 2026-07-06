package model;

import java.util.ArrayList;



public class Mesa {

    private Baralho baralho;
    private int turnoAtual;
    private Carta cartaAtual;
    private int penalidadeCompra; //se alguém tiver um nome melhor pra isso, é basicamente jogar come 2 em cima de outro
    private ArrayList<Jogador> jogadores;
    private ArrayList<Carta> pilhaDescarte; //ultima carta dessa lista é a carta do topo da pilha na mesa
    private boolean sentidoHorario;


    public Mesa() {
        this.baralho = new Baralho();
        this.turnoAtual = 0;
        this.cartaAtual = null;
        this.penalidadeCompra = 0;
        this.jogadores = new ArrayList<>(); //que não seja uma carta especial para ter uma cor e número inicial
        this.pilhaDescarte = new ArrayList<>();
        this.sentidoHorario = true;
    }

    public void prepararMesa(){
        
        // Adiciona jogadores à mesa
        instanciarJogadores();

        // Embaralha o baralho
        baralho.embaralhar();

        //mostrarBaralhoMesa();

        // Distribui cartas para os jogadores
        distribuiCartasAosJogadores();

        //mostrarBaralhoMesa();

        // Coloca a primeira carta na pilha de descarte
        pegarPrimeiraCartaPraIniciar();

        mostrarPilhaDescarte();
        mostrarBaralhoMesa();
        
        // Após devolver as cartas que eram invalidas para o baralho precisamos embaralhar novamente
        //baralho.embaralhar();    
        // Eu acho que aqui não precisa embaralhar novamente, se pensarmos no jogo real as cartas invalidas só são devolvidas pro final e o jogo começa
    }


    public void avancarTurno(){
        if(sentidoHorario){
            this.turnoAtual = (turnoAtual + 1) % jogadores.size();
        }else{
            this.turnoAtual = (turnoAtual - 1 + jogadores.size()) % jogadores.size();
        }
    }

    public boolean verificarJogada(Carta cartaJogada){
        
        if(cartaJogada.getCor().equals(cartaAtual.getCor()) || cartaJogada.getTipo().equals(cartaAtual.getTipo())){
                System.out.println("[MESA] Jogada válida! Carta jogada: " + cartaJogada.getCor() + " " + cartaJogada.getTipo());
                return true;
            } else {
                System.out.println("[MESA] Jogada inválida! Carta jogada: " + cartaJogada.getCor() + " " + cartaJogada.getTipo());
                return false;
            }
    }

    private void instanciarJogadores(){
        jogadores.add(new JogadorHumano("Jogador 1"));
        jogadores.add(new JogadorBot("Bot 1"));
        jogadores.add(new JogadorBot("Bot 2"));
        jogadores.add(new JogadorBot("Bot 3"));
    }
    
    private void distribuiCartasAosJogadores(){
        for (int i = 0; i < 7; i++) {
            for (Jogador jogador : jogadores) {
                Carta carta = baralho.comprar();
                if (carta != null) {
                    jogador.receberCarta(carta);
                    System.out.println("[BARALHO] Jogador: "+ jogador.getNome() + " Carta comprada: " + carta.getCor() + " " + carta.getTipo());
                }
            }
        }
    }
    
    private void pegarPrimeiraCartaPraIniciar(){         
        //Cartas especiais não podem iniciar a partida 
        //por isso são armazenadas temporariamente e devolvidas ao baralho
        boolean cartaValidaPraInicio = false;
        ArrayList<Carta> cartasInvalidasParaInicio = new ArrayList<>(); 
        buscarCarta:
        
        do{
            Carta primeiraCarta = baralho.comprar();
            switch (primeiraCarta) {
                case null -> {
                    System.out.println("[MESA] Baralho vazio ao tentar iniciar partida.");
                    break buscarCarta;
                }
                case CartaNumerada cartaNumerada -> {
                    cartaValidaPraInicio = true;
                    this.cartaAtual = primeiraCarta;
                    this.pilhaDescarte.add(primeiraCarta);
                    System.out.println("[MESA] Primeira carta da partida: " + cartaAtual.getCor() + " " + cartaAtual.getTipo() + " " + cartaNumerada.getNumero());
                }
                case CartaEspecial cartaEspecial -> {
                    System.out.println("[MESA] Encontrou carta invalida!!: " + primeiraCarta.getCor() + " " + primeiraCarta.getTipo() + " " + cartaEspecial.getEfeito());
                    cartasInvalidasParaInicio.add(primeiraCarta);
                }
                default -> {
                    System.out.println("[MESA] Carta desconhecida encontrada no início: " + primeiraCarta.getCor() + " " + primeiraCarta.getTipo());
                    cartasInvalidasParaInicio.add(primeiraCarta);
                }
            }
        }while(!cartaValidaPraInicio);
        
        //Agora que já foi encontrada uma carta válida pra começar temos que devolver as invalidas ao baralho
        while(!cartasInvalidasParaInicio.isEmpty()){
            this.baralho.adicionarCarta(cartasInvalidasParaInicio.get(0));
            cartasInvalidasParaInicio.remove(0);
        }
    }


    public void mostrarBaralhoMesa() {
        System.out.println("[MESA] Baralho:");
        for(int i = baralho.getCartas().size() - 1; i >= 0; i--) {
            switch (baralho.getCartas().get(i)) {
                case model.CartaNumerada cartaNumerada -> System.out.println("Carta "+ (i+1) + " " + cartaNumerada.getCor() + " " + cartaNumerada.getTipo() + " " + cartaNumerada.getNumero());
                case model.CartaEspecial cartaEspecial -> System.out.println("Carta "+ (i+1) + " " + cartaEspecial.getCor() + " " + cartaEspecial.getTipo() +" " + cartaEspecial.getEfeito());
                default -> {
                }
            }
        }
    }

    public void mostrarPilhaDescarte() {
        System.out.println("[MESA] Pilha Descarte:");
        for(int i = pilhaDescarte.size() - 1; i >= 0; i--) {
            switch (pilhaDescarte.get(i)) {
                case model.CartaNumerada cartaNumerada -> System.out.println("Carta "+ (i+1) + " " + cartaNumerada.getCor() + " " + cartaNumerada.getTipo() + " " + cartaNumerada.getNumero());
                case model.CartaEspecial cartaEspecial -> System.out.println("Carta "+ (i+1) + " " + cartaEspecial.getCor() + " " + cartaEspecial.getTipo() +" " + cartaEspecial.getEfeito());
                default -> {
                }
            }
        }
    }

    
    public Baralho getBaralho() {
        return baralho;
    }


    public void setBaralho(Baralho baralho) {
        this.baralho = baralho;
    }

    public int getTurnoAtual() {
        return turnoAtual;
    }

    public void setTurnoAtual(int turnoAtual) {
        this.turnoAtual = turnoAtual;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public ArrayList<Carta> getPilhaDescarte() {
        return pilhaDescarte;
    }

    public void setPilhaDescarte(ArrayList<Carta> pilhaDescarte) {
        this.pilhaDescarte = pilhaDescarte;
    }

    public boolean isSentidoHorario() {
        return sentidoHorario;
    }

    public void setSentidoHorario() {
        this.sentidoHorario = !(this.sentidoHorario);
    }

    public Carta getCartaAtual() {
        return cartaAtual;
    }

    public void setCartaAtual(Carta cartaAtual) {
        this.cartaAtual = cartaAtual;
    }

    public int getPenalidadeCompra() {
        return penalidadeCompra;
    }

    public void setPenalidadeCompra(int penalidadeCompra) {
        this.penalidadeCompra = penalidadeCompra;
    }

    public void setSentidoHorario(boolean sentidoHorario) {
        this.sentidoHorario = sentidoHorario;
    }

    
    

}
