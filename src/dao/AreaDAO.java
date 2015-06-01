package dao;

import java.util.List;
import model.Area;

/**
 *
 * @author gabriel_arsenio
 */
public interface AreaDAO extends DAO<Area, Integer> {

    List<Area> pesquisarNome(String nome);
}
