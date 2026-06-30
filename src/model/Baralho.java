package model;
import java.util.ArrayList;
import java.util.Collections;

public class Baralho {

    ArrayList<Carta> cartas;
    
    public Baralho(){
        
        cartas = new ArrayList<>();
        
        //Um baralho UNO tem 112 cartas
        for(int repete=0 ; repete<2 ; repete++){
            String[] cores = {"Amarelo", "Azul", "Verde", "Vermelho"};
        
            // adidionando as cartas numeradas 0
            if(repete==0){
                for(int i=0 ; i<4 ; i++){
                    cartas.add(new CartaNumerada(cores[i], "Numerada",0));
                }
            }

            // adidionando as cartas numeradas 1 a 9
            for(int i=1 ; i<10 ; i++){
                for(int j=0 ; j<4 ; j++){
                    cartas.add(new CartaNumerada(cores[j], "Numerada",i));
                }
            }       
        
            // adidionando as cartas especiais
            for(int i=0 ; i<4 ; i++){
                cartas.add(new CartaEspecial(cores[i],"Especial", "Bloquear"));
                cartas.add(new CartaEspecial(cores[i],"Especial", "MudaSentido")); 
                cartas.add(new CartaEspecial(cores[i],"Especial", "Mais2"));
            }
        
            // adidionando as cartas especiais pretas
            cartas.add(new CartaEspecial("Preto","Especial", "MudarCor"));
            cartas.add(new CartaEspecial("Preto","Especial", "MudarCor"));
            cartas.add(new CartaEspecial("Preto","Especial", "Mais4"));
            cartas.add(new CartaEspecial("Preto","Especial", "Mais4"));
            //criou 56 cartas aí repete 1 vez
        }


    }
    
    public void embaralhar(){
        Collections.shuffle(cartas);
    }
    
    public Carta comprar(){

        //pega uma carta aleatoria no ArrayList
        if(!cartas.isEmpty()){
            return cartas.remove(0);
        }else{
            return null;
        }  
    }



    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

}
