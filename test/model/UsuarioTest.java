package model;

import controller.UsuarioController;
import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sun.usagetracker.UsageTrackerClient;

/**
 *
 * @author Gabriel
 */
public class UsuarioTest {

    public UsuarioTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void insere50() {
        Usuario u = new UsuarioController().buscarUltimo();
        int aux = (u != null ? u.getCodigo() : 0);

        for (int i = aux + 1; i < aux + 50; i++) {
            u = new Usuario();
            u.setNome("Usuário teste " + i);
            u.setUsuario("Login " + i);
            new UsuarioController().salvar(u);
        }
    }

    @Test
    public void buscaUltimo() {
        Usuario u = new UsuarioController().buscarUltimo();
//        JOptionPane.showMessageDialog(null, u != null ? u.getCodigo() : 0);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
