package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
//tava pensando em algo como um arquivo texto registrando o histórico de partidas, tipo assim:
//[03/07/2026 20:30] Vencedor: Nome do Jogador | Oponentes: Bot 1, Bot 2 | Rodadas: 24
public class ManipulaArquivoTexto {
    private String nomeArquivo;
    private Formatter gravador;
    private Scanner leitor;
    
    public ManipulaArquivoTexto(String nomeArquivo){
        this.nomeArquivo = nomeArquivo;
    }
    
    private void abrirArquivoParaGravacao(){
        try {
            this.gravador = new Formatter(new FileWriter(nomeArquivo, true)); //vai escrever como append assim não vai
        } catch (IOException ex) {                                           //ter problemas de apagar o arquivo anterior
            System.err.println("Nao foi possivel criar o arquivo");
            ex.printStackTrace();
        }        
    }
    
    public void gravarArquivo(ArrayList<String> textos){
        abrirArquivoParaGravacao();
        if (gravador != null){
            for(String linha: textos){
                gravador.format("%s\n", linha);
            }
            gravador.flush();
        }
        fecharArquivoGravacao();
    }
    
    private void fecharArquivoGravacao(){
        if (gravador != null){
            gravador.close();
        }
    }
    
    private void abrirArquivoLeitura(){
        try {
            leitor = new Scanner(new File(nomeArquivo));
        } catch (IOException ex) {
            System.err.println("O arquivo nao pode ser aberto para leitura.");
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }       
    }
    
    public ArrayList<String> lerArquivo(){
        abrirArquivoLeitura();
        ArrayList<String> retorno = new ArrayList<>();
        if (leitor != null){
            while(leitor.hasNextLine()){
                retorno.add(leitor.nextLine());
            }
        }
        fecharArquivoLeitura();
        return retorno;
    }
    
    private void fecharArquivoLeitura(){
        if (leitor != null){
            leitor.close();
        }
    }           
}