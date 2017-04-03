package ru.yaal.offlinedocs.impl.artifact.storage;

import com.google.common.io.ByteStreams;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.ArtifactNotFoundException;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.api.artifact.storage.ArtifactStorage;
import ru.yaal.offlinedocs.impl.artifact.data.ByteArrayArtifactData;
import ru.yaal.offlinedocs.spring.Profiles;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yablokov Aleksey
 */
@Component
@Profile(Profiles.TEST)
public class InMemoryArtifactStorage implements ArtifactStorage {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryArtifactStorage.class);
    private final Map<Artifact, byte[]> artifacts = new HashMap<>();

    @SneakyThrows
    public void save(ArtifactData artifactData) {
        LOG.debug("Save to storage: " + artifactData);
        artifacts.put(artifactData.getArtifact(), ByteStreams.toByteArray(artifactData.getData()));
    }

    public boolean has(Artifact artifact) {
        return artifacts.containsKey(artifact);
    }

    public ArtifactData read(Artifact artifact) {
        byte[] bytes = artifacts.get(artifact);
        if (bytes == null) {
            throw new ArtifactNotFoundException(artifact);
        }
        return new ByteArrayArtifactData(artifact, bytes);
    }
}
