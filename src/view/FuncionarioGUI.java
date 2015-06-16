package view;

import controller.CargoController;
import controller.FuncionarioController;
import controller.Sessao;
import controller.SetorController;
import controller.UsuarioController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class FuncionarioGUI extends javax.swing.JFrame {

    private final Usuario usuarioLogado = Sessao.getInstance().getUsuario();
    private final int qtdRegistros = 50;
    private char modo; // I - Inclusao, M - Manutenção/Alteração
    private List<Funcionario> funcionarios;

    private DefaultTableModel modelo;
    private JTable tabela;

    public FuncionarioGUI() {
        initComponents();
        setLocationRelativeTo(null);
        this.criarTabela();
    }

    private void criarTabela() {
        modelo = new DefaultTableModel();
        tabela = new JTable(modelo);

        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        modelo.addColumn("E-mail");
        modelo.addColumn("Cargo");

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
        funcionarios = FuncionarioController.listarTodos(min, max);

        for (Funcionario f : funcionarios) {
            try {
                f.setSetor(SetorController.buscarPorId(f.getSetor().getCodigo()));
            } catch (NullPointerException e) {
            }
            try {
                f.setCargo(CargoController.buscarPorId(f.getCargo().getCodigo()));
            } catch (NullPointerException e) {
            }
            try {
                f.setUsuario(UsuarioController.buscarUsuario(f.getUsuario().getUsuario()));
            } catch (NullPointerException e) {
            }
            modelo.addRow(new Object[]{
                f.getCodigo(), f.getNome(), f.getEmail(), f.getCargo().getNome()
            });
        }
    }

    private void inclusaoRegistro() {
        modo = 'I';
        lbTitulo.setText("Funcionário - Inclusão");
        lbStatus.setText("");
        txCodigo.setText(null);
        txNome.setText(null);
        bgSexo.clearSelection();
        txTelefone.setText(null);
        txEmail.setText(null);
        txDtNascimento.setText(null);
        txDtEfetiva.setText(null);
        txSetorCodigo.setText(null);
        txSetorDescricao.setText(null);
        txCargoCodigo.setText(null);
        txCargoDescricao.setText(null);
        txUsuarioCodigo.setText(null);
        txUsuarioNome.setText(null);
        txNome.grabFocus();
    }

    private void manutencaoRegistro(java.awt.event.MouseEvent evt) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Funcionario funcSelecionado = funcionarios.get(tabela.getSelectedRow());

        modo = 'M';
        lbTitulo.setText("Funcionário - Manutenção");
        lbStatus.setText("");

        txCodigo.setText(String.valueOf(funcSelecionado.getCodigo()));
        txNome.setText(funcSelecionado.getNome());
        if (funcSelecionado.getCharSexo() == 'F') {
            rbFeminino.setSelected(true);
        } else {
            rbMasculino.setSelected(true);
        }
        txTelefone.setText(funcSelecionado.getTelefone());
        txEmail.setText(funcSelecionado.getEmail());
        txDtNascimento.setText(sdf.format(funcSelecionado.getDataNascimento()));
        txDtEfetiva.setText(sdf.format(funcSelecionado.getDataEfetivo()));

        if (funcSelecionado.getSetor() != null) {
            txSetorCodigo.setText(String.valueOf(funcSelecionado.getSetor().getCodigo()));
            txSetorDescricao.setText(funcSelecionado.getSetor().getNome());
        }

        if (funcSelecionado.getCargo() != null) {
            txCargoCodigo.setText(String.valueOf(funcSelecionado.getCargo().getCodigo()));
            txCargoDescricao.setText(funcSelecionado.getCargo().getNome());
        }

        if (funcSelecionado.getUsuario() != null) {
            txUsuarioCodigo.setText(funcSelecionado.getUsuario().getUsuario());
            txUsuarioNome.setText(funcSelecionado.getUsuario().getNome());
        }
    }

    private void salvarRegistro() {
        int index;
        Setor setor = null;
        Cargo cargo = null;
        Usuario usuario = null;

        if (usuarioLogado.getNivel() > 2) {
            lbStatus.setText("Você não tem privilégio para manipular cargos");
            return;
        }

        if (!validaCampoVazio(txNome.getText())) {
            lbStatus.setText("Informe o nome");
            txNome.grabFocus();
            return;
        }
        if (!rbMasculino.isSelected() && !rbFeminino.isSelected()) {
            lbStatus.setText("Informe o sexo");
            return;
        }

        if (!validaData(txDtNascimento.getText()) || !validaData(txDtNascimento.getText())) {
            lbStatus.setText("Informe a data de nascimento");
            txDtNascimento.grabFocus();
            return;
        }

        if (!validaData(txDtEfetiva.getText()) || !validaData(txDtEfetiva.getText())) {
            lbStatus.setText("Informe a data que foi efetivado");
            txDtEfetiva.grabFocus();
            return;
        }

        if (validaCampoVazio(txSetorCodigo.getText())) {
            if (!validaInt(txSetorCodigo.getText())) {
                lbStatus.setText("Informe o setor corretamente");
                return;
            }

            setor = SetorController.buscarPorId(Integer.parseInt(txSetorCodigo.getText()));
            if (setor == null) {
                lbStatus.setText("Setor não encontrado");
                return;
            }
        }

        if (validaCampoVazio(txCargoCodigo.getText())) {
            if (!validaInt(txCargoCodigo.getText())) {
                lbStatus.setText("Informe o cargo corretamente");
                return;
            }

            cargo = CargoController.buscarPorId(Integer.parseInt(txCargoCodigo.getText()));
            if (cargo == null) {
                lbStatus.setText("Cargo não encontrado");
                return;
            }
        }

        if (validaCampoVazio(txUsuarioCodigo.getText())) {
            usuario = UsuarioController.buscarUsuario(txUsuarioCodigo.getText());
            if (usuario == null) {
                lbStatus.setText("Usuário não encontrado");
                return;
            }
        }

        Funcionario f = new Funcionario();
        if (validaCampoVazio(txCodigo.getText())) {
            f.setCodigo(Integer.parseInt(txCodigo.getText()));
        }
        f.setNome(txNome.getText());
        if (rbFeminino.isSelected()) {
            f.setSexo('F');
        } else {
            f.setSexo('M');
        }
        f.setTelefone(txTelefone.getText());
        f.setEmail(txEmail.getText());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        try {
            f.setDataNascimento(sdf.parse(txDtNascimento.getText()));
            f.setDataEfetivo(sdf.parse(txDtEfetiva.getText()));
        } catch (ParseException parseException) {
            System.out.println("Erro ao converter data: FuncionarioGUI().salvarRegistro()");
        }
        f.setSetor(setor);
        f.setCargo(cargo);
        f.setUsuario(usuario);

        index = (f.getCodigo() > 0 ? tabela.getSelectedRow() : -1);

        f = FuncionarioController.salvar(f);

        if (f == null) {
            System.out.println("Erro ao registrar: FuncionarioGUI.salvarRegistro()");
            return;
        }

        // Se for verdadeiro é alteração
        if (index > -1) {
            funcionarios.set(index, f);
        } else {
            funcionarios.add(f);
            txCodigo.setText(String.valueOf(f.getCodigo()));
            index++;
        }

        modelo.setNumRows(0);

        for (Funcionario ff : funcionarios) {
            modelo.addRow(new Object[]{
                ff.getCodigo(), ff.getNome(), ff.getEmail()
            });
        }

        lbStatus.setText("");
        tabela.addRowSelectionInterval(index, index);
    }

    private void excluirRegistro() {
        int linha = -1;
        linha = tabela.getSelectedRow();
        Funcionario funcEx;

        if (usuarioLogado.getNivel() > 2) {
            JOptionPane.showMessageDialog(this, "Você não tem privilégio para manipular funcionários");
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
        funcEx = funcionarios.get(linha);

        if (!UsuarioController.excluir(codigo)) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir, possivelmente há outros registros vinvulados a este funcionário.");
            return;
        }

        funcionarios.remove(linha);
        modelo.setNumRows(0);
        this.inclusaoRegistro();

        for (Funcionario f : funcionarios) {
            try {
                f.setSetor(SetorController.buscarPorId(f.getSetor().getCodigo()));
            } catch (NullPointerException e) {
            }
            try {
                f.setCargo(CargoController.buscarPorId(f.getCargo().getCodigo()));
            } catch (NullPointerException e) {
            }
            try {
                f.setUsuario(UsuarioController.buscarUsuario(f.getUsuario().getUsuario()));
            } catch (NullPointerException e) {
            }
            modelo.addRow(new Object[]{
                f.getCodigo(), f.getNome(), f.getEmail(), f.getCargo().getNome()
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
        bgSexo = new javax.swing.ButtonGroup();
        painelFundo = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        painelConsulta = new javax.swing.JPanel();
        scrollConsulta = new javax.swing.JScrollPane();
        btSalvar = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        sp1 = new javax.swing.JSeparator();
        lbStatus = new javax.swing.JLabel();
        txEmail = new javax.swing.JTextField();
        lbEmail = new javax.swing.JLabel();
        txTelefone = new javax.swing.JTextField();
        lbTelefone = new javax.swing.JLabel();
        lbCodigo = new javax.swing.JLabel();
        txCodigo = new javax.swing.JTextField();
        txDtNascimento = new javax.swing.JTextField();
        lbNome = new javax.swing.JLabel();
        txNome = new javax.swing.JTextField();
        lbDtNascimento = new javax.swing.JLabel();
        txSetorCodigo = new javax.swing.JTextField();
        txDtEfetiva = new javax.swing.JTextField();
        lbDtEfetiva = new javax.swing.JLabel();
        rbMasculino = new javax.swing.JRadioButton();
        lbSexo = new javax.swing.JLabel();
        lbSetor = new javax.swing.JLabel();
        rbFeminino = new javax.swing.JRadioButton();
        txSetorDescricao = new javax.swing.JTextField();
        btSetor = new javax.swing.JButton();
        lbCargo = new javax.swing.JLabel();
        txCargoCodigo = new javax.swing.JTextField();
        txCargoDescricao = new javax.swing.JTextField();
        btCargo = new javax.swing.JButton();
        lbUsuario = new javax.swing.JLabel();
        txUsuarioCodigo = new javax.swing.JTextField();
        txUsuarioNome = new javax.swing.JTextField();
        btUsuario = new javax.swing.JButton();

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
        lbTitulo.setText("Funcionário");

        painelConsulta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout painelConsultaLayout = new javax.swing.GroupLayout(painelConsulta);
        painelConsulta.setLayout(painelConsultaLayout);
        painelConsultaLayout.setHorizontalGroup(
            painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollConsulta)
        );
        painelConsultaLayout.setVerticalGroup(
            painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollConsulta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
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

        txEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txEmail.setText("funcionario@empresa.com.br");

        lbEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbEmail.setText("E-mail:");

        txTelefone.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txTelefone.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txTelefone.setText("(48) 9999-9999");
        txTelefone.setToolTipText("");

        lbTelefone.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbTelefone.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTelefone.setText("Telefone:");

        lbCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCodigo.setText("Código:");

        txCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txCodigo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txCodigo.setText("1");
        txCodigo.setEnabled(false);

        txDtNascimento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txDtNascimento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txDtNascimento.setText("99/99/9999");

        lbNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbNome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNome.setText("Nome:");

        txNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txNome.setText("Nome do Funcionário");

        lbDtNascimento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbDtNascimento.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDtNascimento.setText("Data Nascimento:");

        txSetorCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txSetorCodigo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txSetorCodigo.setText("1");

        txDtEfetiva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txDtEfetiva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txDtEfetiva.setText("99/99/9999");

        lbDtEfetiva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbDtEfetiva.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDtEfetiva.setText("Data Efetivação:");

        bgSexo.add(rbMasculino);
        rbMasculino.setText("Masculino");

        lbSexo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbSexo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbSexo.setText("Sexo:");

        lbSetor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbSetor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbSetor.setText("Setor:");

        bgSexo.add(rbFeminino);
        rbFeminino.setText("Feminino");

        txSetorDescricao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txSetorDescricao.setText("Setor de Atuação do Funcionário");
        txSetorDescricao.setEnabled(false);

        btSetor.setText("Buscar");
        btSetor.setToolTipText("Buscar área");

        lbCargo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbCargo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCargo.setText("Cargo:");

        txCargoCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txCargoCodigo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txCargoCodigo.setText("1");

        txCargoDescricao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txCargoDescricao.setText("Cargo do Funcionário");
        txCargoDescricao.setEnabled(false);

        btCargo.setText("Buscar");
        btCargo.setToolTipText("Buscar área");

        lbUsuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbUsuario.setText("Usuário:");

        txUsuarioCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txUsuarioCodigo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txUsuarioCodigo.setText("1");

        txUsuarioNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txUsuarioNome.setText("Login de Acesso do Funcionário");
        txUsuarioNome.setEnabled(false);

        btUsuario.setText("Buscar");
        btUsuario.setToolTipText("Buscar área");

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
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelFundoLayout.createSequentialGroup()
                                .addComponent(lbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbMasculino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbFeminino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbSetor, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txSetorCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txSetorDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btSetor))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelFundoLayout.createSequentialGroup()
                                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelFundoLayout.createSequentialGroup()
                                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                    .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelFundoLayout.createSequentialGroup()
                                        .addComponent(txTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txCargoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txCargoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btCargo))
                                    .addGroup(painelFundoLayout.createSequentialGroup()
                                        .addComponent(txEmail)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txUsuarioCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txUsuarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btUsuario)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(lbDtNascimento)
                    .addComponent(txDtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDtEfetiva)
                    .addComponent(txDtEfetiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSexo)
                    .addComponent(rbMasculino)
                    .addComponent(rbFeminino)
                    .addComponent(lbSetor)
                    .addComponent(txSetorCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txSetorDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSetor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCargo)
                    .addComponent(txCargoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txCargoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCargo)
                    .addComponent(lbTelefone)
                    .addComponent(txTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUsuario)
                    .addComponent(txUsuarioCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txUsuarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btUsuario)
                    .addComponent(lbEmail)
                    .addComponent(txEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgSexo;
    private javax.swing.JButton btAnterior;
    private javax.swing.JButton btCargo;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btProximo;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btSetor;
    private javax.swing.JButton btUsuario;
    private javax.swing.JComboBox cbFiltro;
    private javax.swing.JLabel lbCargo;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbContaPagina;
    private javax.swing.JLabel lbDtEfetiva;
    private javax.swing.JLabel lbDtNascimento;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbSetor;
    private javax.swing.JLabel lbSexo;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTelefone;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JPanel painelConsulta;
    private javax.swing.JPanel painelFundo;
    private javax.swing.JRadioButton rbFeminino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JScrollPane scrollConsulta;
    private javax.swing.JSeparator sp1;
    private javax.swing.JTextField txCargoCodigo;
    private javax.swing.JTextField txCargoDescricao;
    private javax.swing.JTextField txCodigo;
    private javax.swing.JTextField txDtEfetiva;
    private javax.swing.JTextField txDtNascimento;
    private javax.swing.JTextField txEmail;
    private javax.swing.JTextField txNome;
    private javax.swing.JTextField txPesquisa;
    private javax.swing.JTextField txSetorCodigo;
    private javax.swing.JTextField txSetorDescricao;
    private javax.swing.JTextField txTelefone;
    private javax.swing.JTextField txUsuarioCodigo;
    private javax.swing.JTextField txUsuarioNome;
    // End of variables declaration//GEN-END:variables
}
