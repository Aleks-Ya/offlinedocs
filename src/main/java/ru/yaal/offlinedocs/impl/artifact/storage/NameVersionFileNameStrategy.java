package ru.yaal.offlinedocs.impl.artifact.storage;

import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.storage.FileNameStrategy;

import java.io.File;

import static java.io.File.separator;

/**
 * @author Yablokov Aleksey
 */
@Component
public class NameVersionFileNameStrategy implements FileNameStrategy {
    @Override
    public File toFile(File rootDir, Artifact artifact) {
        String path = artifact.getCategory() + separator + artifact.getName() + separator
                + artifact.getVersion() + separator + artifact.getName() + "." + artifact.getType().getFileExtension();
        return new File(rootDir, path);
    }
}
