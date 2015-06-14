package dao;

import java.util.List;
import model.Setor;

/**
 *
 * @author gabriel_arsenio
 */
public interface AreaDAO extends DAO<Setor, Integer> {

    List<Setor> pesquisarNome(String nome);
}
