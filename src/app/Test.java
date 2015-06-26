package app;

import controller.CargoController;
import controller.FuncionarioController;
import controller.SetorController;
import controller.UsuarioController;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import model.Cargo;
import model.Funcionario;
import model.Setor;

/**
 *
 * @author Gabriel
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        List<Cargo> cargos = CargoController.listarTodos();
        List<Funcionario> funcionarios = FuncionarioController.listarTodos();
        
        System.out.println(Time.valueOf("1000:-2:0"));    
//        for (int i = 0; i < 10; i++) {
//            SetorController.salvar(new Setor("Setor", "Descricao", funcionarios.get(i)));
//        }

        for (int i = 0; i < 10; i++) {
            FuncionarioController.salvar(
                    new Funcionario(
                            "Funcionario",//Nome
                            new Date(),//Data Nascimento
                            new Date(),//Data Efetiva
                            'M',//Sexo
                            null,//Setor
                            null,//Cargo
                            null,//Usuario
                            "Telefone",
                            "Email"));
        }
        
    }

}
