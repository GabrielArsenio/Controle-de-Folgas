package controller;

import dao.CargoDAO;
import dao.jpa.CargoDAOJPA;
import java.util.List;
import model.Cargo;

/**
 *
 * @author gabriel_arsenio
 */
public class CargoController {

    public static Cargo salvar(Cargo area) {
        CargoDAO dao = new CargoDAOJPA();
        return dao.save(area);
    }

    public boolean excluir(int codigo) {
        CargoDAO dao = new CargoDAOJPA();
        return dao.remove(Cargo.class, codigo);
    }

    public List<Cargo> listarTodos() {
        CargoDAO dao = new CargoDAOJPA();
        return dao.getAll(Cargo.class);
    }

    public static List<Cargo> listarTodos(int min, int max) {
        CargoDAO dao = new CargoDAOJPA();
        return dao.getAll(Cargo.class);
    }

    public Cargo buscarPorId(int codigo) {
        CargoDAO dao = new CargoDAOJPA();
        return dao.getById(Cargo.class, codigo);
    }

    public List<Cargo> pesquisarNome(String nome) {
        CargoDAO dao = new CargoDAOJPA();
        return dao.pesquisarNome(nome);
    }
}
