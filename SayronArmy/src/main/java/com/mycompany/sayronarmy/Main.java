package com.mycompany.sayronarmy;

import com.mycompany.sayronarmy.controller.ArmyController;
import javax.swing.SwingUtilities;

/**
 *
 * @author lihac
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArmyController controller = new ArmyController();
            controller.launchApplication();
        });
    }
}
