package components;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Usuario;

/**
 * Componente UsuarioTableModel * Componente responsável por gerenciar os dados
 * em uma JTable.
 *
 * @version 1.0 14/08/2014
 * @author Gabriel Arsênio da Silva
 */
public class UsuarioTableModel extends AbstractTableModel {

    private List<Usuario> usuarios;

    public UsuarioTableModel() {
        usuarios = new ArrayList<Usuario>();
    }

    public UsuarioTableModel(List<Usuario> usuarioes) {
        this();
        this.usuarios.addAll(usuarioes);
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        if (coluna == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Código";
            case 1:
                return "Nome";
            case 2:
                return "Usuário";
            case 3:
                return "Área";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Usuario usuario = usuarios.get(linha);
        switch (coluna) {
            case 0:
                return usuario.getCodigo();
            case 1:
                return usuario.getNome();
            case 2:
                return usuario.getUsuario();
            case 3:
                return "usuario.getArea().getNome()";
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        Usuario usuario = usuarios.get(linha);
        switch (coluna) {
            case 0:
                usuario.setCodigo(Integer.parseInt(valor.toString()));
                break;
            case 1:
                usuario.setNome(String.valueOf(valor.toString()));
                break;
        }
        fireTableDataChanged();
    }

    public void adicionar(Usuario usuario) {
        usuarios.add(usuario);
        fireTableRowsInserted(usuarios.size() - 1, usuarios.size() - 1);
    }

    public void remover(int indice) {
        usuarios.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public int getIndice(Usuario usuario) {
        return usuarios.indexOf(usuario);
    }

    public void adicionarLista(List<Usuario> lista) {
        int i = usuarios.size();
        usuarios.addAll(lista);
        fireTableRowsInserted(i, i + lista.size());
    }

    public void limparLista() {
        int i = usuarios.size();
        usuarios.clear();
        fireTableRowsDeleted(0, i - 1);
    }
}
