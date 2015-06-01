package view;

import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class JanelaPrincipalGUI extends javax.swing.JFrame {

    private FuncionarioGUI janelaFuncionario;
    private AreaGUI janelaArea;
    private UsuarioGUI janelaUsuario;

    public JanelaPrincipalGUI() {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        initComponents();

        this.setExtendedState(JanelaPrincipalGUI.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelFundo = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        barraMenu = new javax.swing.JMenuBar();
        menuCadastros = new javax.swing.JMenu();
        itemArea = new javax.swing.JMenuItem();
        itemFuncionario = new javax.swing.JMenuItem();
        itemUsuario = new javax.swing.JMenuItem();
        menuConsultas = new javax.swing.JMenu();
        itemConsFolga = new javax.swing.JMenuItem();
        menuFuncoes = new javax.swing.JMenu();
        itemDesbloqFolga = new javax.swing.JMenuItem();
        itemSolicFolga = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controle de Folgas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lbTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("Controle de Folgas");

        javax.swing.GroupLayout painelFundoLayout = new javax.swing.GroupLayout(painelFundo);
        painelFundo.setLayout(painelFundoLayout);
        painelFundoLayout.setHorizontalGroup(
            painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFundoLayout.createSequentialGroup()
                .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                .addContainerGap())
        );
        painelFundoLayout.setVerticalGroup(
            painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFundoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitulo)
                .addContainerGap(539, Short.MAX_VALUE))
        );

        menuCadastros.setText("Cadastros");

        itemArea.setText("Área");
        itemArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAreaActionPerformed(evt);
            }
        });
        menuCadastros.add(itemArea);

        itemFuncionario.setText("Funcionário");
        itemFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFuncionarioActionPerformed(evt);
            }
        });
        menuCadastros.add(itemFuncionario);

        itemUsuario.setText("Usuário");
        itemUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemUsuarioActionPerformed(evt);
            }
        });
        menuCadastros.add(itemUsuario);

        barraMenu.add(menuCadastros);

        menuConsultas.setText("Consultas");

        itemConsFolga.setText("Consultar Folgas");
        menuConsultas.add(itemConsFolga);

        barraMenu.add(menuConsultas);

        menuFuncoes.setText("Funções");

        itemDesbloqFolga.setText("Desbloquear Folga");
        menuFuncoes.add(itemDesbloqFolga);

        itemSolicFolga.setText("Solicitar Folga");
        menuFuncoes.add(itemSolicFolga);

        barraMenu.add(menuFuncoes);

        setJMenuBar(barraMenu);

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

    private void itemFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFuncionarioActionPerformed
        if (janelaFuncionario != null) {
            janelaFuncionario.dispose();
        }
        janelaFuncionario = new FuncionarioGUI();
        janelaFuncionario.setVisible(true);
    }//GEN-LAST:event_itemFuncionarioActionPerformed

    private void itemAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAreaActionPerformed
        if (janelaArea != null) {
            janelaArea.dispose();
        }
        janelaArea = new AreaGUI();
        janelaArea.setVisible(true);
    }//GEN-LAST:event_itemAreaActionPerformed

    private void itemUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUsuarioActionPerformed
        if (janelaUsuario != null) {
            janelaUsuario.dispose();
        }
        janelaUsuario = new UsuarioGUI();
        janelaUsuario.setVisible(true);
    }//GEN-LAST:event_itemUsuarioActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja sair?");

        if (opcao == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JMenuItem itemArea;
    private javax.swing.JMenuItem itemConsFolga;
    private javax.swing.JMenuItem itemDesbloqFolga;
    private javax.swing.JMenuItem itemFuncionario;
    private javax.swing.JMenuItem itemSolicFolga;
    private javax.swing.JMenuItem itemUsuario;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JMenu menuCadastros;
    private javax.swing.JMenu menuConsultas;
    private javax.swing.JMenu menuFuncoes;
    private javax.swing.JPanel painelFundo;
    // End of variables declaration//GEN-END:variables
}
