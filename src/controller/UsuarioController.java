package controller;

import dao.UsuarioDAO;
import dao.jpa.UsuarioDAOJPA;
import java.util.List;
import model.Usuario;

/**
 *
 * @author gabriel_arsenio
 */
public abstract class UsuarioController {

    public static Usuario salvar(Usuario usuario) {
        UsuarioDAO dao = new UsuarioDAOJPA();
        return dao.save(usuario);
    }

    public static boolean excluir(int codigo) {
        UsuarioDAO dao = new UsuarioDAOJPA();
        return dao.remove(Usuario.class, codigo);
    }

    public static List<Usuario> listarTodos() {
        UsuarioDAO dao = new UsuarioDAOJPA();
        return dao.getAll(Usuario.class);
    }

    public static List<Usuario> listarTodos(int min, int max) {
        UsuarioDAO dao = new UsuarioDAOJPA();
        return dao.getAll(Usuario.class, min, max);
    }

    public static Usuario buscarPorId(int codigo) {
        UsuarioDAO dao = new UsuarioDAOJPA();
        return dao.getById(Usuario.class, codigo);
    }

    public static Usuario buscarUsuario(String usuario, String senha) {
        UsuarioDAO dao = new UsuarioDAOJPA();
        return dao.getUsuario(usuario, senha);
    }

    public static Usuario buscarUltimo() {
        UsuarioDAO dao = new UsuarioDAOJPA();
        return dao.getLast(Usuario.class);
    }

    public static List<Usuario> pesquisarNome(String nome) {
        UsuarioDAO dao = new UsuarioDAOJPA();
        return dao.pesquisarNome(nome);
    }

    public static int getUsuariosCount() {
        UsuarioDAO dao = new UsuarioDAOJPA();
        return dao.getUsuariosCount();
    }

    public static Usuario validarLogin(String usuario, String senha) {
        Usuario user = null;
        
        if (getUsuariosCount() == 0) {
            user = new Usuario("Administrador", "admin", "admin", null, 0);
            user = UsuarioController.salvar(user);
        } else {
            user = UsuarioController.buscarUsuario(usuario, senha);
        }

        return user;
    }
}
