package model;

import java.util.ArrayList;

public class Mesa {

    private Baralho baralho;
    private int turnoAtual;
    private String corAtual;
    private int penalidadeCompra; //se alguém tiver um nome melhor pra isso, é basicamente jogar come 2 em cima de outro
    private ArrayList<Jogador> jogadores;
    private ArrayList<Carta> pilhaDescarte; //ultima carta dessa lista é a carta do topo da pilha na mesa
    private boolean sentidoHorario;

    public Mesa() {
        this.baralho = new Baralho();
        this.turnoAtual = 0;
        this.corAtual = "NAO INICIADA"; //ainda n comecou o jogo, quando for iniciar q vai pegar alguma carta da pilha
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

        // Distribui cartas para os jogadores
        distribuiCartasAosJogadores();

        // Coloca a primeira carta na pilha de descarte
        pegarPrimeiraCartaPraIniciar();
        
        // Após devolver as cartas que eram invalidas para o baralho precisamos embaralhar novamente
        baralho.embaralhar();       
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
        do{
            Carta primeiraCarta = baralho.comprar();
            if(primeiraCarta instanceof CartaNumerada){
                cartaValidaPraInicio = true;
                this.pilhaDescarte.add(primeiraCarta);
                this.corAtual = primeiraCarta.getCor();
            }else{
                cartasInvalidasParaInicio.add(primeiraCarta);
            }
        }while(!cartaValidaPraInicio);
        
        //agr que já foi encontrada uma carta válida pra começar temos que devolver as invalidas ao baralho
        while(!cartasInvalidasParaInicio.isEmpty()){
            this.baralho.adicionarCarta(cartasInvalidasParaInicio.getFirst());
            cartasInvalidasParaInicio.removeFirst();
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

    public Carta getCartaDoTopo(){
        if(this.pilhaDescarte.isEmpty()){
            return null;
        }
        return this.pilhaDescarte.get(pilhaDescarte.size() - 1);
    }

}
