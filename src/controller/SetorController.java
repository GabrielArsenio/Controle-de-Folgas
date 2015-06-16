package controller;

import dao.SetorDAO;
import dao.jpa.SetorDAOJPA;
import java.util.List;
import model.Setor;

/**
 *
 * @author gabriel_arsenio
 */
public class SetorController {

    public Setor salvar(Setor area) {
        SetorDAO dao = new SetorDAOJPA();
        return dao.save(area);
    }

    public boolean excluir(int codigo) {
        SetorDAO dao = new SetorDAOJPA();
        return dao.remove(Setor.class, codigo);
    }

    public List<Setor> listarTodos() {
        SetorDAO dao = new SetorDAOJPA();
        return dao.getAll(Setor.class);
    }

    public List<Setor> listarTodos(int min, int max) {
        SetorDAO dao = new SetorDAOJPA();
        return dao.getAll(Setor.class);
    }

    public Setor buscarPorId(int codigo) {
        SetorDAO dao = new SetorDAOJPA();
        return dao.getById(Setor.class, codigo);
    }

    public List<Setor> pesquisarNome(String nome) {
        SetorDAO dao = new SetorDAOJPA();
        return dao.pesquisarNome(nome);
    }
}
