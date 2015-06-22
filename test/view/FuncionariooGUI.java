package view;

import controller.FuncionarioController;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.Funcionario;

/**
 *
 * @author Gabriel
 */
public class FuncionariooGUI extends javax.swing.JFrame {

    private int qtdRegistros = 0;
    private DefaultTableModel modelo;
    private JTable tabela;
    private List<Funcionario> funcionarios;

    public FuncionariooGUI() {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FuncionariooGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        initComponents();
        this.setLocationRelativeTo(null);

        this.criarTabela();
//        this.manutencaoRegistro(null);
    }

    private void criarTabela() {
        modelo = new DefaultTableModel();
        tabela = new JTable(modelo);

        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        modelo.addColumn("Sexo");
        modelo.addColumn("Área");
        modelo.addColumn("Função");

        /* Registros =========================================================*/
        preencherTabela(0, qtdRegistros);

        /* Eventos ========================================================== */
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manutencaoRegistro(evt);
            }
        });

        /* Configurações =====================================================*/
        scrollConsulta.setViewportView(tabela);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        if (tabela.getRowCount() >= 0) {
//            tabela.addRowSelectionInterval(0, 0);
//        }
    }

    private void preencherTabela(int min, int max) {
        funcionarios = new FuncionarioController().listarTodos(min, max);

        for (Funcionario f : funcionarios) {
            modelo.addRow(new Object[]{
                f.getCodigo(), f.getNome(), f.getSexo(), "Área", "Função"
            });
        }
    }

    private void manutencaoRegistro(java.awt.event.MouseEvent evt) {
        Funcionario f = funcionarios.get(tabela.getSelectedRow());

        txCodigo.setText(String.valueOf(f.getCodigo()));
        txNome.setText(f.getNome());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgSexo = new javax.swing.ButtonGroup();
        painelFundo = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        lbCodigo = new javax.swing.JLabel();
        txCodigo = new javax.swing.JTextField();
        lbNome = new javax.swing.JLabel();
        txNome = new javax.swing.JTextField();
        lbDtNascimento = new javax.swing.JLabel();
        txDtNascimento = new javax.swing.JTextField();
        lbDtEfetiva = new javax.swing.JLabel();
        txDtEfetiva = new javax.swing.JTextField();
        lbSexo = new javax.swing.JLabel();
        rbMasculino = new javax.swing.JRadioButton();
        rbFeminino = new javax.swing.JRadioButton();
        lbArea = new javax.swing.JLabel();
        btArea = new javax.swing.JButton();
        txNomeArea = new javax.swing.JTextField();
        txCodigoArea = new javax.swing.JTextField();
        lbFuncao = new javax.swing.JLabel();
        txFuncao = new javax.swing.JTextField();
        lbTelefone = new javax.swing.JLabel();
        txTelefone = new javax.swing.JTextField();
        lbEmail = new javax.swing.JLabel();
        txEmail = new javax.swing.JTextField();
        painelConsulta = new javax.swing.JPanel();
        scrollConsulta = new javax.swing.JScrollPane();
        btAnterior = new javax.swing.JButton();
        btProximo = new javax.swing.JButton();
        lbContaPagina = new javax.swing.JLabel();
        cbFiltro = new javax.swing.JComboBox();
        txPesquisa = new javax.swing.JTextField();
        btSalvar = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        sp1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Funcionário");

        lbTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("Funcionário");

        lbCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCodigo.setText("Código:");

        txCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txCodigo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txCodigo.setText("1");
        txCodigo.setEnabled(false);

        lbNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbNome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNome.setText("Nome:");

        txNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txNome.setText("Nome do Funcionário");

        lbDtNascimento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbDtNascimento.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDtNascimento.setText("Data Nascimento:");

        txDtNascimento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txDtNascimento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txDtNascimento.setText("99/99/9999");

        lbDtEfetiva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbDtEfetiva.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDtEfetiva.setText("Data Efetivação:");

        txDtEfetiva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txDtEfetiva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txDtEfetiva.setText("99/99/9999");

        lbSexo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbSexo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbSexo.setText("Sexo:");

        bgSexo.add(rbMasculino);
        rbMasculino.setText("Masculino");

        bgSexo.add(rbFeminino);
        rbFeminino.setText("Feminino");

        lbArea.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbArea.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbArea.setText("Área:");

        btArea.setText("Buscar");
        btArea.setToolTipText("Buscar área");

        txNomeArea.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txNomeArea.setText("Área de Atuação do Funcionário");
        txNomeArea.setEnabled(false);

        txCodigoArea.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txCodigoArea.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txCodigoArea.setText("1");

        lbFuncao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbFuncao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbFuncao.setText("Função:");

        txFuncao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txFuncao.setText("Função do Funcionário");

        lbTelefone.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbTelefone.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTelefone.setText("Telefone:");

        txTelefone.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txTelefone.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txTelefone.setText("(48) 9999-9999");
        txTelefone.setToolTipText("");

        lbEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbEmail.setText("E-mail:");

        txEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txEmail.setText("funcionario@empresa.com.br");

        painelConsulta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btAnterior.setText(">");
        btAnterior.setToolTipText("Próxima página");
        btAnterior.setEnabled(false);
        btAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnteriorActionPerformed(evt);
            }
        });

        btProximo.setText("<");
        btProximo.setToolTipText("Página anterior");
        btProximo.setEnabled(false);
        btProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProximoActionPerformed(evt);
            }
        });

        lbContaPagina.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbContaPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbContaPagina.setText("1");

        cbFiltro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Código", "Nome", "Usuário", "Área" }));
        cbFiltro.setEnabled(false);

        txPesquisa.setEnabled(false);

        javax.swing.GroupLayout painelConsultaLayout = new javax.swing.GroupLayout(painelConsulta);
        painelConsulta.setLayout(painelConsultaLayout);
        painelConsultaLayout.setHorizontalGroup(
            painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaLayout.createSequentialGroup()
                .addComponent(cbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btProximo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbContaPagina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAnterior))
            .addComponent(scrollConsulta)
        );
        painelConsultaLayout.setVerticalGroup(
            painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelConsultaLayout.createSequentialGroup()
                .addGroup(painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAnterior)
                    .addComponent(btProximo)
                    .addComponent(lbContaPagina)
                    .addComponent(cbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
        );

        btSalvar.setText("Salvar");
        btSalvar.setToolTipText("Salvar usuário");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btNovo.setText("Novo");
        btNovo.setToolTipText("Novo usuário");
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });

        btExcluir.setText("Excluir");
        btExcluir.setToolTipText("Excluir usuário");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        sp1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout painelFundoLayout = new javax.swing.GroupLayout(painelFundo);
        painelFundo.setLayout(painelFundoLayout);
        painelFundoLayout.setHorizontalGroup(
            painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFundoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(painelFundoLayout.createSequentialGroup()
                        .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(painelFundoLayout.createSequentialGroup()
                                    .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lbNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(painelFundoLayout.createSequentialGroup()
                                            .addComponent(txNome, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lbDtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txDtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lbDtEfetiva, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txDtEfetiva, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(painelFundoLayout.createSequentialGroup()
                                    .addComponent(lbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rbMasculino)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rbFeminino))
                                .addGroup(painelFundoLayout.createSequentialGroup()
                                    .addComponent(lbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txCodigoArea, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txNomeArea, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btArea)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txFuncao)))
                            .addGroup(painelFundoLayout.createSequentialGroup()
                                .addComponent(lbTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addComponent(painelConsulta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFundoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        painelFundoLayout.setVerticalGroup(
            painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFundoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigo)
                    .addComponent(txCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNome)
                    .addComponent(txNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDtNascimento)
                    .addComponent(txDtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDtEfetiva)
                    .addComponent(txDtEfetiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSexo)
                    .addComponent(rbMasculino)
                    .addComponent(rbFeminino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbArea)
                    .addComponent(txCodigoArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNomeArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btArea)
                    .addComponent(lbFuncao)
                    .addComponent(txFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelefone)
                    .addComponent(txTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail)
                    .addComponent(txEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btSalvar)
                        .addComponent(btNovo)
                        .addComponent(btExcluir))
                    .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelFundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelFundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnteriorActionPerformed
//        proximaPagina();
    }//GEN-LAST:event_btAnteriorActionPerformed

    private void btProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProximoActionPerformed
//        paginaAnterior();
    }//GEN-LAST:event_btProximoActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
//        salvarRegistro();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
//        inclusaoRegistro();
    }//GEN-LAST:event_btNovoActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
//        excluirRegistro();
    }//GEN-LAST:event_btExcluirActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgSexo;
    private javax.swing.JButton btAnterior;
    private javax.swing.JButton btArea;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btProximo;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox cbFiltro;
    private javax.swing.JLabel lbArea;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbContaPagina;
    private javax.swing.JLabel lbDtEfetiva;
    private javax.swing.JLabel lbDtNascimento;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbFuncao;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbSexo;
    private javax.swing.JLabel lbTelefone;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel painelConsulta;
    private javax.swing.JPanel painelFundo;
    private javax.swing.JRadioButton rbFeminino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JScrollPane scrollConsulta;
    private javax.swing.JSeparator sp1;
    private javax.swing.JTextField txCodigo;
    private javax.swing.JTextField txCodigoArea;
    private javax.swing.JTextField txDtEfetiva;
    private javax.swing.JTextField txDtNascimento;
    private javax.swing.JTextField txEmail;
    private javax.swing.JTextField txFuncao;
    private javax.swing.JTextField txNome;
    private javax.swing.JTextField txNomeArea;
    private javax.swing.JTextField txPesquisa;
    private javax.swing.JTextField txTelefone;
    // End of variables declaration//GEN-END:variables
}
