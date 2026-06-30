package controller;

import model.Mesa;
import view.Janela;

public class Controller {

    private Janela janela;
    private Mesa mesa;

    public Controller() {
        this.janela = new Janela();
        this.mesa = new Mesa();
        
    }


    public void iniciarPartida() {
            System.out.println("[CONTROLLER] Preparando a mesa e embaralhando...");
            
            mesa.prepararMesa(); // Pede para a model se arrumar
            
            System.out.println("[CONTROLLER] Baralho pronto! Abrindo a interface gráfica...");
            
            // Forma segura e correta de abrir a Janela
            java.awt.EventQueue.invokeLater(() -> {
                janela.setVisible(true);
            });
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
