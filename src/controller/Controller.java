package controller;

import model.Carta;
import model.CartaEspecial;
import model.Jogador;
import model.JogadorBot;
import model.JogadorHumano;
import model.Mesa;
import view.Janela;

public class Controller {

    private Janela janela;
    private Mesa mesa;

    public Controller() {
        this.janela = new Janela();
        this.mesa = new Mesa();
        this.janela.setController(this);
        
    }


    public void iniciarPartida() {
            System.out.println("[CONTROLLER] Preparando a mesa e embaralhando...");
            
            mesa.prepararMesa(); // Pede para a model se arrumar
            
            System.out.println("[CONTROLLER] Baralho pronto! Abrindo a interface gráfica...");
            
            Jogador humano = mesa.getJogadores().get(0);
            Carta cartaAtualController = mesa.getCartaAtual();

            // Forma segura e correta de abrir a Janela
            java.awt.EventQueue.invokeLater(() -> {
                        janela.inicializarBotoes(humano.getMao()); // Desenha as 7 cartas do humano
                        janela.atualizarPilha(cartaAtualController); // Desenha a carta inicial da mesa
                        janela.setVisible(true); // Mostra a tela
            });

            gerenciarTurno(); // Inicia o loop de turnos
    }


    public void realizarJogada(Jogador jogador, Carta cartaSelecionada){
        if(mesa.verificarJogada(cartaSelecionada)){

            jogador.jogarCarta(cartaSelecionada);

            mesa.getPilhaDescarte().add(cartaSelecionada);
            mesa.setCartaAtual(cartaSelecionada);

            if ((cartaSelecionada instanceof CartaEspecial cartaEspecial) && (mesa.getPenalidadeCompra()==0)){

                switch (cartaEspecial.getEfeito()){

                case "Mais2":
                    mesa.setPenalidadeCompra(2);
                break;

                case "Mais4":
                    mesa.setPenalidadeCompra(4);
                break;
                }
            }

            janela.atualizarPilha(cartaSelecionada);
            if (jogador instanceof JogadorHumano) {
                janela.inicializarBotoes(jogador.getMao());
            }

            if(jogador.getMao().isEmpty()){
                System.out.println("[CONTROLLER] O jogador " + jogador.getNome() + " venceu a partida!");
                return;
            }

            mesa.avancarTurno();
            gerenciarTurno(); // Chama o próximo turno

        } else {
            System.out.println("[CONTROLLER] Jogada inválida! Carta jogada: " + cartaSelecionada.getCor() + " " + cartaSelecionada.getTipo());
        }
    }


    public void gerenciarTurno(){
        Jogador jogadorAtual = mesa.getJogadores().get(mesa.getTurnoAtual());
        System.out.println("[CONTROLLER] É a vez do jogador: " + jogadorAtual.getNome());

        if (jogadorAtual instanceof JogadorBot jogadorBot) {
            // Lógica para o bot jogar
            System.out.println("[CONTROLLER] O jogador " + jogadorBot.getNome() + " é um bot e está jogando automaticamente.");

            Carta cartaEscolhidaBot = jogadorBot.escolherCartaParaJogar(mesa.getCartaAtual());
            
            if(cartaEscolhidaBot != null){
                realizarJogada(jogadorAtual, cartaEscolhidaBot);
            } else {
                System.out.println("[CONTROLLER] O jogador " + jogadorAtual.getNome() + " não tem cartas válidas para jogar e deve comprar uma carta.");
                Carta cartaComprada = mesa.getBaralho().comprar();
                if(cartaComprada != null){
                    jogadorAtual.receberCarta(cartaComprada);
                    System.out.println("[CONTROLLER] O jogador " + jogadorAtual.getNome() + " comprou a carta: " + cartaComprada.getCor() + " " + cartaComprada.getTipo());

                    mesa.avancarTurno();
                    gerenciarTurno();

                } else {
                    System.out.println("[CONTROLLER] O baralho está vazio. O jogador " + jogadorAtual.getNome() + " não pode comprar cartas.");
                }
            }

        } else {
            // Lógica para o jogador humano jogar
            System.out.println("[CONTROLLER] O jogador " + jogadorAtual.getNome() + " é humano e deve jogar manualmente.");
        }

    }

    public void processarCliqueInterface(int indiceDaCartaNaMao) {
        // Descobre quem é o jogador da vez
        Jogador jogadorDaVez = mesa.getJogadores().get(mesa.getTurnoAtual());

        // Só aceita o clique se realmente for a vez do humano
        if (jogadorDaVez instanceof JogadorHumano) {

            // Pega a carta específica da mão baseada no índice do botão
            if (indiceDaCartaNaMao >= 0 && indiceDaCartaNaMao < jogadorDaVez.getMao().size()) {
                Carta cartaClicada = jogadorDaVez.getMao().get(indiceDaCartaNaMao);

                // chama o metodo de realizar jogada
                realizarJogada(jogadorDaVez, cartaClicada);
            }
        } else {
            // Ignora o clique silenciosamente (ou imprime no log para debug)
            System.out.println("[CONTROLLER] Clique ignorado: turno atual é do Bot.");
        }
    }


    
    public void mostrarBaralho() {
        System.out.println("[CONTROLLER] Baralho:");
        for(int i = mesa.getBaralho().getCartas().size() - 1; i >= 0; i--) {
            switch (mesa.getBaralho().getCartas().get(i)) {
                case model.CartaNumerada cartaNumerada -> System.out.println("Carta "+ (i+1) + " " + mesa.getBaralho().getCartas().get(i).getCor() + " " + mesa.getBaralho().getCartas().get(i).getTipo() + " " + cartaNumerada.getNumero());
                case model.CartaEspecial cartaEspecial -> System.out.println("Carta "+ (i+1) + " " + mesa.getBaralho().getCartas().get(i).getCor() + " " + mesa.getBaralho().getCartas().get(i).getTipo() +" " + cartaEspecial.getEfeito());
                default -> {
                }
            }
        }
    }

    public void mostrarMao(String nomeJogador) {
        for(int i = 0; i < mesa.getJogadores().size(); i++) {
            if(mesa.getJogadores().get(i).getNome().equals(nomeJogador)) {
                System.out.println("[CONTROLLER] Mão do jogador " + nomeJogador + ":");
                for(int j = 0; j < mesa.getJogadores().get(i).getMao().size(); j++) {
                    switch (mesa.getJogadores().get(i).getMao().get(j)) {
                        case model.CartaNumerada cartaNumerada -> System.out.println("Carta "+ j + " " + mesa.getJogadores().get(i).getMao().get(j).getCor() + " " + mesa.getJogadores().get(i).getMao().get(j).getTipo() + " " + cartaNumerada.getNumero());
                        case model.CartaEspecial cartaEspecial -> System.out.println("Carta "+ j + " " + mesa.getJogadores().get(i).getMao().get(j).getCor() + " " + mesa.getJogadores().get(i).getMao().get(j).getTipo() +" " + cartaEspecial.getEfeito());
                        default -> {
                        }
                    }
                }
            }
        }

    }


    public Janela getJanela() {
        return janela;
    }


    public void setJanela(Janela janela) {
        this.janela = janela;
    }


    public Mesa getMesa() {
        return mesa;
    }


    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
    
}
