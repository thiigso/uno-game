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
        System.out.println("[CONTROLLER] (realizarJogada) iniciando...");
        if(mesa.verificarJogada(cartaSelecionada)){
            System.out.println("[CONTROLLER] (realizarJogada) jogada verificada...");

            jogador.jogarCarta(cartaSelecionada);

            mesa.getPilhaDescarte().add(cartaSelecionada);
            mesa.setCartaAtual(cartaSelecionada);
            
            if(cartaSelecionada instanceof CartaEspecial cartaEspecial){

                switch(cartaEspecial.getEfeito()){
                    case "Mais2" -> mesa.setPenalidadeCompra(mesa.getPenalidadeCompra() + 2);
                    case "Mais4" -> {
                        mesa.setPenalidadeCompra(mesa.getPenalidadeCompra() + 4);
                        String novaCor;
                        if(jogador instanceof JogadorBot bot){
                        //aq o bot escolhe a melhor cor usando a lógica dele
                        novaCor = bot.escolherMelhorCor(); 
                        }else{
                        //para o humano (EU NÃO SEI FAZER), por isso por enquanto:
                        novaCor = "Azul"; // vc pode trocar por uma lógica de input se quiser
                        System.out.println("[CONTROLLER] Jogador humano escolheu Azul."); //é só um mockup por enquanto
                        }           
                        mesa.getCartaAtual().setCor(novaCor);
                        break;
                        
                    case "Bloquear":
                        System.out.println("[EFEITO] Pula a vez do proximo!");
                        mesa.avancarTurno();
                    }
                    case "MudaSentido" -> {
                        System.out.println("[EFEITO] Inverte o sentido do jogo!");
                        mesa.inverteSentido();
                        break;
                        
                    case "MudarCor":
                        if(jogador instanceof JogadorBot bot){
                        //aq o bot escolhe a melhor cor usando a lógica dele
                        novaCor = bot.escolherMelhorCor(); 
                        }else{
                        //para o humano (EU NÃO SEI FAZER), por isso por enquanto:
                        novaCor = "Azul"; // vc pode trocar por uma lógica de input se quiser
                        System.out.println("[CONTROLLER] Jogador humano escolheu Azul."); //é só um mockup por enquanto
                        }           
                        mesa.getCartaAtual().setCor(novaCor);
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
    System.out.println("[CONTROLLER] (gerenciarTurno) É a vez do jogador: " + jogadorAtual.getNome());

    if (jogadorAtual instanceof JogadorBot jogadorBot){
        // Lógica para o bot jogar
        System.out.println("[CONTROLLER] (gerenciarTurno) O jogador " + jogadorBot.getNome() + " é um bot e está jogando automaticamente.");

        Carta cartaEscolhidaBot = jogadorBot.escolherCartaParaJogar(mesa);
        System.out.println("[CONTROLLER] (gerenciarTurno) O bot " + jogadorBot.getNome() + " escolheu a carta: " + (cartaEscolhidaBot != null ? cartaEscolhidaBot.getCor() + " " + cartaEscolhidaBot.getTipo() + "" + ((CartaEspecial) cartaEscolhidaBot).getEfeito() : "Nenhuma carta válida"));

        if(cartaEscolhidaBot != null){
            realizarJogada(jogadorAtual, cartaEscolhidaBot);
        }else{
            if(mesa.getPenalidadeCompra() > 0){
                aplicarPenalidadeCompra(jogadorAtual);
            }else{
                System.out.println("[CONTROLLER] O jogador " + jogadorAtual.getNome() + " não tem cartas válidas e deve comprar uma carta.");
                Carta cartaComprada = mesa.getBaralho().comprar();

                if(cartaComprada != null){
                    jogadorAtual.receberCarta(cartaComprada);
                    System.out.println("[CONTROLLER] (gerenciarTurno) O jogador " + jogadorAtual.getNome() + " comprou: " + cartaComprada.getCor() + " " + cartaComprada.getTipo());
                }else{
                    System.out.println("[CONTROLLER] (gerenciarTurno) O baralho está vazio.");
                }
                System.out.println("[CONTROLLER] (gerenciarTurno) O jogador " + jogadorAtual.getNome() + " não tem cartas válidas e não há penalidade, então o turno passa para o próximo jogador.");
                mesa.avancarTurno();
                gerenciarTurno(); // Chama o próximo turno

            }
        }
    }else{
        // Lógica para o jogador humano jogar
        System.out.println("[CONTROLLER] O jogador " + jogadorAtual.getNome() + " é humano e deve jogar manualmente.");
    }
}

    public void processarCliqueInterface(int indiceDaCartaNaMao){
        // Descobre quem é o jogador da vez
        Jogador jogadorDaVez = mesa.getJogadores().get(mesa.getTurnoAtual());
        System.out.println("[CONTROLLER] (processarCliqueInterface) Clique recebido na interface para a carta de índice: " + indiceDaCartaNaMao);

        // Só aceita o clique se realmente for a vez do humano
        if (jogadorDaVez instanceof JogadorHumano) {
            System.out.println("[CONTROLLER] (processarCliqueInterface) Jogador Humano ");

            // Pega a carta específica da mão baseada no índice do botão
            if (indiceDaCartaNaMao >= 0 && indiceDaCartaNaMao < jogadorDaVez.getMao().size()) {
                Carta cartaClicada = jogadorDaVez.getMao().get(indiceDaCartaNaMao);

                // chama o metodo de realizar jogada
                realizarJogada(jogadorDaVez, cartaClicada);
            }
        } else {
            // Ignora o clique silenciosamente (ou imprime no log para debug)
            System.out.println("[CONTROLLER] (processarCliqueInterface) Clique ignorado: turno atual é do Bot.");
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

    public void aplicarPenalidadeCompra(Jogador jogadorAzarado){
        System.out.println("[CONTROLLER] (aplicarPenalidadeCompra) O jogador " + jogadorAzarado.getNome() + " tem uma penalidade de compra ativa de " + mesa.getPenalidadeCompra() + " cartas.");
        int cartasParaComprar = mesa.getPenalidadeCompra();
        
        if(mesa.getPenalidadeCompra() > 0){
            System.out.println("[CONTROLLER] (aplicarPenalidadeCompra) " + jogadorAzarado.getNome() + " não tem defesa e vai comer " 
                    + cartasParaComprar + " cartas!");
            for(int i = 0; i < cartasParaComprar; i++){
                Carta cartaComprada = mesa.getBaralho().comprar();
                if(cartaComprada != null){
                    jogadorAzarado.receberCarta(cartaComprada);
                    System.out.println("[CONTROLLER] (aplicarPenalidadeCompra) " + jogadorAzarado.getNome() + " comprou: " + cartaComprada.getCor() + " " + cartaComprada.getTipo());
                }
            }
            mesa.setPenalidadeCompra(0);//agr q alguem teve q comer reseta a contagem
            mesa.avancarTurno();//pula o turno de quem teve q comer
            gerenciarTurno(); // Chama o próximo turno
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
