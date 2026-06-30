
package model;

import java.util.ArrayList;

public abstract class Jogador {
    private String nome;
    private ArrayList<Carta> mao;

    public Jogador(String nome){
        this.nome = nome;
        this.mao = new ArrayList<>();
    }


    public void receberCarta(Carta carta){
        mao.add(carta);
    }

    public void jogarCarta(Carta carta){
        mao.remove(carta);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Carta> getMao() {
        return mao;
    }

    public void setMao(ArrayList<Carta> mao) {
        this.mao = mao;
    }


}
