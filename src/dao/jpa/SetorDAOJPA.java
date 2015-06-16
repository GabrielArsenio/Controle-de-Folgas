package dao.jpa;

import dao.SetorDAO;
import java.util.List;
import model.Setor;

/**
 *
 * @author gabriel_arsenio
 */
public class SetorDAOJPA extends DAOJPA<Setor, Integer> implements SetorDAO {

    @Override
    public List<Setor> pesquisarNome(String nome) {
        return getEntityManager().createQuery(
                "select a arom Area a where a.nome like '%" + nome + "%'")
                .getResultList();
    }
}
