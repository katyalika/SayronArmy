package com.mycompany.sayronarmy;

import com.mycompany.sayronarmy.gui.SayronArmyGui;
import javax.swing.SwingUtilities;

/**
 *
 * @author lihac
 */
public class SayronArmy {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SayronArmyGui gui = new SayronArmyGui();
            gui.setVisible(true);
        });
    }
}
