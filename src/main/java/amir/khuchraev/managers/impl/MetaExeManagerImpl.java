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
        String productName = exeFile.getName().split("\\.")[0];
        PointerByReference transPtr = new PointerByReference();
        IntByReference transLen = new IntByReference();
        boolean transResult = Version.INSTANCE.VerQueryValue(buffer, "\\VarFileInfo\\Translation", transPtr, transLen);
        if (!transResult || transLen.getValue() < 4) {
            return null;
        }
        byte[] transBytes = transPtr.getValue().getByteArray(0, transLen.getValue());
        int lang = ((transBytes[1] & 0xff) << 8) | (transBytes[0] & 0xff);
        int codepage = ((transBytes[3] & 0xff) << 8) | (transBytes[2] & 0xff);
        String langCode = String.format("%04X%04X", lang, codepage);
        String versionKey = "\\StringFileInfo\\" + langCode + "\\FileVersion";
        String companyKey = "\\StringFileInfo\\" + langCode + "\\CompanyName";
        String fileVersion = queryVersionString(buffer, versionKey);
        String companyName = queryVersionString(buffer, companyKey);
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
        if (queryResult && len.getValue() > 0) {
            Pointer ptr = pbr.getValue();
            return ptr.getWideString(0);
        }
        return null;
    }
}
