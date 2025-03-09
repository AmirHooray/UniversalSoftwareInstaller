package amir.khuchraev.models;

public class ProgramRegistrationInWindowsModel {

    private String appKeyName;
    private String displayName;
    private String displayVersion;
    private String publisher;
    private String installLocation;
    private String displayIcon;
    private String uninstallString;

    public ProgramRegistrationInWindowsModel(
            String appKeyName,
            String displayName,
            String displayVersion,
            String publisher,
            String installLocation,
            String displayIcon,
            String uninstallString) {
        this.appKeyName = appKeyName;
        this.displayName = displayName;
        this.displayVersion = displayVersion;
        this.publisher = publisher;
        this.installLocation = installLocation;
        this.displayIcon = displayIcon;
        this.uninstallString = uninstallString;
    }

    public String getAppKeyName() {
        return appKeyName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDisplayVersion() {
        return displayVersion;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getInstallLocation() {
        return installLocation;
    }

    public String getDisplayIcon() {
        return displayIcon;
    }

    public String getUninstallString() {
        return uninstallString;
    }
}
