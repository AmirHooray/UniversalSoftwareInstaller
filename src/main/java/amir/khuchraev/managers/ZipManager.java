package amir.khuchraev.managers;

import java.io.File;
import java.io.IOException;

public interface ZipManager {

    File unzipFile(File zipFile) throws IOException;
}
