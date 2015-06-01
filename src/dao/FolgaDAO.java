package dao;

import java.util.List;
import model.Folga;

/**
 *
 * @author gabriel_arsenio
 */
public interface FolgaDAO extends DAO<Folga, Integer> {

    List<Folga> pesquisarMotivo(String motivo);
}
