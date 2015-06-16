package app;

import controller.CargoController;
import controller.UsuarioController;
import model.Cargo;

/**
 *
 * @author Gabriel
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            CargoController.salvar(new Cargo("Cargo", "Descricao"));            
        }
    }
    
}
