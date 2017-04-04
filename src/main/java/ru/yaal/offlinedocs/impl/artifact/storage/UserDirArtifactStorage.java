package ru.yaal.offlinedocs.impl.artifact.storage;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.ArtifactNotFoundException;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.api.artifact.storage.ArtifactStorage;
import ru.yaal.offlinedocs.api.artifact.storage.FileNameStrategy;
import ru.yaal.offlinedocs.impl.artifact.data.FileArtifactData;
import ru.yaal.offlinedocs.spring.Profiles;

import java.io.File;

/**
 * @author Yablokov Aleksey
 */
@Component
@Profile(Profiles.PROD)
public class UserDirArtifactStorage implements ArtifactStorage {
    private static final Logger LOG = LoggerFactory.getLogger(UserDirArtifactStorage.class);
    private static final File rootDir = new File(System.getProperty("user.home"), ".offlinedocs/storage");

    @Autowired
    @SuppressWarnings("SpringAutowiredFieldsWarningInspection")
    private FileNameStrategy strategy;

    public UserDirArtifactStorage() {
        LOG.info("Artifact storage directory: " + rootDir.getAbsolutePath());
    }

    @SneakyThrows
    public void save(ArtifactData artifactData) {
        Artifact artifact = artifactData.getArtifact();
        File artifactFile = strategy.subDirInStorage(rootDir, artifact);
        LOG.debug("Saving artifact to file: " + artifactFile.getAbsolutePath());
        FileUtils.copyInputStreamToFile(artifactData.getData(), artifactFile);
        LOG.debug("Artifact saved: " + artifactFile.getAbsolutePath());
    }

    public boolean has(Artifact artifact) {
        return strategy.subDirInStorage(rootDir, artifact).exists();
    }

    public ArtifactData read(Artifact artifact) {
        if (!has(artifact)) {
            throw new ArtifactNotFoundException(artifact);
        }
        return new FileArtifactData(artifact, strategy.subDirInStorage(rootDir, artifact));
    }
}
