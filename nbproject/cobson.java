//cobson WABAG
//vantabrap by branigger embedded vantablack brimcoal 'p below DO NOT INTERACT
//nada pra ver aq

package cartas;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

import java.util.Scanner;

public class cobson {
     public static void main(String[] args) {

        // =========================
        // ARRAYLIST
        // =========================

        ArrayList<String> alunos = new ArrayList<>();

        // add (inserir)
        alunos.add("Ana");
        alunos.add("Bruno");
        alunos.add("Carlos");

        // add em posição específica
        alunos.add(1, "Daniel");

        // tamanho
        System.out.println("Tamanho: " + alunos.size());

        // acessar elemento
        System.out.println("Primeiro: " + alunos.get(0));

        // alterar elemento
        alunos.set(2, "Beatriz");

        // remover por índice
        alunos.remove(1);

        // verificar se contém
        System.out.println("Tem Ana? " + alunos.contains("Ana"));

        // percorrer lista
        for (String aluno : alunos) {
            System.out.println("Aluno: " + aluno);
        }

        // =========================
        // HASHMAP
        // =========================

        HashMap<Integer, String> mapa = new HashMap<>();

        // put (adicionar)
        mapa.put(1, "Java");
        mapa.put(2, "Python");
        mapa.put(3, "C++");

        // get (buscar)
        System.out.println("Chave 2: " + mapa.get(2));

        // remover
        mapa.remove(3);

        // verificar chave
        System.out.println("Tem chave 1? " + mapa.containsKey(1));

        // verificar valor
        System.out.println("Tem Python? " + mapa.containsValue("Python"));

        // tamanho
        System.out.println("Tamanho mapa: " + mapa.size());

        // percorrer HashMap
        for (Integer chave : mapa.keySet()) {
            System.out.println("Chave: " + chave +
                               " Valor: " + mapa.get(chave));
        }
    }
     

public class ArquivoTexto {

    public static void main(String[] args) {

        File arquivo = new File("dados.txt");

        // ESCRITA
        try {
            Formatter escritor = new Formatter(arquivo);

            escritor.format("João %d\n", 20);
            escritor.format("Maria %d\n", 25);

            escritor.close(); // fecha o arquivo

            System.out.println("Arquivo escrito com sucesso!");

        } catch (FileNotFoundException e) {
            System.out.println("Erro ao criar/escrever o arquivo.");
        }

        // LEITURA
        try {
            Scanner leitor = new Scanner(arquivo);

            System.out.println("\nConteúdo do arquivo:");

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                System.out.println(linha);
            }

            leitor.close(); // fecha o arquivo

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
        }
    }
}


public class ExemploScanner {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite seu nome: ");
        String nome = entrada.nextLine();

        System.out.print("Digite sua idade: ");
        int idade = entrada.nextInt();

        System.out.println("\nDados informados:");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);

        entrada.close();
    }
}
}
