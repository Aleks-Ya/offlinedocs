package ru.yaal.offlinedocs.impl.storage;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.ArtifactNotFoundException;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.api.properties.DataAppProps;
import ru.yaal.offlinedocs.api.storage.ArtifactStorage;
import ru.yaal.offlinedocs.api.system.FileApi;
import ru.yaal.offlinedocs.impl.artifact.data.FileArtifactData;

import java.io.File;
import java.io.InputStream;

/**
 * @author Yablokov Aleksey
 */
@Component
class UserDirArtifactStorage implements ArtifactStorage {
    private static final Logger LOG = LoggerFactory.getLogger(UserDirArtifactStorage.class);
    private final File rootDir;
    private final String sep;

    @Autowired
    public UserDirArtifactStorage(FileApi fileApi, DataAppProps dataAppProps) {
        sep = fileApi.getFileSeparator();
        rootDir = dataAppProps.getArtifactStorageDir();
        LOG.info("Artifact storage directory: " + rootDir.getAbsolutePath());
    }

    @Override
    @SneakyThrows
    public ArtifactData save(Artifact artifact, InputStream artifactIS) {
        File artifactFile = subDirInStorage(rootDir, artifact);
        LOG.debug("Saving artifact to file: " + artifactFile.getAbsolutePath());
        FileUtils.copyInputStreamToFile(artifactIS, artifactFile);
        LOG.debug("Artifact saved: " + artifactFile.getAbsolutePath());
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
        String path = artifact.getCategory() + sep + artifact.getName() + sep
                + artifact.getVersion() + sep + artifact.getName() + "." + artifact.getType().getFileExtension();
        return new File(storageDir, path);
    }
}
