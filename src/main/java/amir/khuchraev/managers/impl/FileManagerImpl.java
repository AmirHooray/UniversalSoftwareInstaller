package amir.khuchraev.managers.impl;

import amir.khuchraev.managers.FileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileManagerImpl implements FileManager {

    @Override
    public void copyFilesFromTo(File rootDirectory, String homeProgramPath) throws IOException {
        File homeProgram = new File(homeProgramPath);
        if (!homeProgram.exists()) {
            homeProgram.mkdirs();
        }
        Path sourcePath = rootDirectory.toPath();
        Path targetPath = homeProgram.toPath();
        Files.walk(sourcePath).forEach(source -> {
            Path destination = targetPath.resolve(sourcePath.relativize(source));
            try {
                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
