package amir.khuchraev.managers;

import amir.khuchraev.models.ProgramRegistrationInWindowsModel;

import java.io.File;

public interface MetaExeManager {

    ProgramRegistrationInWindowsModel getProgramMetaInfo(
            File exeFile,
            File pathInstallLocation
    );
}
