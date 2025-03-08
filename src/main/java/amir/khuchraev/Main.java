package amir.khuchraev;

import javax.swing.*;

public class Main {

    public static void createAndShowGUI() {
        // Создаем основное окно (JFrame)
        JFrame frame = new JFrame("UniversalSoftwareInstaller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }
}