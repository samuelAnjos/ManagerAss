/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.JOptionPane;
// a linha abaixo importa recursos  da biblioteca rs2xml que faz a relacao da tabela de pesquisa
import net.proteanit.sql.DbUtils;

/**
 *
 * @author samue
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {
	initComponents();
	conexao = ModuloConexao.conector();
    }

    // foi um ctrol + c do metoda adicionar da TelaUsuario
    // metodo para adicionar clientes
    private void adicionar() {
	String sql = "insert  into tbclientes(nomecli,endcli,fonecli,emailcli) values(?,?,?,?)";
	try {
	    pst = conexao.prepareStatement(sql);

	    pst.setString(1, txtCliNome.getText());
	    pst.setString(2, txtCliEnde.getText());
	    pst.setString(3, txtCliTele.getText());
	    pst.setString(4, txtCliEmail.getText());

	    //validação dos campos obrigatorios
	    if ((txtCliNome.getText().isEmpty()) || (txtCliTele.getText().isEmpty())) {
		JOptionPane.showMessageDialog(null, "Preencha Todos os Campos Obrigatótios");
	    } else {

		// A LINHA ABAIXO ATUALIZA A TABELA usuarios com os dados recebidos do formulario
		// a estrutura abaixo é usada para confimar  a inserçao dos dados no mySQL
		int adicionado = pst.executeUpdate();
		if (adicionado > 0) {
		    JOptionPane.showMessageDialog(null, "Cliente Adicionado Com Sucesso");
		    txtCliNome.setText(null);
		    txtCliEnde.setText(null);
		    txtCliTele.setText(null);
		    txtCliEmail.setText(null);

		}
	    }
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    // metodo para pesquisar clientes pelo nome com filtro
    private void pesquisarCliente() {
	String sql = "select * from tbclientes where nomecli like ? ";
	try {
	    pst = conexao.prepareStatement(sql);
	    //passando o conteudo da caixa de pesquisa para o ?
	    //atençao ao "%" -> continuaçao da String sql
	    pst.setString(1, txtCliPesq.getText() + "%");
	    rs = pst.executeQuery();
	    // a linha abaixo usa a biblioteca rs2xml.jar para preecher a tabela
	    tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
	} catch (Exception e) {
	}
    }

    //metodo para setar os campos do formulario com o conteudo da tabela
    public void setarCampos() {
	int setar = tblClientes.getSelectedRow();
	txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
	txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
	txtCliEnde.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
	txtCliTele.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
	txtCliEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
//A LINHA ABAIXO desabilita o botao adicionar
    btnAdici.setEnabled(false);
    }

// metodo para alterar dados do cliente
    private void alterar() {
	String sql = "update tbclientes set nomecli=?,endcli=?,fonecli=?,emailcli=? where idcli=?";
	try {
	    pst = conexao.prepareStatement(sql);
	    pst.setString(1, txtCliNome.getText());
	    pst.setString(2, txtCliEnde.getText());
	    pst.setString(3, txtCliTele.getText());
	    pst.setString(4, txtCliEmail.getText());
	    pst.setString(5, txtCliId.getText());

	    if ((txtCliNome.getText().isEmpty()) || (txtCliTele.getText().isEmpty())) {
		JOptionPane.showMessageDialog(null, "Preencha Todos os Campos Obrigatótios");
	    } else {

		// A LINHA ABAIXO ATUALIZA A TABELA clientes com os dados recebidos do formulario
		// a estrutura abaixo é usada para confimar  a ALTERACAO dos dados no mySQL
		int adicionado = pst.executeUpdate();
		if (adicionado > 0) {
		    JOptionPane.showMessageDialog(null, "Dados do Cliente Alterado Com Sucesso");
		   
		    txtCliNome.setText(null);
		    txtCliEnde.setText(null);
		    txtCliTele.setText(null);
		    txtCliEmail.setText(null);
		    
		     btnAdici.setEnabled(true);
		}
	    }

	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }
    
    // metodo resposavel pela remocao do cliente
    private void remover() {
        // a estrutura abaixo confirma a remoçao do cliente
        int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza Que Deseja Remover Este Cliente ?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbclientes where idcli=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCliId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário Removido com Sucesso");
                    txtCliNome.setText(null);
		    txtCliEnde.setText(null);
		    txtCliTele.setText(null);
		    txtCliEmail.setText(null);
		    
		     btnAdici.setEnabled(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCliNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCliEnde = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCliTele = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCliEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnAdici = new javax.swing.JButton();
        btnAltera = new javax.swing.JButton();
        btnApaga = new javax.swing.JButton();
        txtCliPesq = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");
        setMinimumSize(new java.awt.Dimension(124, 34));
        setPreferredSize(new java.awt.Dimension(673, 409));

        jLabel1.setText("*Nome");

        jLabel2.setText("Endereço");

        jLabel3.setText("*Telefone");

        jLabel4.setText("Email");

        jLabel5.setText("* Campos Obrigatórios");

        btnAdici.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnAdici.setToolTipText("Adicionar");
        btnAdici.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdici.setPreferredSize(new java.awt.Dimension(70, 70));
        btnAdici.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdiciActionPerformed(evt);
            }
        });

        btnAltera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/iconfinder_document_edit_48757.png"))); // NOI18N
        btnAltera.setToolTipText("Atuallizar");
        btnAltera.setPreferredSize(new java.awt.Dimension(70, 70));
        btnAltera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlteraActionPerformed(evt);
            }
        });

        btnApaga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/te.png"))); // NOI18N
        btnApaga.setToolTipText("Apagar");
        btnApaga.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnApaga.setPreferredSize(new java.awt.Dimension(70, 70));
        btnApaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagaActionPerformed(evt);
            }
        });

        txtCliPesq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesqKeyReleased(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/lupaFim.png"))); // NOI18N

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jLabel7.setText("Id Cliente");

        txtCliId.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnAdici, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(btnAltera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btnApaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliEnde, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliTele, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCliPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(51, 51, 51))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(txtCliPesq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCliEnde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtCliTele, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAltera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdici, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnApaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdiciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdiciActionPerformed
	// metodo para adicionar clientes (Este merto chamado esta acima)
	adicionar();
    }//GEN-LAST:event_btnAdiciActionPerformed

    // o evento abaixo	é do tipo "enquanto for digitando"
    private void txtCliPesqKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesqKeyReleased
	// chamar o metodo pesquisar clientes
	pesquisarCliente();
    }//GEN-LAST:event_txtCliPesqKeyReleased

    // evento que sera usado para ssetar os campos da tabela (clicando com o mouse)
    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
	// chamando o metodo para setar os campos
	setarCampos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnAlteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlteraActionPerformed
        // chamando o metodo para alterar dados do cliente
	alterar();
    }//GEN-LAST:event_btnAlteraActionPerformed

    private void btnApagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagaActionPerformed
        // chamando o metodo remover
	remover();
    }//GEN-LAST:event_btnApagaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdici;
    private javax.swing.JButton btnAltera;
    private javax.swing.JButton btnApaga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEnde;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesq;
    private javax.swing.JTextField txtCliTele;
    // End of variables declaration//GEN-END:variables
}
