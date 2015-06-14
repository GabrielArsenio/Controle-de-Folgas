package dao.jpa;

import dao.AreaDAO;
import java.util.List;
import model.Setor;

/**
 *
 * @author gabriel_arsenio
 */
public class AreaDAOJPA extends DAOJPA<Setor, Integer> implements AreaDAO {

    @Override
    public List<Setor> pesquisarNome(String nome) {
        return getEntityManager().createQuery(
                "select a arom Area a where a.nome like '%" + nome + "%'")
                .getResultList();
    }
}
