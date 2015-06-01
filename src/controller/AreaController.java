package controller;

import dao.AreaDAO;
import dao.jpa.AreaDAOJPA;
import java.util.List;
import model.Area;

/**
 *
 * @author gabriel_arsenio
 */
public class AreaController {

    public Area salvar(Area area) {
        AreaDAO dao = new AreaDAOJPA();
        return dao.save(area);
    }

    public boolean excluir(int codigo) {
        AreaDAO dao = new AreaDAOJPA();
        return dao.remove(Area.class, codigo);
    }

    public List<Area> listarTodos() {
        AreaDAO dao = new AreaDAOJPA();
        return dao.getAll(Area.class);
    }

    public List<Area> listarTodos(int min, int max) {
        AreaDAO dao = new AreaDAOJPA();
        return dao.getAll(Area.class);
    }

    public Area buscarPorId(int codigo) {
        AreaDAO dao = new AreaDAOJPA();
        return dao.getById(Area.class, codigo);
    }

    public List<Area> pesquisarNome(String nome) {
        AreaDAO dao = new AreaDAOJPA();
        return dao.pesquisarNome(nome);
    }
}
