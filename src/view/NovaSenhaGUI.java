package view;

import java.awt.Color;
import static util.Validadores.validaCampoVazio;

/**
 *
 * @author Gabriel
 */
public class NovaSenhaGUI extends javax.swing.JFrame {

    private boolean senhaValida = false;

    public NovaSenhaGUI() {
        initComponents();
    }

    private void validarSenha(String senha, String confirmaSenha) {
        if (!validaCampoVazio(senha) && !validaCampoVazio(confirmaSenha)) {
            lbSenha.setForeground(Color.red);
            lbConfirmaSenha.setForeground(Color.red);
            txSenha.grabFocus();
        } else if (!senha.equals(confirmaSenha)) {
            lbSenha.setForeground(Color.black);
            lbConfirmaSenha.setForeground(Color.red);
            txConfirmaSenha.grabFocus();
        } else {
            lbSenha.setForeground(Color.black);
            lbConfirmaSenha.setForeground(Color.black);
            this.setVisible(false);
            senhaValida = true;
        }
    }

    public boolean isSenhaValida() {
        return senhaValida;
    }

    public String getSenha() {
        return txSenha.getText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelSenha = new javax.swing.JPanel();
        lbSenha = new javax.swing.JLabel();
        txSenha = new javax.swing.JPasswordField();
        lbConfirmaSenha = new javax.swing.JLabel();
        txConfirmaSenha = new javax.swing.JPasswordField();
        btCancela = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nova Senha");
        setResizable(false);

        lbSenha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbSenha.setText("Senha");

        txSenha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txSenhaActionPerformed(evt);
            }
        });

        lbConfirmaSenha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbConfirmaSenha.setText("Confirmar Senha");

        txConfirmaSenha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txConfirmaSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txConfirmaSenhaActionPerformed(evt);
            }
        });

        btCancela.setText("Cancelar");
        btCancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelaActionPerformed(evt);
            }
        });

        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelSenhaLayout = new javax.swing.GroupLayout(painelSenha);
        painelSenha.setLayout(painelSenhaLayout);
        painelSenhaLayout.setHorizontalGroup(
            painelSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelSenhaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txSenha)
                    .addComponent(lbConfirmaSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                    .addComponent(txConfirmaSenha)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelSenhaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancela)))
                .addContainerGap())
        );
        painelSenhaLayout.setVerticalGroup(
            painelSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelSenhaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbConfirmaSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCancela)
                    .addComponent(btSalvar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txSenhaActionPerformed
        txConfirmaSenha.grabFocus();
    }//GEN-LAST:event_txSenhaActionPerformed

    private void txConfirmaSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txConfirmaSenhaActionPerformed
        btSalvar.doClick();
    }//GEN-LAST:event_txConfirmaSenhaActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        validarSenha(txSenha.getText(), txConfirmaSenha.getText());
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelaActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btCancelaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancela;
    private javax.swing.JButton btSalvar;
    private javax.swing.JLabel lbConfirmaSenha;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JPanel painelSenha;
    private javax.swing.JPasswordField txConfirmaSenha;
    private javax.swing.JPasswordField txSenha;
    // End of variables declaration//GEN-END:variables
}
