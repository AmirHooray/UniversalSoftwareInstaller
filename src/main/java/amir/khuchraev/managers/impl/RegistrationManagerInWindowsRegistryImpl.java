package amir.khuchraev.managers.impl;

import amir.khuchraev.managers.RegistrationManagerInWindowsRegistry;
import amir.khuchraev.models.ProgramRegistrationInWindowsModel;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

import static amir.khuchraev.consts.Const.*;

public class RegistrationManagerInWindowsRegistryImpl implements RegistrationManagerInWindowsRegistry {

    @Override
    public boolean programRegistrationInWindowsRegistry(
            ProgramRegistrationInWindowsModel model
    ) {
        String fullAppKeyPath = UNINSTALL_PATH + "\\" + model.getAppKeyName();
        boolean keyIsExists = false;
        if (Advapi32Util.registryKeyExists(WinReg.HKEY_CURRENT_USER, fullAppKeyPath)) {
            keyIsExists = true;
        } else {
            Advapi32Util.registryCreateKey(WinReg.HKEY_CURRENT_USER, UNINSTALL_PATH, model.getAppKeyName());
        }
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, fullAppKeyPath, DISPLAY_NAME, model.getDisplayName());
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, fullAppKeyPath, DISPLAY_VERSION, model.getDisplayVersion());
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, fullAppKeyPath, PUBLISHER, model.getPublisher());
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, fullAppKeyPath, INSTALL_LOCATION, model.getInstallLocation());
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, fullAppKeyPath, DISPLAY_ICON, model.getDisplayIcon());
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, fullAppKeyPath, UNINSTALL_STRING, model.getUninstallString());
        return keyIsExists;
    }
}
