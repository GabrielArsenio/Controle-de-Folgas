package controller;

import dao.FuncionarioDAO;
import dao.jpa.FuncionarioDAOJPA;
import java.util.List;
import model.Funcionario;

/**
 *
 * @author gabriel_arsenio
 */
public class FuncionarioController {

    public static Funcionario salvar(Funcionario funcionario) {
        FuncionarioDAO dao = new FuncionarioDAOJPA();
        return dao.save(funcionario);
    }

    public static boolean excluir(int codigo) {
        FuncionarioDAO dao = new FuncionarioDAOJPA();
        return dao.remove(Funcionario.class, codigo);
    }

    public static List<Funcionario> listarTodos() {
        FuncionarioDAO dao = new FuncionarioDAOJPA();
        return dao.getAll(Funcionario.class);
    }

    public static List<Funcionario> listarTodos(int min, int max) {
        FuncionarioDAO dao = new FuncionarioDAOJPA();
        return dao.getAll(Funcionario.class);
    }

    public static Funcionario buscarPorId(int codigo) {
        FuncionarioDAO dao = new FuncionarioDAOJPA();
        return dao.getById(Funcionario.class, codigo);
    }

    public static List<Funcionario> pesquisarNome(String nome) {
        FuncionarioDAO dao = new FuncionarioDAOJPA();
        return dao.pesquisarNome(nome);
    }
}
