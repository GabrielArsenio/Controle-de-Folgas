package dao.jpa;

import dao.CargoDAO;
import java.util.List;
import model.Cargo;

/**
 *
 * @author gabriel_arsenio
 */
public class CargoDAOJPA extends DAOJPA<Cargo, Integer> implements CargoDAO {

    @Override
    public List<Cargo> pesquisarNome(String nome) {
        return getEntityManager().createQuery(
                "select c from Cargo c where c.nome like '%" + nome + "%'")
                .getResultList();
    }
}
