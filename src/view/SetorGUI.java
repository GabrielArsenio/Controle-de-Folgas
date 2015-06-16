package view;

import controller.CargoController;
import controller.FuncionarioController;
import controller.Sessao;
import controller.SetorController;
import controller.UsuarioController;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.Cargo;
import model.Funcionario;
import model.Setor;
import model.Usuario;
import static util.Validadores.*;

/**
 *
 * @author Gabriel
 */
public class SetorGUI extends javax.swing.JFrame {

    private final Usuario usuarioLogado = Sessao.getInstance().getUsuario();
    private final int qtdRegistros = 50;
    private char modo; // I - Inclusao, M - Manutenção/Alteração
    private List<Setor> setores;

    private DefaultTableModel modelo;
    private JTable tabela;

    public SetorGUI() {
        initComponents();
        setLocationRelativeTo(null);
        this.criarTabela();
    }

    private void criarTabela() {
        modelo = new DefaultTableModel();
        tabela = new JTable(modelo);

        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        modelo.addColumn("Coordenador");

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
    }

    /* Busca no banco e adiciona as linhas */
    private void preencherTabela(int min, int max) {
        setores = SetorController.listarTodos(min, max);

        for (Setor s : setores) {
            try {
                s.setCoordenador(FuncionarioController.buscarPorId(s.getCoordenador().getCodigo()));
            } catch (NullPointerException e) {
            }
            modelo.addRow(new Object[]{
                s.getCodigo(), s.getNome(), s.getCoordenador().getNome()
            });
        }
    }

    private void inclusaoRegistro() {
        modo = 'I';
        lbTitulo.setText("Setor - Inclusão");
        lbStatus.setText("");
        txCodigo.setText(null);
        txNome.setText(null);
        txDescricao.setText(null);
        txCoordenadorCodigo.setText(null);
        txCoordenadorNome.setText(null);
        txNome.grabFocus();
    }

    private void manutencaoRegistro(java.awt.event.MouseEvent evt) {
        Setor setorSelecionado = setores.get(tabela.getSelectedRow());

        modo = 'M';
        lbTitulo.setText("Setor - Manutenção");
        lbStatus.setText("");
        txCodigo.setText(String.valueOf(setorSelecionado.getCodigo()));
        txNome.setText(setorSelecionado.getNome());
        txDescricao.setText(setorSelecionado.getDescricao());

        if (setorSelecionado.getCoordenador() != null) {
            txCoordenadorCodigo.setText(String.valueOf(setorSelecionado.getCoordenador().getCodigo()));
            txCoordenadorNome.setText(setorSelecionado.getCoordenador().getNome());
        }
    }

    private void salvarRegistro() {
        int index;
        Funcionario coordenador;

        if (usuarioLogado.getNivel() > 1) {
            lbStatus.setText("Você não tem privilégio para manipular setores");
            return;
        }

        if (!validaCampoVazio(txNome.getText())) {
            lbStatus.setText("Informe o nome");
            txNome.grabFocus();
            return;
        }

        if (validaCampoVazio(txCoordenadorCodigo.getText())) {
            if (!validaInt(txCoordenadorCodigo.getText())) {
                lbStatus.setText("Informe o coordenador corretamente");
                return;
            }

            coordenador = FuncionarioController.buscarPorId(Integer.parseInt(txCoordenadorCodigo.getText()));
            if (coordenador == null) {
                lbStatus.setText("Setor não encontrado");
                return;
            }

            coordenador.setUsuario(UsuarioController.buscarPorId(coordenador.getUsuario().getCodigo()));
            if (coordenador.getUsuario().getNivel() > 1) {
                lbStatus.setText("Coordenador inválido, favor verificar login de acesso");
                return;
            }
        }

        Setor s = new Setor();
        if (validaCampoVazio(txCodigo.getText())) {
            s.setCodigo(Integer.parseInt(txCodigo.getText()));
        }
        s.setNome(txNome.getText());
        s.setDescricao(txDescricao.getText());

        index = (s.getCodigo() > 0 ? tabela.getSelectedRow() : -1);

        s = SetorController.salvar(s);

        if (s == null) {
            System.out.println("Erro ao registrar: SetorGUI.salvarRegistro()");
            return;
        }

        // Se for verdadeiro é alteração
        if (index > -1) {
            setores.set(index, s);
        } else {
            setores.add(s);
            txCodigo.setText(String.valueOf(s.getCodigo()));
            index++;
        }

        modelo.setNumRows(0);

        for (Setor ss : setores) {
            try {
                s.setCoordenador(FuncionarioController.buscarPorId(s.getCoordenador().getCodigo()));
            } catch (NullPointerException e) {
            }
            modelo.addRow(new Object[]{
                s.getCodigo(), s.getNome(), s.getCoordenador().getNome()
            });
        }

        lbStatus.setText("");
        tabela.addRowSelectionInterval(index, index);
    }

    private void excluirRegistro() {
        int linha = -1;
        linha = tabela.getSelectedRow();
        Setor setorEx;

        if (usuarioLogado.getNivel() > 1) {
            JOptionPane.showMessageDialog(this, "Você não tem privilégio para manipular setores");
            return;
        }

        if (!(linha > -1)) { // se NÃO for maior que -1
            JOptionPane.showMessageDialog(this, "Nenhum registro foi selecionado");
            return;
        }

        if (JOptionPane.showConfirmDialog(this, "Confirma exclusão do registro?")
                != JOptionPane.YES_OPTION) {
            return;
        }

        int codigo = (int) tabela.getValueAt(linha, 0);
        setorEx = setores.get(linha);

        if (!CargoController.excluir(codigo)) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir, possivelmente há funcionários relacionados a este setor.");
            return;
        }

