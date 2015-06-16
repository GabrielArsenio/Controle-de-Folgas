package view;

import controller.Sessao;
import model.Usuario;

/**
 *
 * @author Gabriel
 */
public class JanelaPrincipalGUI extends javax.swing.JFrame {

    private final Usuario usuarioLogado = Sessao.getInstance().getUsuario();
    private UsuarioGUI usuarioGUI;
    private CargoGUI cargoGUI;
    private FuncionarioGUI funcionarioGUI;
    private SetorGUI setorGUI;

    public JanelaPrincipalGUI() {
        initComponents();

        this.setTitle(usuarioLogado.getNome() + " - " + this.getTitle());
        this.setExtendedState(JanelaPrincipalGUI.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelFundo = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        barraMenu = new javax.swing.JMenuBar();
        menuCadastros = new javax.swing.JMenu();
        itemUsuario = new javax.swing.JMenuItem();
        itemFuncionario = new javax.swing.JMenuItem();
        itemCargo = new javax.swing.JMenuItem();
        itemSetor = new javax.swing.JMenuItem();
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

        itemUsuario.setText("Usuário");
        itemUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemUsuarioActionPerformed(evt);
            }
        });
        menuCadastros.add(itemUsuario);

        itemFuncionario.setText("Funcionário");
        itemFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFuncionarioActionPerformed(evt);
            }
        });
        menuCadastros.add(itemFuncionario);

        itemCargo.setText("Cargo");
        itemCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCargoActionPerformed(evt);
            }
        });
        menuCadastros.add(itemCargo);

        itemSetor.setText("Setor");
        itemSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSetorActionPerformed(evt);
            }
        });
        menuCadastros.add(itemSetor);

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
        if (funcionarioGUI != null) {
            funcionarioGUI.dispose();
        }
        funcionarioGUI = new FuncionarioGUI();
        funcionarioGUI.setVisible(true);
    }//GEN-LAST:event_itemFuncionarioActionPerformed

    private void itemCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCargoActionPerformed
        if (cargoGUI != null) {
            cargoGUI.dispose();
        }
        cargoGUI = new CargoGUI();
        cargoGUI.setVisible(true);
    }//GEN-LAST:event_itemCargoActionPerformed

    private void itemUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUsuarioActionPerformed
        if (usuarioGUI != null) {
            usuarioGUI.dispose();
        }
        usuarioGUI = new UsuarioGUI();
        usuarioGUI.setVisible(true);
    }//GEN-LAST:event_itemUsuarioActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
//        int opcao = JOptionPane.showConfirmDialog(null, "Deseja sair?");
//
//        if (opcao == JOptionPane.YES_OPTION) {
//            this.dispose();
//        }
    }//GEN-LAST:event_formWindowClosing

    private void itemSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSetorActionPerformed
        if (setorGUI != null) {
            setorGUI.dispose();
        }
        setorGUI = new SetorGUI();
        setorGUI.setVisible(true);
    }//GEN-LAST:event_itemSetorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JMenuItem itemCargo;
    private javax.swing.JMenuItem itemConsFolga;
    private javax.swing.JMenuItem itemDesbloqFolga;
    private javax.swing.JMenuItem itemFuncionario;
    private javax.swing.JMenuItem itemSetor;
    private javax.swing.JMenuItem itemSolicFolga;
    private javax.swing.JMenuItem itemUsuario;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JMenu menuCadastros;
    private javax.swing.JMenu menuConsultas;
    private javax.swing.JMenu menuFuncoes;
    private javax.swing.JPanel painelFundo;
    // End of variables declaration//GEN-END:variables
}
