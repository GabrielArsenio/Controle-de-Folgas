package dao;

import java.util.List;
import model.Funcionario;

/**
 *
 * @author gabriel_arsenio
 */
public interface FuncionarioDAO extends DAO<Funcionario, Integer> {

    List<Funcionario> pesquisarNome(String nome);
}
