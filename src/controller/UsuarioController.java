package controller;

import dao.UsuarioDAO;
import dao.jpa.UsuarioDAOJPA;
import java.util.List;
import model.Usuario;

/**
 *
 * @author gabriel_arsenio
 */
public class UsuarioController {

    public Usuario salvar(Usuario usuario) {
        UsuarioDAO dao = new UsuarioDAOJPA();
        return dao.save(usuario);
    }

    public boolean excluir(int codigo) {
        UsuarioDAO dao = new UsuarioDAOJPA();
        return dao.remove(Usuario.class, codigo);
    }

    public List<Usuario> listarTodos() {
        UsuarioDAO dao = new UsuarioDAOJPA();
        return dao.getAll(Usuario.class);
    }

    public List<Usuario> listarTodos(int min, int max) {
        UsuarioDAO dao = new UsuarioDAOJPA();
        return dao.getAll(Usuario.class, min, max);
    }

    public Usuario buscarPorId(int codigo) {
        UsuarioDAO dao = new UsuarioDAOJPA();
        return dao.getById(Usuario.class, codigo);
    }

    public Usuario buscarUltimo() {
        UsuarioDAO dao = new UsuarioDAOJPA();
        return dao.getLast(Usuario.class);
    }

    public List<Usuario> pesquisarNome(String nome) {
        UsuarioDAO dao = new UsuarioDAOJPA();
        return dao.pesquisarNome(nome);
    }
}
