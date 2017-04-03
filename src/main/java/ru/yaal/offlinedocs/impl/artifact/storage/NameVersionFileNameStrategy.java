package ru.yaal.offlinedocs.impl.artifact.storage;

import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.storage.FileNameStrategy;

import java.io.File;

/**
 * @author Yablokov Aleksey
 */
@Component
public class NameVersionFileNameStrategy implements FileNameStrategy {
    @Override
    public File toFile(File rootDir, Artifact artifact) {
        String path = artifact.getName() + File.pathSeparator + artifact.getVersion() + "object.bin";
        return new File(rootDir, path);
    }
}
