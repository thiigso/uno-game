
package application;

import controller.Controller;

/**
 * @author André Lopes Alves Pereira Machado
 * @author Augusto de Morais Lemos
 * @author Daniel Santos Carmo
 * @author Thiago Gabriel da Silva Oliveira 
 */

public class App {
    
    public static void main(String[] args) {
        
        System.out.println("[System] Iniciando o Uno");

        Controller gerenciadorJogo = new Controller();

        //gerenciadorJogo.mostrarBaralho();
        gerenciadorJogo.iniciarPartida();

        //gerenciadorJogo.mostrarBaralho();

        gerenciadorJogo.mostrarMao("Jogador 1");
        gerenciadorJogo.mostrarMao("Bot 1");
        gerenciadorJogo.mostrarMao("Bot 2");
        gerenciadorJogo.mostrarMao("Bot 3");

        //gerenciadorJogo.mostrarBaralho();
        
        
    }
    
}
