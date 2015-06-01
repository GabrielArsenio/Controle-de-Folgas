package dao.jpa;

import dao.FolgaDAO;
import java.util.List;
import model.Folga;

/**
 *
 * @author gabriel_arsenio
 */
public class FolgaDAOJPA extends DAOJPA<Folga, Integer> implements FolgaDAO {

    @Override
    public List<Folga> pesquisarMotivo(String motivo) {
        return getEntityManager().createQuery(
                "select f from Folga f where f.motivo like '%" + motivo + "%'")
                .getResultList();
    }
}
