
package view;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import model.*;

public class Janela extends javax.swing.JFrame {
    
    private controller.Controller gerenciadorJogo;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Janela.class.getName());
    public Janela() {
        initComponents();
    }
    
    public void setController(controller.Controller controller){
        this.gerenciadorJogo = controller;
    }
    
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        painelMao = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/sprites/cartaOculta.jpg"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/sprites/cartaOculta.jpg"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/sprites/cartaOculta.jpg"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/sprites/amarelo (4).png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(353, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(390, 390, 390))
            .addGroup(layout.createSequentialGroup()
                .addGap(361, 361, 361)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(painelMao, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(119, 119, 119)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3))
                    .addComponent(jLabel4))
                .addGap(85, 85, 85)
                .addComponent(painelMao, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void inicializarBotoes(ArrayList<Carta> mao){//logica do model precisa garantir que a mao recebida seja a do humano
        
        painelMao.removeAll();
        
        
        for(int i=0; i< mao.size(); i++){
            Carta carta = mao.get(i);
            String nomeArqCarta = "";
            
            
            if(carta instanceof CartaNumerada cartaNumerada)
                nomeArqCarta = carta.getCor() + cartaNumerada.getNumero();
            if(carta instanceof CartaEspecial cartaEspecial)
                nomeArqCarta = carta.getCor() + cartaEspecial.getEfeito();
            
            JButton botaoCarta = new JButton();
            botaoCarta.setActionCommand(String.valueOf(i));
            
            java.net.URL imgURL = getClass().getResource("/resources/sprites/" + nomeArqCarta +".png");
            if(imgURL != null){
                botaoCarta.setIcon(redimensionarImagem(imgURL, 70, 100));
            }else{
                System.out.println("Imagem não encontrada: "+ nomeArqCarta);
            }
            
            botaoCarta.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    processarCliqueNaCarta(evt);
                }
            });
            
            botaoCarta.setBorderPainted(false);
            botaoCarta.setContentAreaFilled(false);
            
            painelMao.add(botaoCarta);
            
        }
        
        painelMao.revalidate();
        painelMao.repaint();
    }
    
   
    private void processarCliqueNaCarta(java.awt.event.ActionEvent evt) {
        JButton botaoClicado = (JButton) evt.getSource();
        String indiceString = botaoClicado.getActionCommand();

        System.out.println("[VIEW] Clique recebido na interface para a carta de índice: " + indiceString);

        if (indiceString == null || indiceString.isEmpty()) {
            return; 
        }

        int indiceCarta = Integer.parseInt(indiceString);

        // A View passa apenas o número para a ponte do Controller
        gerenciadorJogo.processarCliqueInterface(indiceCarta);
    }
    
    //
    public void atualizarPilha(Carta cartaNoTopo) {
        String nomeArqCarta = "";
        
        if(cartaNoTopo instanceof CartaNumerada cartaNumerada)
            nomeArqCarta = cartaNoTopo.getCor() + cartaNumerada.getNumero();
        if(cartaNoTopo instanceof CartaEspecial cartaEspecial)
            nomeArqCarta = cartaNoTopo.getCor() + cartaEspecial.getEfeito();
        
        java.net.URL imgURL = getClass().getResource("/resources/sprites/" + nomeArqCarta + ".png");
        
        if(imgURL != null){
            jLabel4.setIcon(redimensionarImagem(imgURL, 100, 140)); // jLabel4 é a carta da mesa
        } else {
            System.out.println("Imagem não encontrada para a mesa: " + nomeArqCarta);
        }
    }
    
    private ImageIcon redimensionarImagem(java.net.URL imgURL, int larguraDesejada, int alturaDesejada) {
        if (imgURL != null) {
            ImageIcon iconeOriginal = new ImageIcon(imgURL);
            // Pega a imagem real de dentro do ícone
            java.awt.Image imagemReal = iconeOriginal.getImage(); 
            // Cria uma nova versão redimensionada com a qualidade "SMOOTH" (suave)
            java.awt.Image imagemRedimensionada = imagemReal.getScaledInstance(larguraDesejada, alturaDesejada, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(imagemRedimensionada);
        }
        return null;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel painelMao;
    // End of variables declaration//GEN-END:variables
}
