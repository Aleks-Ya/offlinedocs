package ru.yaal.offlinedocs.impl.storage;

import com.google.common.io.ByteStreams;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.ArtifactNotFoundException;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.api.storage.ArtifactStorage;
import ru.yaal.offlinedocs.impl.artifact.data.ByteArrayArtifactData;
import ru.yaal.offlinedocs.spring.Profiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yablokov Aleksey
 */
//@Component
//@Profile(Profiles.TEST)
@Deprecated
public class InMemoryArtifactStorage implements ArtifactStorage {
    private final Map<Artifact, byte[]> artifacts = new HashMap<>();

    @Override
    @SneakyThrows
    public ArtifactData save(Artifact artifact, InputStream artifactIS) {
        byte[] bytes = ByteStreams.toByteArray(artifactIS);
        artifacts.put(artifact, bytes);
        return new ByteArrayArtifactData(artifact, bytes);
    }

    @Override
    @SneakyThrows
    public ArtifactData save(Artifact artifact, File artifactFile) {
        return save(artifact, new FileInputStream(artifactFile));
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
