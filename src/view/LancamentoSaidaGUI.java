package view;

import controller.FuncionarioController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Folga;
import model.Funcionario;
import static util.Validadores.*;

/**
 *
 * @author Gabriel
 */
public class LancamentoSaidaGUI extends javax.swing.JFrame {

    public LancamentoSaidaGUI() {
        this.initComponents();
        this.setLocationRelativeTo(null);
        this.novoLancamento();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitulo = new javax.swing.JLabel();
        lbData = new javax.swing.JLabel();
        lbHoraManha = new javax.swing.JLabel();
        lbHoraTarde = new javax.swing.JLabel();
        lbMotivo = new javax.swing.JLabel();
        lbTipo = new javax.swing.JLabel();
        lbFuncionario = new javax.swing.JLabel();
        txFuncionarioCodigo = new javax.swing.JTextField();
        txFuncionarioNome = new javax.swing.JTextField();
        btFuncionario = new javax.swing.JButton();
        cbTipo = new javax.swing.JComboBox();
        txDtInicio = new javax.swing.JTextField();
        lbAte = new javax.swing.JLabel();
        txDtFim = new javax.swing.JTextField();
        txHrManhaInicio = new javax.swing.JTextField();
        lbAsManha = new javax.swing.JLabel();
        txHrManhaFim = new javax.swing.JTextField();
        txHrTardeInicio = new javax.swing.JTextField();
        lbAsTarde = new javax.swing.JLabel();
        txHrTardeFim = new javax.swing.JTextField();
        scrollMotivo = new javax.swing.JScrollPane();
        txMotivo = new javax.swing.JEditorPane();
        sp1 = new javax.swing.JSeparator();
        btCancelar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btConsulta = new javax.swing.JButton();
        btConsulta1 = new javax.swing.JButton();
        ckDiaTodo = new javax.swing.JCheckBox();
        lbStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Solicitar Saída");
        setResizable(false);

        lbTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("Solicitar Saída");

        lbData.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbData.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbData.setText("Data Solicitação:");

        lbHoraManha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbHoraManha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHoraManha.setText("Horário Manhã:");

        lbHoraTarde.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbHoraTarde.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHoraTarde.setText("Horário Tarde:");

        lbMotivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbMotivo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbMotivo.setText("Motivo:");

        lbTipo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbTipo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTipo.setText("Tipo:");

        lbFuncionario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbFuncionario.setText("Funcionário:");

        txFuncionarioCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txFuncionarioCodigo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txFuncionarioCodigo.setText("1");
        txFuncionarioCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txFuncionarioCodigoActionPerformed(evt);
            }
        });

        txFuncionarioNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txFuncionarioNome.setText("Nome do Funcionário");
        txFuncionarioNome.setEnabled(false);

        btFuncionario.setText("Buscar");
        btFuncionario.setToolTipText("Buscar área");
        btFuncionario.setFocusable(false);

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Folga", "Férias", "Saída" }));

        txDtInicio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txDtInicio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txDtInicio.setText("99/99/9999");

        lbAte.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbAte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAte.setText("Até");

        txDtFim.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txDtFim.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txDtFim.setText("99/99/9999");

        txHrManhaInicio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txHrManhaInicio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txHrManhaInicio.setText("00:00");

        lbAsManha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbAsManha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAsManha.setText("às");

        txHrManhaFim.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txHrManhaFim.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txHrManhaFim.setText("00:00");

        txHrTardeInicio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txHrTardeInicio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txHrTardeInicio.setText("00:00");

        lbAsTarde.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbAsTarde.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAsTarde.setText("às");

        txHrTardeFim.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txHrTardeFim.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txHrTardeFim.setText("00:00");

        scrollMotivo.setViewportView(txMotivo);

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btConsulta.setText("Consulta");

        btConsulta1.setText("Banco H.");

        ckDiaTodo.setText("O dia todo");
        ckDiaTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckDiaTodoActionPerformed(evt);
            }
        });

        lbStatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbStatus.setForeground(java.awt.Color.red);
        lbStatus.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sp1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbHoraManha, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txHrManhaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbAsManha, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txHrManhaFim, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ckDiaTodo))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbHoraTarde, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txHrTardeInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbAsTarde, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txHrTardeFim, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txFuncionarioCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txFuncionarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btFuncionario))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lbData)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txDtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbAte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txDtFim, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btConsulta1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btConsulta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancelar))
                    .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbStatus)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFuncionario)
                    .addComponent(txFuncionarioCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txFuncionarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFuncionario))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTipo)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbData)
                    .addComponent(txDtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAte)
                    .addComponent(txDtFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbHoraManha)
                    .addComponent(txHrManhaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAsManha)
                    .addComponent(txHrManhaFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckDiaTodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbHoraTarde)
                    .addComponent(txHrTardeInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAsTarde)
                    .addComponent(txHrTardeFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollMotivo, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(lbMotivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btCancelar)
                                .addComponent(btSalvar))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btConsulta)
                                .addComponent(btConsulta1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void novoLancamento() {
        txFuncionarioCodigo.setText(null);
        txFuncionarioNome.setText(null);
        txDtInicio.setText(null);
        txDtFim.setText(null);
        txHrManhaInicio.setText(null);
        txHrManhaFim.setText(null);
        txHrTardeInicio.setText(null);
        txHrTardeFim.setText(null);
        ckDiaTodo.setSelected(false);
        txMotivo.setText(null);
    }

    private void ckDiaTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckDiaTodoActionPerformed
        if (ckDiaTodo.isSelected()) {
            txHrManhaInicio.setEnabled(false);
            txHrManhaFim.setEnabled(false);
            txHrTardeInicio.setEnabled(false);
            txHrTardeFim.setEnabled(false);
        } else {
            txHrManhaInicio.setEnabled(true);
            txHrManhaFim.setEnabled(true);
            txHrTardeInicio.setEnabled(true);
            txHrTardeFim.setEnabled(true);
        }
    }//GEN-LAST:event_ckDiaTodoActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        Funcionario funcionario;
        String codigoFunc = txFuncionarioCodigo.getText();
        char tipo;
        String dtIni = txDtInicio.getText();
        String dtFim = txDtFim.getText();
        Date dIni = null;
        Date dFim = null;
        String hrManhaInicio = txHrManhaInicio.getText();
        String hrManhaFim = txHrManhaFim.getText();
        String hrTardeInicio = txHrTardeInicio.getText();
        String hrTardeFim = txHrTardeFim.getText();
        String motivo = txMotivo.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (!validaCampoVazio(codigoFunc)) {
            lbStatus.setText("Informe o funcionário");
            txFuncionarioCodigo.grabFocus();
            return;
        }

        if (!validaInt(codigoFunc)) {
            lbStatus.setText("Informe o funcionário corretamente");
            txFuncionarioCodigo.grabFocus();
            return;
        }

        funcionario = FuncionarioController.buscarPorId(Integer.parseInt(codigoFunc));

        if (funcionario == null) {
            lbStatus.setText("Funcionário não encontrado");
            txFuncionarioCodigo.grabFocus();
            return;
        }

        switch (cbTipo.getSelectedIndex()) {
            case 0:
                tipo = 'F';
                break;
            case 1:
                tipo = 'E';
                break;
            case 2:
                tipo = 'S';
                break;
            default:
                tipo = 'F';
        }

        if (!validaCampoVazio(dtIni)) {
            lbStatus.setText("Informe a data inicial");
            txDtInicio.grabFocus();
            return;
        }

        if (!validaData(dtIni)) {
            lbStatus.setText("Informe a data corretamnete");
            txDtInicio.grabFocus();
            return;
        }

        try {
            dIni = sdf.parse(dtIni);
        } catch (ParseException ex) {
        }

        if (!validaCampoVazio(dtFim)) {
            lbStatus.setText("Informe a data final");
            txDtFim.grabFocus();
            return;
        }

        if (!validaData(dtFim)) {
            lbStatus.setText("Informe a data corretamnete");
            txDtFim.grabFocus();
            return;
        }

        try {
            dFim = sdf.parse(dtFim);
        } catch (ParseException ex) {
        }

        if (!ckDiaTodo.isSelected()) {
            if (!validaCampoVazio(hrManhaInicio)) {
                lbStatus.setText("Informe a hora inicial da manhã");
                txHrManhaInicio.grabFocus();
                return;
            }

            if (!validaCampoVazio(hrManhaFim)) {
                lbStatus.setText("Informe a hora final da manhã");
                txHrManhaFim.grabFocus();
                return;
            }

            if (!validaCampoVazio(hrTardeInicio)) {
                lbStatus.setText("Informe a hora inicial da tarde");
                txHrTardeInicio.grabFocus();
                return;
            }

            if (!validaCampoVazio(hrTardeFim)) {
                lbStatus.setText("Informe a hora final da tarde");
                txHrTardeFim.grabFocus();
                return;
            }
        }

        if (!validaCampoVazio(motivo)) {
            lbStatus.setText("Informe o motivo");
            txMotivo.grabFocus();
            return;
        }

        sdf = new SimpleDateFormat("HH:mm");
        Folga folga = new Folga();
        folga.setFuncionario(funcionario);
        folga.setTipo(tipo);
        folga.setDtInicio(dIni);
        folga.setDtFim(dFim);
        try {
            folga.getHrManhaInicio().setTime(sdf.parse(hrManhaInicio).getTime());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao converter hora");
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void txFuncionarioCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txFuncionarioCodigoActionPerformed
        Funcionario funcionario = null;
        try {
            funcionario = FuncionarioController.buscarPorId(
                    Integer.parseInt(txFuncionarioCodigo.getText()));
        } catch (NumberFormatException numberFormatException) {
        }

        if (funcionario != null) {
            txFuncionarioNome.setText(funcionario.getNome());
            funcionario = null;
        } else {
            txFuncionarioNome.setText(null);
        }
    }//GEN-LAST:event_txFuncionarioCodigoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btConsulta;
    private javax.swing.JButton btConsulta1;
    private javax.swing.JButton btFuncionario;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox cbTipo;
    private javax.swing.JCheckBox ckDiaTodo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbAsManha;
    private javax.swing.JLabel lbAsTarde;
    private javax.swing.JLabel lbAte;
    private javax.swing.JLabel lbData;
    private javax.swing.JLabel lbFuncionario;
    private javax.swing.JLabel lbHoraManha;
    private javax.swing.JLabel lbHoraTarde;
    private javax.swing.JLabel lbMotivo;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTipo;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JScrollPane scrollMotivo;
    private javax.swing.JSeparator sp1;
    private javax.swing.JTextField txDtFim;
    private javax.swing.JTextField txDtInicio;
    private javax.swing.JTextField txFuncionarioCodigo;
    private javax.swing.JTextField txFuncionarioNome;
    private javax.swing.JTextField txHrManhaFim;
    private javax.swing.JTextField txHrManhaInicio;
    private javax.swing.JTextField txHrTardeFim;
    private javax.swing.JTextField txHrTardeInicio;
    private javax.swing.JEditorPane txMotivo;
    // End of variables declaration//GEN-END:variables
}
