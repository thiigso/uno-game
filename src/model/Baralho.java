package model;
import java.util.ArrayList;


public class Baralho {
    ArrayList<Carta> cartas;
    
    public Baralho(){
        cartas = new ArrayList<>();
        
        //Um baralho UNO tem 112 cartas
        for(int repete=1 ; repete<2 ; repete++){
            String[] cores = {"Amarelo", "Azul", "Verde", "Vermelho"};
        
            for(int i=1 ; i<10 ; i++){
                for(int j=0 ; j<4 ; j++){
                    cartas.add(new CartaNumerada(cores[j], "Numerada",i));
                }
            }       
        
            for(int i=0 ; i<4 ; i++){
                cartas.add(new CartaEspecial(cores[i], "Bloquear"));
                cartas.add(new CartaEspecial(cores[i], "Mais2"));
                cartas.add(new CartaEspecial(cores[i], "MudaSentido")); 
            }
        
            cartas.add(new CartaEspecial("Preto", "MudarCor"));
            cartas.add(new CartaEspecial("Preto", "MudarCor"));
            cartas.add(new CartaEspecial("Preto", "Mais4"));
            cartas.add(new CartaEspecial("Preto", "Mais4"));
            //criou 56 cartas aí repete 1 vez
        }
    }
    
    public void embaralhar(){
        
    }
    
    public Carta comprar(){
        Carta cartaAleatoria;
        
        //pega uma carta aleatoria no ArrayList
        
        return cartaAleatoria;        
    }
}
