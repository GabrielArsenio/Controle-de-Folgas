package dao;

import java.util.List;
import model.Usuario;

/**
 *
 * @author gabriel_arsenio
 */
public interface UsuarioDAO extends DAO<Usuario, Integer> {

    List<Usuario> pesquisarNome(String nome);

    int getUsuariosCount();
    
    Usuario getUsuario(String usuario, String senha);
    
    Usuario getUsuario(String usuario);
}
