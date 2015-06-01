package controller;

import dao.FolgaDAO;
import dao.jpa.FolgaDAOJPA;
import java.util.List;
import model.Folga;

/**
 *
 * @author gabriel_arsenio
 */
public class FolgaController {

    public Folga salvar(Folga folga) {
        FolgaDAO dao = new FolgaDAOJPA();
        return dao.save(folga);
    }

    public boolean excluir(int codigo) {
        FolgaDAO dao = new FolgaDAOJPA();
        return dao.remove(Folga.class, codigo);
    }

    public List<Folga> listarTodos() {
        FolgaDAO dao = new FolgaDAOJPA();
        return dao.getAll(Folga.class);
    }

    public List<Folga> listarTodos(int min, int max) {
        FolgaDAO dao = new FolgaDAOJPA();
        return dao.getAll(Folga.class);
    }

    public Folga buscarPorId(int codigo) {
        FolgaDAO dao = new FolgaDAOJPA();
        return dao.getById(Folga.class, codigo);
    }

    public List<Folga> pesquisarMotivo(String nome) {
        FolgaDAO dao = new FolgaDAOJPA();
        return dao.pesquisarMotivo(nome);
    }
}
