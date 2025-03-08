package amir.khuchraev.managers;

public interface RegistrationManagerInWindowsRegistry {

    boolean programRegistrationInWindowsRegistry(
            String appKeyName,
            String displayName,
            String displayVersion,
            String publisher,
            String installLocation,
            String displayIcon,
            String uninstallString);
}
