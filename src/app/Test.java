package app;

import controller.UsuarioController;

/**
 *
 * @author Gabriel
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(UsuarioController.buscarUsuario("admin", "admin").getNome());
    }
    
}
