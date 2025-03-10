package amir.khuchraev;

import amir.khuchraev.controller.UniversalInstallerController;
import amir.khuchraev.controller.impl.UniversalInstallerControllerImpl;
import amir.khuchraev.managers.impl.FileManagerImpl;
import amir.khuchraev.managers.impl.MetaExeManagerImpl;
import amir.khuchraev.managers.impl.RegistrationManagerInWindowsRegistryImpl;
import amir.khuchraev.managers.impl.ZipManagerImpl;
import amir.khuchraev.ui.UniversalInstallerViewImpl;

public class DependencyInitializer {

    public static void programInit() {
        UniversalInstallerController controller = createUniversalInstallerUI();
        UniversalInstallerViewImpl installerUi = new UniversalInstallerViewImpl(
                controller
        );
        installerUi.setVisible(true);
    }

    private static UniversalInstallerController createUniversalInstallerUI() {
        return new UniversalInstallerControllerImpl(
                new RegistrationManagerInWindowsRegistryImpl(),
                new FileManagerImpl(),
                new MetaExeManagerImpl(),
                new ZipManagerImpl()
        );
    }
}
