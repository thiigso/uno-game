
package application;

import controller.Controller;
/**
 *
 * @author Thiago Gabriel da Silva Oliveira
 * @author Coloquem os nomes aqui!
 */

public class App {
    
    public static void main(String[] args) {
        System.out.println("[System] Iniciando o Uno");
        Controller gerenciadorJogo = new Controller();
        
        gerenciadorJogo.mostrarBaralho();
        
        gerenciadorJogo.iniciarPartida();

        gerenciadorJogo.mostrarBaralho();

        gerenciadorJogo.mostrarMao("Jogador 1");
        gerenciadorJogo.mostrarMao("bot 1");
        gerenciadorJogo.mostrarMao("bot 2");
        gerenciadorJogo.mostrarMao("bot 3");

        gerenciadorJogo.mostrarBaralho();
        
        
        
    }
    
}
