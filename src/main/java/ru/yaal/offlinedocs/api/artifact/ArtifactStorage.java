package ru.yaal.offlinedocs.api.artifact;

import java.io.InputStream;

/**
 * @author Yablokov Aleksey
 */
public interface ArtifactStorage {
    void save(ArtifactData artifactData);

    boolean has(Artifact artifact);

    ArtifactData read(Artifact artifact);
}
