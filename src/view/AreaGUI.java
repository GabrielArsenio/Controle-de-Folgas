package view;

/**
 *
 * @author Gabriel
 */
public class AreaGUI extends javax.swing.JFrame {

    public AreaGUI() {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AreaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelFundo = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        lbCodigo = new javax.swing.JLabel();
        txCodigo = new javax.swing.JTextField();
        lbDescricao = new javax.swing.JLabel();
        txDescricao = new javax.swing.JTextField();
        lbCoordenador = new javax.swing.JLabel();
        txCodigoCoordenador = new javax.swing.JTextField();
        txNomeCoordenador = new javax.swing.JTextField();
        btCoordenador = new javax.swing.JButton();
        painelConsulta = new javax.swing.JPanel();
        scrollConsulta = new javax.swing.JScrollPane();
        btAnterior = new javax.swing.JButton();
        btProximo = new javax.swing.JButton();
        lbContaPagina = new javax.swing.JLabel();
        cbFiltro = new javax.swing.JComboBox();
        txPesquisa = new javax.swing.JTextField();
        btSalvar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        sp1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Área");

        lbTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("Área");

        lbCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCodigo.setText("Código:");

        txCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txCodigo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txCodigo.setText("1");
        txCodigo.setEnabled(false);

        lbDescricao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbDescricao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDescricao.setText("Descrição:");

        txDescricao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txDescricao.setText("Descrição da Área");

        lbCoordenador.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbCoordenador.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCoordenador.setText("Coordenador:");

        txCodigoCoordenador.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txCodigoCoordenador.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txCodigoCoordenador.setText("1");

        txNomeCoordenador.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txNomeCoordenador.setText("Nome do Coordenador");
        txNomeCoordenador.setEnabled(false);

        btCoordenador.setText("Buscar");
        btCoordenador.setToolTipText("Buscar área");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 512, Short.MAX_VALUE)
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
                .addComponent(scrollConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
        );

        btSalvar.setText("Salvar");
        btSalvar.setToolTipText("Salvar usuário");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btExcluir.setText("Excluir");
        btExcluir.setToolTipText("Excluir usuário");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        btNovo.setText("Novo");
        btNovo.setToolTipText("Novo usuário");
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });

        sp1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout painelFundoLayout = new javax.swing.GroupLayout(painelFundo);
        painelFundo.setLayout(painelFundoLayout);
        painelFundoLayout.setHorizontalGroup(
            painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFundoLayout.createSequentialGroup()
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFundoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE))
                    .addGroup(painelFundoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(painelFundoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelFundoLayout.createSequentialGroup()
                                .addComponent(lbDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbCoordenador, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txCodigoCoordenador, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txNomeCoordenador, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btCoordenador)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(painelConsulta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addGap(18, 18, 18)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigo)
                    .addComponent(txCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDescricao)
                    .addComponent(txDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCoordenador)
                    .addComponent(txCodigoCoordenador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNomeCoordenador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCoordenador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btSalvar)
                        .addComponent(btNovo)
                        .addComponent(btExcluir))
                    .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
//        inclusaoRegistro();
    }//GEN-LAST:event_btNovoActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
//        excluirRegistro();
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
//        salvarRegistro();
    }//GEN-LAST:event_btSalvarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAnterior;
    private javax.swing.JButton btCoordenador;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btProximo;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox cbFiltro;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbContaPagina;
    private javax.swing.JLabel lbCoordenador;
    private javax.swing.JLabel lbDescricao;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel painelConsulta;
    private javax.swing.JPanel painelFundo;
    private javax.swing.JScrollPane scrollConsulta;
    private javax.swing.JSeparator sp1;
    private javax.swing.JTextField txCodigo;
    private javax.swing.JTextField txCodigoCoordenador;
    private javax.swing.JTextField txDescricao;
    private javax.swing.JTextField txNomeCoordenador;
    private javax.swing.JTextField txPesquisa;
    // End of variables declaration//GEN-END:variables
}
