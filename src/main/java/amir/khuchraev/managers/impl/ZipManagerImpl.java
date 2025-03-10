package amir.khuchraev.managers.impl;

import amir.khuchraev.managers.ZipManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipManagerImpl implements ZipManager {

    public File unzipFile(File zipFile) throws IOException {
        String zipName = zipFile.getName();
        int dotIndex = zipName.lastIndexOf('.');
        String folderName = (dotIndex > 0) ? zipName.substring(0, dotIndex) : zipName;
        File destDir = new File(zipFile.getParentFile(), folderName);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry zipEntry = zis.getNextEntry();
            byte[] buffer = new byte[4096];
            while (zipEntry != null) {
                File newFile = new File(destDir, zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    File parent = newFile.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zis.closeEntry();
                zipEntry = zis.getNextEntry();
            }
        }
        return destDir;
    }
}
