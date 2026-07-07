# Jogo de UNO em Java 

[Diagrama UML do Projeto] <img width="1451" height="841" alt="image" src="https://github.com/user-attachments/assets/af24ddc0-f230-4f7d-bd1e-fde5ae1dcdc7" />
*(Nota: Substitua o caminho acima pelo link ou caminho real da imagem do seu diagrama gerado no Umbrelo)*

## Sobre o Projeto
Este é um jogo clássico de UNO desenvolvido inteiramente em **Java**, com interface gráfica construída em **Java Swing**. O projeto foi estruturado utilizando o padrão de arquitetura **MVC (Model-View-Controller)** para garantir uma separação limpa entre as regras de negócio, a interface do utilizador e o controlo do fluxo do jogo.

O jogo permite que um jogador humano enfrente **3 Bots** controlados pelo sistema, aplicando as regras oficiais de compra, descarte e penalidades do UNO.

## Funcionalidades
* **Motor de Regras Oficial:** Validação rigorosa de jogadas por cor, número ou tipo de carta.
* **Cartas Especiais:** Implementação funcional das cartas Pular (Bloquear), Inverter Sentido, +2, Coringa (Mudar Cor) e Coringa +4.
* **Sistema de Penalidades:** Acúmulo de punições (ex: jogar um +2 em cima de um +2).
* **Inteligência Artificial (Bots):** Os oponentes analisam as próprias mãos, selecionam a melhor jogada baseada na mesa e escolhem a melhor cor ao jogar curingas de forma autônoma.
* **Interface Gráfica Dinâmica:** A mão do jogador ajusta-se automaticamente independente da quantidade de cartas, utilizando geração dinâmica de botões e redimensionamento de sprites.
* **Baralho Interativo:** Compra de cartas diretamente clicando no monte do baralho na interface.

## Arquitetura e Tecnologias
O projeto foi desenvolvido puramente em Java (POO) com a seguinte divisão de responsabilidades:
* **Model:** Gere o estado do jogo (`Mesa`, `Baralho`), as entidades (`JogadorHumano`, `JogadorBot`) e a abstração das `Cartas`.
* **View:** Interface gráfica (`Janela.java`) desenvolvida com Swing, 100% "cega" às regras do jogo, apenas reagindo aos comandos.
* **Controller:** O cérebro (`Controller.java`) que orquestra a máquina de estados, validando turnos, processando cliques e ordenando as jogadas dos Bots.

**Ferramentas utilizadas:**
* Java Development Kit (JDK)
* IDE: NetBeans (com GUI Builder)
* Modelagem: Umbrello (Diagramas UML)

## Como Executar
1. Clone este repositório para a sua máquina local:
   ```bash
   git clone [https://github.com/seu-usuario/nome-do-repositorio.git](https://github.com/seu-usuario/nome-do-repositorio.git)
