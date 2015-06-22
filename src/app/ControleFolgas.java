package app;

import javax.persistence.Persistence;
import view.LoginGUI;

/**
 *
 * @author Gabriel
 */
public class ControleFolgas {

    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("ControleFolgasPU");
        new LoginGUI().setVisible(true);
    }
}
