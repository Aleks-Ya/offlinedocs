package ru.yaal.offlinedocs.impl.storage;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.api.properties.DataAppProps;
import ru.yaal.offlinedocs.api.storage.ArtifactStorage;
import ru.yaal.offlinedocs.api.storage.TempStorage;
import ru.yaal.offlinedocs.api.system.FileApi;
import ru.yaal.offlinedocs.impl.artifact.data.FileArtifactData;

import java.io.File;
import java.io.InputStream;

/**
 * TODO provide thread safety
 *
 * @author Yablokov Aleksey
 */
@Component
class UserDirArtifactStorage implements ArtifactStorage {
    private static final Logger LOG = LoggerFactory.getLogger(UserDirArtifactStorage.class);
    private final File rootDir;
    private final String sep;
    private final TempStorage tempStorage;

    @Autowired
    public UserDirArtifactStorage(FileApi fileApi, DataAppProps dataAppProps, TempStorage tempStorage) {
        this.tempStorage = tempStorage;
        sep = fileApi.getFileSeparator();
        rootDir = dataAppProps.getArtifactStorageDir();
        //noinspection ResultOfMethodCallIgnored
        rootDir.mkdirs();
        LOG.info("Artifact storage directory: " + rootDir.getAbsolutePath());
    }

    @Override
    @SneakyThrows
    public ArtifactData save(Artifact artifact, InputStream artifactIS) {
        File artifactFile = subDirInStorage(rootDir, artifact);
        if (!artifactFile.exists()) {
            File tempFile = tempStorage.createTempFile();
            LOG.debug("Saving artifact to file: " + tempFile);
            FileUtils.copyInputStreamToFile(artifactIS, tempFile);
            LOG.debug("Artifact saved (size {} kb): {}", tempFile.length() / 1000, tempFile);
            LOG.debug("Move {} to {}: ", tempFile, artifactFile);
            FileUtils.moveFile(tempFile, artifactFile);
            LOG.debug("Artifact moved: " + artifactFile);
        } else {
            LOG.debug("Skip exists artifact: " + artifactFile);
        }
        return new FileArtifactData(artifact, artifactFile);
    }

    @Override
    @SneakyThrows
    public ArtifactData save(Artifact artifact, File artifactFile) {
        File artifactFileInStorage = subDirInStorage(rootDir, artifact);
        if (artifactFileInStorage.equals(artifactFile)) {
            LOG.debug("Artifact already in storage: " + artifactFileInStorage.getAbsolutePath());
        } else {
            LOG.debug("Saving artifact to file: " + artifactFileInStorage.getAbsolutePath());
            FileUtils.copyFile(artifactFile, artifactFileInStorage);
            LOG.debug("Artifact saved: " + artifactFileInStorage.getAbsolutePath());
        }
        return new FileArtifactData(artifact, artifactFileInStorage);
    }

    public boolean has(Artifact artifact) {
        return subDirInStorage(rootDir, artifact).exists();
    }

    public ArtifactData read(Artifact artifact) {
        if (!has(artifact)) {
            throw new ArtifactNotFoundException(artifact);
        }
        return new FileArtifactData(artifact, subDirInStorage(rootDir, artifact));
    }

    private File subDirInStorage(File storageDir, Artifact artifact) {
        String file = artifact.getName() + "-" + artifact.getVersion() + "." + artifact.getType().getFileExtension();
        String path = artifact.getCategory() + sep + artifact.getName() + sep + artifact.getVersion() + sep + file;
        return new File(storageDir, path);
    }
}
