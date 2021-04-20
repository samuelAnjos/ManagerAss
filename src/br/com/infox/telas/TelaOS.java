/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author samue
 */
public class TelaOS extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    // a linha abaixo cria uma variavel para armazenar um texto de acordo com o radion button selecionado
    private String tipo;

    /**
     * Creates new form TelaOS
     */
    public TelaOS() {
	initComponents();
	conexao = ModuloConexao.conector();
    }

    private void pesquisarCliente() {
	String sql = "select idcli as Id, nomecli as Nome, fonecli as Fone from tbclientes where nomecli like ?";
	try {
	    pst = conexao.prepareStatement(sql);
	    pst.setString(1, txtCliAoLadoDaLupa.getText() + "%");
	    rs = pst.executeQuery();
	    tblAbaixoLupa.setModel(DbUtils.resultSetToTableModel(rs));
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    private void setarCampo() {
	int setar = tblAbaixoLupa.getSelectedRow();
	txtIdLupa.setText(tblAbaixoLupa.getModel().getValueAt(setar, 0).toString());
    }

    // metodo para cadastrar uma Ordem  de Serviço (OS)
    private void emitirOS() {
	String sql = "insert into tbos(tipo, situacao,equipamento,defeito,servico,tecnico,valor,idcli) values(?,?,?,?,?,?,?,?)";

	try {
	    pst = conexao.prepareStatement(sql);
	    pst.setString(1, tipo);
	    pst.setString(2, ComboEscolha.getSelectedItem().toString());
	    pst.setString(3, txtEquipamento.getText());
	    pst.setString(4, txtDefeito.getText());
	    pst.setString(5, txtServico.getText());
	    pst.setString(6, txtTecnico.getText());
	    // .replace substitui a vírgula pelo ponto
	    pst.setString(7, txtPreco.getText().replace(",", "."));
	    pst.setString(8, txtIdLupa.getText());

	    // validacao dos campos obrigatorios
	    if ((txtIdLupa.getText().isEmpty()) || (txtEquipamento.getText().isEmpty()) || (txtDefeito.getText().isEmpty())) {
		JOptionPane.showMessageDialog(null, "Preencha Todos os Campos Obrigatórios");
	    } else {
		int adiciondo = pst.executeUpdate();
		if (adiciondo > 0) {
		    JOptionPane.showMessageDialog(null, "Ordem de Serviço Emitida com Sucesso");
		}
		txtIdLupa.setText(null);
		txtEquipamento.setText(null);
		txtDefeito.setText(null);
		txtServico.setText(null);
		txtTecnico.setText(null);
		txtPreco.setText(null);
	    }

	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    //metodo para perquisar ordeem de serviço
    private void pesquisarOS() {
	//a linha abaixo cria uma caixa de entrada do tipo JOption Pane
	String numOS = JOptionPane.showInputDialog("Número da Ordem de Serviço");
	String sql = "select * from tbos where os=" + numOS;
	try {
	    pst = conexao.prepareStatement(sql);
	    rs = pst.executeQuery();
	    if (rs.next()) {
		txtNumOS.setText(rs.getString(1));
		txtData.setText(rs.getString(2));
		// setando os raddions button
		String rbtTipo = rs.getString(3);
		if (rbtTipo.equals("os")) {
		    txtOrdemSer.setSelected(true);
		    tipo = "os";
		} else {
		    txtOrcam.setSelected(true);
		    tipo = "Orçamento";
		}
		ComboEscolha.setSelectedItem(rs.getString(4));
		txtEquipamento.setText(rs.getString(5));
		txtDefeito.setText(rs.getString(6));
		//
		txtServico.setText(rs.getString(7));
		txtTecnico.setText(rs.getString(8));
		txtPreco.setText(rs.getString(9));
		txtIdLupa.setText(rs.getString(10));
		// evitando problemas
		btnAdicionar.setEnabled(false);
		txtCliAoLadoDaLupa.setEnabled(false);
		tblAbaixoLupa.setVisible(false);

	    } else {
		JOptionPane.showMessageDialog(null, "Ordem de serviço não cadastrada");

	    }
	} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
	    JOptionPane.showMessageDialog(null, "Ordem de Serviço Inválida");
	    //System.out.println(e);
	} catch (Exception e2) {
	    JOptionPane.showMessageDialog(null, e2);
	}
    }

    //metodo para alterar uma OS
    private void alterar_os() {
	String sql = "update tbos set tipo=?, situacao=?, equipamento=?,defeito=?,servico=?,tecnico=?,valor=? where os=?";

	try {
	    pst = conexao.prepareStatement(sql);
	    pst.setString(1, tipo);
	    pst.setString(2, ComboEscolha.getSelectedItem().toString());
	    pst.setString(3, txtEquipamento.getText());
	    pst.setString(4, txtDefeito.getText());
	    pst.setString(5, txtServico.getText());
	    pst.setString(6, txtTecnico.getText());
	    // .replace substitui a vírgula pelo ponto
	    pst.setString(7, txtPreco.getText().replace(",", "."));
	    pst.setString(8, txtNumOS.getText()); // i don't know

	    // validacao dos campos obrigatorios
	    if ((txtIdLupa.getText().isEmpty()) || (txtEquipamento.getText().isEmpty()) || (txtDefeito.getText().isEmpty())) {
		JOptionPane.showMessageDialog(null, "Preencha Todos os Campos Obrigatórios");
	    } else {
		int adiciondo = pst.executeUpdate();
		if (adiciondo > 0) {
		    JOptionPane.showMessageDialog(null, "Ordem de Serviço Alterada com Sucesso");
		}
		txtNumOS.setText(null);
		txtData.setText(null);
		txtIdLupa.setText(null);
		txtEquipamento.setText(null);
		txtDefeito.setText(null);
		txtServico.setText(null);
		txtTecnico.setText(null);
		txtPreco.setText(null);

		//abilitando pq pode isncrever de novo
		btnAdicionar.setEnabled(true);
		txtCliAoLadoDaLupa.setEnabled(true);
		tblAbaixoLupa.setVisible(true);
	    }

	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }
    

    //metodo para exclir uma OS
    private void excluir_os() {
	int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza Que Deseja Excluir Esta Ordem de Serviço?", "Atenção", JOptionPane.YES_NO_OPTION);
	if (confirma == JOptionPane.YES_OPTION) {
	    String sql = "delete from tbos where os=?";
	    try {
		pst = conexao.prepareStatement(sql);
		pst.setString(1, txtNumOS.getText());
		int seApagado = pst.executeUpdate();
		if (seApagado > 0) {
		    JOptionPane.showMessageDialog(null, "Ordem de Serviço Excluída Com Sucesso");
		    txtNumOS.setText(null);
		    txtData.setText(null);
		    txtIdLupa.setText(null);
		    txtEquipamento.setText(null);
		    txtDefeito.setText(null);
		    txtServico.setText(null);
		    txtTecnico.setText(null);
		    txtPreco.setText(null);

		    //abilitando pq pode isncrever de novo
		    btnAdicionar.setEnabled(true);
		    txtCliAoLadoDaLupa.setEnabled(true);
		    tblAbaixoLupa.setVisible(true);

		}
	    } catch (Exception e) {
		JOptionPane.showMessageDialog(null, e);
	    }
	}
    }
    
    //metodo para imprimir uma os oi
    private void imprimirF(){
	  // imprimindo uma OS
	int cofirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão desta Ordem de Serviço?","Atenção",JOptionPane.YES_NO_OPTION);
	if(cofirma == JOptionPane.YES_OPTION){
	    //emitindo o relatorio com o framework JasperReports
	    try {
		// usando o HashMap para filtrar 
		HashMap filtro = new HashMap();
		filtro.put("os",Integer.parseInt(txtNumOS.getText()));
		// usando a classe JasperPrint para preparar	a impresao de um relatorio
		JasperPrint print = JasperFillManager.fillReport("C:/Ireporte/os.jasper",filtro,conexao);
		// a linha abaixo abaixo  exibe o relatório atraves da classe JasperViewer
		JasperViewer.viewReport(print,false);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNumOS = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        txtOrcam = new javax.swing.JRadioButton();
        txtOrdemSer = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        ComboEscolha = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        txtCliAoLadoDaLupa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtIdLupa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAbaixoLupa = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtEquipamento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDefeito = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtServico = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTecnico = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        btlVerificar = new javax.swing.JButton();
        txtAtualizar = new javax.swing.JButton();
        txtExcluir = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("OS");
        setPreferredSize(new java.awt.Dimension(673, 409));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nº OS");

        jLabel2.setText("Data");

        txtNumOS.setEditable(false);

        txtData.setEditable(false);
        txtData.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N

        buttonGroup1.add(txtOrcam);
        txtOrcam.setText("Orçamento");
        txtOrcam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrcamActionPerformed(evt);
            }
        });

        buttonGroup1.add(txtOrdemSer);
        txtOrdemSer.setText(" Ordem de Serviço");
        txtOrdemSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrdemSerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtOrcam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(txtOrdemSer))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNumOS, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtData)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtData)
                    .addComponent(txtNumOS, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOrcam)
                    .addComponent(txtOrdemSer))
                .addContainerGap())
        );

        jLabel3.setText("Situação");

        ComboEscolha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Na bancada", "Entrega OK", "Orçamento REPROVADO", "Aguardando Aprovação", "Aguardando peças", "Abandonado pelo cliente\t", "Retornou" }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        txtCliAoLadoDaLupa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliAoLadoDaLupaKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/lupaFim.png"))); // NOI18N

        txtIdLupa.setEnabled(false);

        jLabel5.setText("* Id");

        tblAbaixoLupa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Title 3"
            }
        ));
        tblAbaixoLupa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAbaixoLupaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAbaixoLupa);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCliAoLadoDaLupa, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdLupa, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtCliAoLadoDaLupa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtIdLupa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setText("* Equipamento");

        jLabel7.setText("* Defeito");

        jLabel8.setText("Serviço");

        jLabel9.setText("Tecnico");

        jLabel10.setText("Valor");

        txtPreco.setText("0");

        btlVerificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/ve_32.png"))); // NOI18N
        btlVerificar.setToolTipText("Consultar");
        btlVerificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btlVerificar.setPreferredSize(new java.awt.Dimension(35, 35));
        btlVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlVerificarActionPerformed(evt);
            }
        });

        txtAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/pencil_32.png"))); // NOI18N
        txtAtualizar.setToolTipText("Atualizar");
        txtAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtAtualizar.setPreferredSize(new java.awt.Dimension(35, 35));
        txtAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAtualizarActionPerformed(evt);
            }
        });

        txtExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/del_32.png"))); // NOI18N
        txtExcluir.setToolTipText("Apagar");
        txtExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtExcluir.setPreferredSize(new java.awt.Dimension(35, 35));
        txtExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExcluirActionPerformed(evt);
            }
        });

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/Ad_32.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.setPreferredSize(new java.awt.Dimension(35, 35));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/imp.png"))); // NOI18N
        jButton1.setToolTipText("Imprimir");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(ComboEscolha, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEquipamento)
                    .addComponent(txtDefeito)
                    .addComponent(txtServico)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(btlVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtPreco))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(txtAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                                .addComponent(txtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(ComboEscolha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAtualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btlVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        setBounds(0, 0, 673, 409);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliAoLadoDaLupaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliAoLadoDaLupaKeyReleased
	// chama o metido pesquisar cliente
	pesquisarCliente();
    }//GEN-LAST:event_txtCliAoLadoDaLupaKeyReleased

    private void tblAbaixoLupaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAbaixoLupaMouseClicked
	// chama o metodo setarCampos
	setarCampo();
    }//GEN-LAST:event_tblAbaixoLupaMouseClicked

    private void txtOrcamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrcamActionPerformed
	// atruibuindo um texto a variavel tipo, se selecionado
	tipo = "Orçamento";
    }//GEN-LAST:event_txtOrcamActionPerformed

    private void txtOrdemSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrdemSerActionPerformed
	// a linha abaixo atribui um texto a variavel tipo, se o radion button estiver selecionado
	tipo = "os";
    }//GEN-LAST:event_txtOrdemSerActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
	// ao abrir o form marcar o radion button Orçamento
	txtOrcam.setSelected(true);
	tipo = "Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
	// chamar o metodo emitirOS
	emitirOS();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btlVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlVerificarActionPerformed
	// metido para chamar pesquisarOS;
	pesquisarOS();

    }//GEN-LAST:event_btlVerificarActionPerformed

    private void txtAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAtualizarActionPerformed
	// chama o metodo alterar os
	alterar_os();
    }//GEN-LAST:event_txtAtualizarActionPerformed

    private void txtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExcluirActionPerformed
        // metodo para excluir uma ordem de serviçp (OS)
	excluir_os();
    }//GEN-LAST:event_txtExcluirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // chamando o metodo para imprimir uma os
	imprimirF();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboEscolha;
    private javax.swing.JButton btlVerificar;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAbaixoLupa;
    private javax.swing.JButton txtAtualizar;
    private javax.swing.JTextField txtCliAoLadoDaLupa;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDefeito;
    private javax.swing.JTextField txtEquipamento;
    private javax.swing.JButton txtExcluir;
    private javax.swing.JTextField txtIdLupa;
    private javax.swing.JTextField txtNumOS;
    private javax.swing.JRadioButton txtOrcam;
    private javax.swing.JRadioButton txtOrdemSer;
    private javax.swing.JTextField txtPreco;
    private javax.swing.JTextField txtServico;
    private javax.swing.JTextField txtTecnico;
    // End of variables declaration//GEN-END:variables
}
