package ru.yaal.offlinedocs.impl.artifact.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.storage.FileNameStrategy;
import ru.yaal.offlinedocs.api.system.FileApi;

import java.io.File;

/**
 * @author Yablokov Aleksey
 */
@Component
public class NameVersionFileNameStrategy implements FileNameStrategy {
    private final FileApi fileApi;

    @Autowired
    public NameVersionFileNameStrategy(FileApi fileApi) {
        this.fileApi = fileApi;
    }

    @Override
    public File toFile(File rootDir, Artifact artifact) {
        String sep = fileApi.getFileSeparator();
        String path = artifact.getCategory() + sep + artifact.getName() + sep
                + artifact.getVersion() + sep + artifact.getName() + "." + artifact.getType().getFileExtension();
        return new File(rootDir, path);
    }
}
