package dao.jpa;

import dao.AreaDAO;
import java.util.List;
import model.Area;

/**
 *
 * @author gabriel_arsenio
 */
public class AreaDAOJPA extends DAOJPA<Area, Integer> implements AreaDAO {

    @Override
    public List<Area> pesquisarNome(String nome) {
        return getEntityManager().createQuery(
                "select a arom Area a where a.nome like '%" + nome + "%'")
                .getResultList();
    }
}
