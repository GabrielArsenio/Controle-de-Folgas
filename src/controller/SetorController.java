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

    public static Setor salvar(Setor area) {
        SetorDAO dao = new SetorDAOJPA();
        return dao.save(area);
    }

    public static boolean excluir(int codigo) {
        SetorDAO dao = new SetorDAOJPA();
        return dao.remove(Setor.class, codigo);
    }

    public static List<Setor> listarTodos() {
        SetorDAO dao = new SetorDAOJPA();
        return dao.getAll(Setor.class);
    }

    public static List<Setor> listarTodos(int min, int max) {
        SetorDAO dao = new SetorDAOJPA();
        return dao.getAll(Setor.class);
    }

    public static Setor buscarPorId(int codigo) {
        SetorDAO dao = new SetorDAOJPA();
        return dao.getById(Setor.class, codigo);
    }

    public static List<Setor> pesquisarNome(String nome) {
        SetorDAO dao = new SetorDAOJPA();
        return dao.pesquisarNome(nome);
    }
}
