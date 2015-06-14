package controller;

import dao.AreaDAO;
import dao.jpa.AreaDAOJPA;
import java.util.List;
import model.Setor;

/**
 *
 * @author gabriel_arsenio
 */
public class AreaController {

    public Setor salvar(Setor area) {
        AreaDAO dao = new AreaDAOJPA();
        return dao.save(area);
    }

    public boolean excluir(int codigo) {
        AreaDAO dao = new AreaDAOJPA();
        return dao.remove(Setor.class, codigo);
    }

    public List<Setor> listarTodos() {
        AreaDAO dao = new AreaDAOJPA();
        return dao.getAll(Setor.class);
    }

    public List<Setor> listarTodos(int min, int max) {
        AreaDAO dao = new AreaDAOJPA();
        return dao.getAll(Setor.class);
    }

    public Setor buscarPorId(int codigo) {
        AreaDAO dao = new AreaDAOJPA();
        return dao.getById(Setor.class, codigo);
    }

    public List<Setor> pesquisarNome(String nome) {
        AreaDAO dao = new AreaDAOJPA();
        return dao.pesquisarNome(nome);
    }
}
