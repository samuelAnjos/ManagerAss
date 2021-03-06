/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;
import br.com.infox.dal.ModuloConexao;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author samue
 */
public class TelaPrincipal extends javax.swing.JFrame {

    Connection conexao = null;
    
    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
	conexao = ModuloConexao.conector();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Desktop = new javax.swing.JDesktopPane();
        lblUsuario = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        Man = new javax.swing.JMenu();
        ManCli = new javax.swing.JMenuItem();
        ManOs = new javax.swing.JMenuItem();
        ManUsuario = new javax.swing.JMenuItem();
        RelatorioServico = new javax.swing.JMenu();
        menRelCli = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        Ajuda = new javax.swing.JMenu();
        AjudaSobre = new javax.swing.JMenuItem();
        Opcao = new javax.swing.JMenu();
        OpcaoSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Controle OS");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUsuario.setText("Usu??rio");

        lblData.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblData.setText("Data");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/iconfinder_lowercase_letter_x_blue_3052271.png"))); // NOI18N

        Man.setText("Cadastro");

        ManCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        ManCli.setText("Cliente");
        ManCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManCliActionPerformed(evt);
            }
        });
        Man.add(ManCli);

        ManOs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        ManOs.setText("OS");
        ManOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManOsActionPerformed(evt);
            }
        });
        Man.add(ManOs);

        ManUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        ManUsuario.setText("Usu??rios");
        ManUsuario.setEnabled(false);
        ManUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManUsuarioActionPerformed(evt);
            }
        });
        Man.add(ManUsuario);

        Menu.add(Man);

        RelatorioServico.setText("Relat??rio");
        RelatorioServico.setEnabled(false);

        menRelCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        menRelCli.setText("Clientes");
        menRelCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelCliActionPerformed(evt);
            }
        });
        RelatorioServico.add(menRelCli);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem4.setText("Servi??os");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        RelatorioServico.add(jMenuItem4);

        Menu.add(RelatorioServico);

        Ajuda.setText("Ajuda");

        AjudaSobre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_MASK));
        AjudaSobre.setText("Sobre");
        AjudaSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjudaSobreActionPerformed(evt);
            }
        });
        Ajuda.add(AjudaSobre);

        Menu.add(Ajuda);

        Opcao.setText("Op????es");

        OpcaoSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        OpcaoSair.setText("Sair");
        OpcaoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpcaoSairActionPerformed(evt);
            }
        });
        Opcao.add(OpcaoSair);

        Menu.add(Opcao);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblData)
                            .addComponent(lblUsuario)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblUsuario)
                .addGap(33, 33, 33)
                .addComponent(lblData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Desktop)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(902, 483));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ManUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManUsuarioActionPerformed
        // As linhas abaixo abri o form TelaUsuario dentro do desktop pane
        TelaUsuario usuario = new TelaUsuario();
        usuario.setVisible(true);
        Desktop.add(usuario);
    }//GEN-LAST:event_ManUsuarioActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // gerando um relatorio de servi??os
	int cofirma = JOptionPane.showConfirmDialog(null, "Confirma a emiss??o deste relat??rio?","Aten????o",JOptionPane.YES_NO_OPTION);
	if(cofirma == JOptionPane.YES_OPTION){
	    //emitindo o relatorio com o framework JasperReports
	    try {
		// usando a classe JasperPrint para preparar	a impresao de um relatorio
		JasperPrint print = JasperFillManager.fillReport("C:/Ireporte/servicos.jasper",null,conexao);
		// a linha abaixo abaixo  exibe o relat??rio atraves da classe JasperViewer
		JasperViewer.viewReport(print,false);
	    } catch (Exception e) {
		JOptionPane.showMessageDialog(null, e);
	    }
	}
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
      // as linhas abaixo substituem a lblData pela data atual 
      // do sistema ao inicializar
      Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
      lblData.setText(formatador.format(data));
    }//GEN-LAST:event_formWindowActivated

    private void OpcaoSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpcaoSairActionPerformed
        // exibe uma caixa de dialogo
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?","Aten????o",JOptionPane.YES_NO_OPTION);
        if(sair == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_OpcaoSairActionPerformed

    private void AjudaSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjudaSobreActionPerformed
        // chamando  a tela sobre
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true);
    }//GEN-LAST:event_AjudaSobreActionPerformed

    private void ManCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManCliActionPerformed
        // chama a telaClienre
        TelaCliente sobre = new TelaCliente();
        sobre.setVisible(true);
        Desktop.add(sobre);
    }//GEN-LAST:event_ManCliActionPerformed

    private void ManOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManOsActionPerformed
        // Chama a tela OS
	TelaOS os = new TelaOS();
	os.setVisible(true);
	Desktop.add(os);    
    }//GEN-LAST:event_ManOsActionPerformed

    private void menRelCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelCliActionPerformed
        // gerando um relatorio de clientes
	int cofirma = JOptionPane.showConfirmDialog(null, "Confirma a impress??o deste relat??rio?","Aten????o",JOptionPane.YES_NO_OPTION);
	if(cofirma == JOptionPane.YES_OPTION){
	    //imprimindo o relatorio com o framework JasperReports
	    try {
		// usando a classe JasperPrint para preparar	a impresao de um relatorio
		JasperPrint print = JasperFillManager.fillReport("C:/Ireporte/clientes.jasper",null,conexao);
		// a linha abaixo abaixo  exibe o relat??rio atraves da classe JasperViewer
		JasperViewer.viewReport(print,false);
	    } catch (Exception e) {
		JOptionPane.showMessageDialog(null, e);
	    }
	}
    }//GEN-LAST:event_menRelCliActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Ajuda;
    private javax.swing.JMenuItem AjudaSobre;
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenu Man;
    private javax.swing.JMenuItem ManCli;
    private javax.swing.JMenuItem ManOs;
    public static javax.swing.JMenuItem ManUsuario;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenu Opcao;
    private javax.swing.JMenuItem OpcaoSair;
    public static javax.swing.JMenu RelatorioServico;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JMenuItem menRelCli;
    // End of variables declaration//GEN-END:variables
}
