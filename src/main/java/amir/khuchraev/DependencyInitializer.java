package amir.khuchraev;

import amir.khuchraev.ui.UniversalInstallerUI;

public class DependencyInitializer {

    public static void programInit() {
        UniversalInstallerUI installerUi = new UniversalInstallerUI();
        installerUi.setVisible(true);
    }
}
