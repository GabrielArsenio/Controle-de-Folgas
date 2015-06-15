package view;

import controller.UsuarioController;
import java.awt.Color;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.Usuario;
import static util.Validadores.*;

/**
 *
 * @author Gabriel
 */
public class UsuarioGUI extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    private char modo; // I - Inclusao, M - Manutenção/Alteração
    private int qtdRegistros = 0;
    private int pagina;
    private List<Usuario> usuarios;
    private NovaSenhaGUI novaSenhaGUI = new NovaSenhaGUI();

    private DefaultTableModel modelo;
    private JTable tabela;

    public UsuarioGUI(Usuario usuarioLogado) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsuarioGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        initComponents();
        setLocationRelativeTo(null);

        this.usuarioLogado = usuarioLogado;
        this.criarTabela();

//        this.manutencaoRegistro(null);
    }

    private void criarTabela() {
        modelo = new DefaultTableModel();
        tabela = new JTable(modelo);

        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        modelo.addColumn("Usuário");
//        modelo.addColumn("Área");

        preencherTabela(0, qtdRegistros);

        //Eventos
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manutencaoRegistro(evt);
            }
        });

        //Configurações
        scrollConsulta.setViewportView(tabela);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        inclusaoRegistro();
//        tabela.addRowSelectionInterval(0, 0);
    }

    /* Busca no banco e adiciona as linhas */
    private void preencherTabela(int min, int max) {
        usuarios = UsuarioController.listarTodos(min, max);

        for (Usuario u : usuarios) {

            modelo.addRow(new Object[]{
                u.getCodigo(), u.getNome(), u.getUsuario(), "u.getArea()"
            });
        }
    }

    private void inclusaoRegistro() {
        modo = 'I';
        txCodigo.setText(null);
        txNome.setText(null);
        txUsuario.setText(null);
        txNome.grabFocus();
        lbTitulo.setText("Usuário - Inclusão");
        lbStatus.setText("");

        cbNivel.removeAllItems();
        switch (usuarioLogado.getNivel()) {
            case 0:
                cbNivel.addItem("Administrador");
                cbNivel.addItem("Coordenador");
                cbNivel.addItem("Funcionário");
                break;
            case 1:
                cbNivel.addItem("Coordenador");
                cbNivel.addItem("Funcionário");
                break;
            case 2:
                cbNivel.addItem("Funcionário");
                break;
        }
    }

    private void manutencaoRegistro(java.awt.event.MouseEvent evt) {
        Usuario usu = usuarios.get(tabela.getSelectedRow());

        modo = 'M';
        lbTitulo.setText("Usuário - Manutenção");
        txCodigo.setText(String.valueOf(usu.getCodigo()));
        txNome.setText(usu.getNome());
        txUsuario.setText(usu.getUsuario());
        cbNivel.setSelectedIndex(usu.getNivel());
    }

    private void salvarRegistro() {
        int index;

        if (!validaCampoVazio(txNome.getText())) {
            lbStatus.setText("Informe o nome.");
            txNome.grabFocus();
            return;
        }

        if (!validaCampoVazio(txUsuario.getText())) {
            lbStatus.setText("Informe o usuário.");
            txUsuario.grabFocus();
            return;
        }

        if (!novaSenhaGUI.isSenhaValida()) {
            lbStatus.setText("Verifique sua senha.");
            novaSenhaGUI.setLocationRelativeTo(this);
            novaSenhaGUI.setVisible(true);
            return;
        }

        Usuario u = new Usuario();
        if (validaCampoVazio(txCodigo.getText())) {
            u.setCodigo(Integer.parseInt(txCodigo.getText()));
        }
        u.setNome(txNome.getText());
        u.setNivel(cbNivel.getSelectedIndex());
        u.setUsuario(txUsuario.getText());
        u.setSenha(novaSenhaGUI.getSenha());

        index = (u.getCodigo() > 0 ? tabela.getSelectedRow() : -1);

        u = UsuarioController.salvar(u);

        if (u == null) {
            System.out.println("Erro ao registrar: UsuarioGUI.salvarRegistro()");
            return;
        }

        // Se for verdadeiro é alteração
        if (index > -1) {
            usuarios.set(index, u);
        } else {
            usuarios.add(u);
            txCodigo.setText(String.valueOf(u.getCodigo()));
            index++;
        }

        modelo.setNumRows(0);

        for (Usuario us : usuarios) {
            modelo.addRow(new Object[]{
                us.getCodigo(), us.getNome(), us.getUsuario(), "us.getArea()"
            });
        }

        lbStatus.setText("");
        tabela.addRowSelectionInterval(index, index);
    }

    private void excluirRegistro() {
        int linha = -1;
        linha = tabela.getSelectedRow();
        Usuario usuEx;

        if (JOptionPane.showConfirmDialog(this, "Confirma exclusão do registro?")
                != JOptionPane.YES_OPTION) {
            return;
        }

        if (!(linha > -1)) { // se NÃO for maior que -1
            JOptionPane.showMessageDialog(this, "Nenhum registro foi selecionado");
            return;
        }

        int codigo = (int) tabela.getValueAt(linha, 0);
        usuEx = usuarios.get(linha);

        if (usuarioLogado.getCodigo() == usuEx.getCodigo()) {
            JOptionPane.showMessageDialog(this, "Você não pode excluir o seu usuário");
            return;
        }

        if (usuarioLogado.getNivel() > usuEx.getNivel()) {
            JOptionPane.showMessageDialog(this, "Você não tem privilégio pra excluir esse usuário");
            return;
        }

        if (!UsuarioController.excluir(codigo)) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir");
            return;
        }

        usuarios.remove(linha);
        modelo.setNumRows(0);
        this.inclusaoRegistro();

        for (Usuario us : usuarios) {
            modelo.addRow(new Object[]{
                us.getCodigo(), us.getNome(), us.getUsuario(), "us.getArea()"
            });
        }
    }

    private void proximaPagina() {
        if (modelo.getColumnCount() > 0) {
            pagina = Integer.parseInt(lbContaPagina.getText());

            pagina++;
            lbContaPagina.setText(String.valueOf(pagina));
            modelo.setNumRows(0);
//            this.criarTabela();
//            preencherTabela(pagina * qtdRegistros, qtdRegistros);
        }
    }

    private void paginaAnterior() {
        pagina = Integer.parseInt(lbContaPagina.getText());

        if (pagina > 1) {
            pagina--;
            lbContaPagina.setText(String.valueOf(pagina));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelFundo = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        lbCodigo = new javax.swing.JLabel();
        txCodigo = new javax.swing.JTextField();
        lbNome = new javax.swing.JLabel();
        txNome = new javax.swing.JTextField();
        lbNivel = new javax.swing.JLabel();
        lbUsuario = new javax.swing.JLabel();
        txUsuario = new javax.swing.JTextField();
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
        btSenha = new javax.swing.JButton();
        lbStatus = new javax.swing.JLabel();
        cbNivel = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Usuário");
        setResizable(false);

        lbTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("Usuário");

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
        txNome.setText("Nome do Usuário");

        lbNivel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbNivel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNivel.setText("Nível:");

        lbUsuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbUsuario.setText("Usuário:");

        txUsuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txUsuario.setText("Login");

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
                .addComponent(scrollConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
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

        btSenha.setText("Alterar Senha");
        btSenha.setToolTipText("Salvar usuário");
        btSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSenhaActionPerformed(evt);
            }
        });

        lbStatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbStatus.setForeground(java.awt.Color.red);
        lbStatus.setText(" ");

        cbNivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout painelFundoLayout = new javax.swing.GroupLayout(painelFundo);
        painelFundo.setLayout(painelFundoLayout);
        painelFundoLayout.setHorizontalGroup(
            painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFundoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelConsulta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFundoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelFundoLayout.createSequentialGroup()
                        .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelFundoLayout.createSequentialGroup()
                                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbNome, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txNome, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(painelFundoLayout.createSequentialGroup()
                                .addComponent(lbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btSenha)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 258, Short.MAX_VALUE))
                    .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        painelFundoLayout.setVerticalGroup(
            painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFundoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigo)
                    .addComponent(txCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNome)
                    .addComponent(txNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNivel)
                    .addComponent(cbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUsuario)
                    .addComponent(txUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btSalvar)
                        .addComponent(btNovo)
                        .addComponent(btExcluir))
                    .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        excluirRegistro();
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProximoActionPerformed
        paginaAnterior();
    }//GEN-LAST:event_btProximoActionPerformed

    private void btAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnteriorActionPerformed
        proximaPagina();
    }//GEN-LAST:event_btAnteriorActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        salvarRegistro();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        inclusaoRegistro();
    }//GEN-LAST:event_btNovoActionPerformed

    private void btSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSenhaActionPerformed
        novaSenhaGUI.setLocationRelativeTo(this);
        novaSenhaGUI.setVisible(true);
    }//GEN-LAST:event_btSenhaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAnterior;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btProximo;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btSenha;
    private javax.swing.JComboBox cbFiltro;
    private javax.swing.JComboBox cbNivel;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbContaPagina;
    private javax.swing.JLabel lbNivel;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JPanel painelConsulta;
    private javax.swing.JPanel painelFundo;
    private javax.swing.JScrollPane scrollConsulta;
    private javax.swing.JSeparator sp1;
    private javax.swing.JTextField txCodigo;
    private javax.swing.JTextField txNome;
    private javax.swing.JTextField txPesquisa;
    private javax.swing.JTextField txUsuario;
    // End of variables declaration//GEN-END:variables
}
