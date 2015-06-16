package dao;

import java.util.List;
import model.Cargo;

/**
 *
 * @author gabriel_arsenio
 */
public interface CargoDAO extends DAO<Cargo, Integer> {

    List<Cargo> pesquisarNome(String nome);
}
