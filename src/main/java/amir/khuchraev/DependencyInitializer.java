package amir.khuchraev;

import amir.khuchraev.controller.UniversalInstallerController;
import amir.khuchraev.controller.impl.UniversalInstallerControllerImpl;
import amir.khuchraev.ui.UniversalInstallerUI;

public class DependencyInitializer {

    public static void programInit() {
        UniversalInstallerController controller = createUniversalInstallerUI();
        UniversalInstallerUI installerUi = new UniversalInstallerUI(
                createUniversalInstallerUI()
        );
        installerUi.setVisible(true);
        controller.attacheView(installerUi);
    }

    private static UniversalInstallerController createUniversalInstallerUI() {
        return new UniversalInstallerControllerImpl();
    }
}
