package amir.khuchraev.managers.impl;

import amir.khuchraev.managers.RegistrationManagerInWindowsRegistry;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

import static amir.khuchraev.consts.Const.*;

public class RegistrationManagerInWindowsRegistryImpl implements RegistrationManagerInWindowsRegistry {

    @Override
    public boolean programRegistrationInWindowsRegistry(
            String appKeyName,
            String displayName,
            String displayVersion,
            String publisher,
            String installLocation,
            String displayIcon,
            String uninstallString
    ) {
        String fullAppKeyPath = UNINSTALL_PATH + "\\" + appKeyName;
        boolean keyIsExists = false;
        if (Advapi32Util.registryKeyExists(WinReg.HKEY_CURRENT_USER, fullAppKeyPath)) {
            keyIsExists = true;
        } else {
            Advapi32Util.registryCreateKey(WinReg.HKEY_CURRENT_USER, UNINSTALL_PATH, appKeyName);
        }
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, fullAppKeyPath, DISPLAY_NAME, displayName);
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, fullAppKeyPath, DISPLAY_VERSION, displayVersion);
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, fullAppKeyPath, PUBLISHER, publisher);
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, fullAppKeyPath, INSTALL_LOCATION, installLocation);
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, fullAppKeyPath, DISPLAY_ICON, displayIcon);
        Advapi32Util.registrySetStringValue(WinReg.HKEY_CURRENT_USER, fullAppKeyPath, UNINSTALL_STRING, uninstallString);
        return keyIsExists;
    }
}
