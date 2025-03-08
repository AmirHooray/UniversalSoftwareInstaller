package amir.khuchraev.managers;

import java.io.File;
import java.io.IOException;

public interface FileManager {

    void copyFilesFromTo(File rootDirectory, String homeProgramPath) throws IOException;
}
