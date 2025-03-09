package amir.khuchraev.managers.impl;

import amir.khuchraev.managers.MetaExeManager;
import amir.khuchraev.models.ProgramRegistrationInWindowsModel;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Version;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.Memory;
import com.sun.jna.ptr.PointerByReference;

import java.io.File;

public class MetaExeManagerImpl implements MetaExeManager {

    @Override
    public ProgramRegistrationInWindowsModel getProgramMetaInfo(
            File exeFile,
            File pathInstallLocation
    )  {
        String exePath = exeFile.getAbsolutePath();
        IntByReference dwDummy = new IntByReference();
        int size = Version.INSTANCE.GetFileVersionInfoSize(exePath, dwDummy);
        if (size == 0) {
            return null;
        }
        Memory buffer = new Memory(size);
        boolean result = Version.INSTANCE.GetFileVersionInfo(exePath, 0, size, buffer);
        if (!result) {
            return null;
        }
        String productName = queryVersionString(buffer, "\\StringFileInfo\\040904E4\\ProductName");
        String fileVersion = queryVersionString(buffer, "\\StringFileInfo\\040904E4\\FileVersion");
        String companyName = queryVersionString(buffer, "\\StringFileInfo\\040904E4\\CompanyName");
        return new ProgramRegistrationInWindowsModel(
                productName,
                productName,
                fileVersion,
                companyName,
                pathInstallLocation.getAbsolutePath(),
                exeFile.getAbsolutePath(),
                ""
        );
    }

    private static String queryVersionString(Memory buffer, String subBlock) {
        PointerByReference pbr = new PointerByReference();
        IntByReference len = new IntByReference();
        boolean queryResult = Version.INSTANCE.VerQueryValue(buffer, subBlock, pbr, len);
        if (queryResult) {
            Pointer ptr = pbr.getValue();
            return ptr.getWideString(0);
        }
        return null;
    }
}
