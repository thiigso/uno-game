package model;

import java.util.ArrayList;

import view.Janela;

public class Mesa {

    private Baralho baralho;
    private int turnoAtual;
    private ArrayList<Jogador> jogadores;
    private ArrayList<Carta> pilhaDescarte;
    private boolean sentidoHorario;

    public Mesa() {
        this.baralho = new Baralho();
        this.turnoAtual = 0;
        this.jogadores = new ArrayList<>();
        this.pilhaDescarte = new ArrayList<>();
        this.sentidoHorario = true;

    }

    public void prepararMesa(){
        // Adiciona jogadores à mesa
        jogadores.add(new JogadorHumano("Jogador 1"));
        jogadores.add(new JogadorBot("Bot 1"));
        jogadores.add(new JogadorBot("Bot 2"));
        jogadores.add(new JogadorBot("Bot 3"));

        // Embaralha o baralho
        baralho.embaralhar();

        // Distribui cartas para os jogadores
        for (int i = 0; i < 7; i++) {
            for (Jogador jogador : jogadores) {
                Carta carta = baralho.comprar();
                if (carta != null) {
                    jogador.receberCarta(carta);
                    System.out.println("[BARALHO] Jogador: "+ jogador.getNome() + " Carta comprada: " + carta.getCor() + " " + carta.getTipo());
                }
            }
        }

        // Coloca a primeira carta na pilha de descarte
        Carta primeiraCarta = baralho.comprar();
        if (primeiraCarta != null) {
            pilhaDescarte.add(primeiraCarta);
        }
    }


    public Baralho getBaralho() {
        return baralho;
    }


    public void setBaralho(Baralho baralho) {
        this.baralho = baralho;
    }


    public Janela getJanela() {
        return janela;
    }


    public void setJanela(Janela janela) {
        this.janela = janela;
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

    public void setSentidoHorario(boolean sentidoHorario) {
        this.sentidoHorario = sentidoHorario;
    }

    

}