        setores.remove(linha);
        modelo.setNumRows(0);
        this.inclusaoRegistro();

        for (Setor s : setores) {
            modelo.addRow(new Object[]{
                s.getCodigo(), s.getNome(), s.getDescricao()
            });
        }
    }

//    private void proximaPagina() {
//        if (modelo.getColumnCount() > 0) {
//            pagina = Integer.parseInt(lbContaPagina.getText());
//
//            pagina++;
//            lbContaPagina.setText(String.valueOf(pagina));
//            modelo.setNumRows(0);
////            this.criarTabela();
////            preencherTabela(pagina * qtdRegistros, qtdRegistros);
//        }
//    }
//
//    private void paginaAnterior() {
//        pagina = Integer.parseInt(lbContaPagina.getText());
//
//        if (pagina > 1) {
//            pagina--;
//            lbContaPagina.setText(String.valueOf(pagina));
//        }
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbFiltro = new javax.swing.JComboBox();
        txPesquisa = new javax.swing.JTextField();
        btProximo = new javax.swing.JButton();
        lbContaPagina = new javax.swing.JLabel();
        btAnterior = new javax.swing.JButton();
        painelFundo = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        lbCodigo = new javax.swing.JLabel();
        txCodigo = new javax.swing.JTextField();
        lbNome = new javax.swing.JLabel();
        txNome = new javax.swing.JTextField();
        lbDescricao = new javax.swing.JLabel();
        txDescricao = new javax.swing.JTextField();
        painelConsulta = new javax.swing.JPanel();
        scrollConsulta = new javax.swing.JScrollPane();
        btSalvar = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        sp1 = new javax.swing.JSeparator();
        lbStatus = new javax.swing.JLabel();
        lbCoordenador = new javax.swing.JLabel();
        txCoordenadorCodigo = new javax.swing.JTextField();
        txCoordenadorNome = new javax.swing.JTextField();
        btCoordenador = new javax.swing.JButton();

        cbFiltro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Código", "Nome", "Usuário", "Área" }));
        cbFiltro.setEnabled(false);

        txPesquisa.setEnabled(false);

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

        btAnterior.setText(">");
        btAnterior.setToolTipText("Próxima página");
        btAnterior.setEnabled(false);
        btAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnteriorActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Usuário");
        setResizable(false);

        lbTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("Setor / Área");

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
        txNome.setText("Nome do Cargo");

        lbDescricao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbDescricao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDescricao.setText("Descrição:");

        txDescricao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txDescricao.setText("Descricao");

        painelConsulta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout painelConsultaLayout = new javax.swing.GroupLayout(painelConsulta);
        painelConsulta.setLayout(painelConsultaLayout);
        painelConsultaLayout.setHorizontalGroup(
            painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollConsulta)
        );
        painelConsultaLayout.setVerticalGroup(
            painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollConsulta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
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

        lbStatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbStatus.setForeground(java.awt.Color.red);
        lbStatus.setText(" ");

        lbCoordenador.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbCoordenador.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCoordenador.setText("Coordenador:");

        txCoordenadorCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txCoordenadorCodigo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txCoordenadorCodigo.setText("1");

        txCoordenadorNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txCoordenadorNome.setText("Login de Acesso do Funcionário");
        txCoordenadorNome.setEnabled(false);

        btCoordenador.setText("Buscar");
        btCoordenador.setToolTipText("Buscar área");
        btCoordenador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCoordenadorActionPerformed(evt);
            }
        });

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
                        .addGap(0, 542, Short.MAX_VALUE)
                        .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(painelFundoLayout.createSequentialGroup()
                        .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(lbCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelFundoLayout.createSequentialGroup()
                                .addComponent(txCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(painelFundoLayout.createSequentialGroup()
                                .addComponent(txNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbCoordenador, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txCoordenadorCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txCoordenadorNome, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btCoordenador))
                            .addComponent(txDescricao))))
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
                    .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCoordenador)
                        .addComponent(txCoordenadorCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txCoordenadorNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btCoordenador)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDescricao)
                    .addComponent(txDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
//        paginaAnterior();
    }//GEN-LAST:event_btProximoActionPerformed

    private void btAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnteriorActionPerformed
//        proximaPagina();
    }//GEN-LAST:event_btAnteriorActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        salvarRegistro();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        inclusaoRegistro();
    }//GEN-LAST:event_btNovoActionPerformed

    private void btCoordenadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCoordenadorActionPerformed
        
    }//GEN-LAST:event_btCoordenadorActionPerformed

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
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel painelConsulta;
    private javax.swing.JPanel painelFundo;
    private javax.swing.JScrollPane scrollConsulta;
    private javax.swing.JSeparator sp1;
    private javax.swing.JTextField txCodigo;
    private javax.swing.JTextField txCoordenadorCodigo;
    private javax.swing.JTextField txCoordenadorNome;
    private javax.swing.JTextField txDescricao;
    private javax.swing.JTextField txNome;
    private javax.swing.JTextField txPesquisa;
    // End of variables declaration//GEN-END:variables
}
