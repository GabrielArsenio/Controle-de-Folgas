package dao.jpa;

import dao.UsuarioDAO;
import java.util.List;
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
}
