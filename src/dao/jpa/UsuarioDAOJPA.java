package dao.jpa;

import dao.UsuarioDAO;
import java.util.List;
import javax.persistence.NoResultException;
import model.Usuario;

/**
 *
 * @author gabriel_arsenio
 */
public class UsuarioDAOJPA extends DAOJPA<Usuario, Integer> implements UsuarioDAO {

    @Override
    public List<Usuario> pesquisarNome(String nome) {
        return getEntityManager().createQuery(
                "select u from Usuario u where u.nome like '%" + nome + "%'")
                .getResultList();
    }

    @Override
    public int getUsuariosCount() {
        return getEntityManager()
                .createQuery("SELECT u FROM Usuario u")
                .getResultList().size();
    }

    @Override
    public Usuario getUsuario(String usuario, String senha) {
        Usuario user = null;
        try {
            user = (Usuario) getEntityManager()
                    .createQuery("SELECT u FROM Usuario u WHERE "
                            + "u.usuario = '" + usuario + "' AND u.senha = '" + senha + "'")
                    .getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Usuário não encontrado\n" + ex.getMessage());
        }

        return user;
    }
}
