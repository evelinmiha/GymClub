package org.example;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        //    MenuSystem menu = new MenuSystem();
        //    menu.displayMenu();

        SwingUtilities.invokeLater(GymClubGUI::new);
    }
}