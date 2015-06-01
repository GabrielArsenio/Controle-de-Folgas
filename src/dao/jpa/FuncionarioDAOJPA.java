package dao.jpa;

import dao.FuncionarioDAO;
import java.util.List;
import model.Funcionario;

/**
 *
 * @author gabriel_arsenio
 */
public class FuncionarioDAOJPA extends DAOJPA<Funcionario, Integer> implements FuncionarioDAO {

    @Override
    public List<Funcionario> pesquisarNome(String nome) {
        return getEntityManager().createQuery(
                "select f from Funcionario f where f.nome like '%" + nome + "%'")
                .getResultList();
    }
}
