package ru.yaal.offlinedocs.api.artifact.storage;

import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;

/**
 * @author Yablokov Aleksey
 */
public interface ArtifactStorage {
    void save(ArtifactData artifactData);

    boolean has(Artifact artifact);

    ArtifactData read(Artifact artifact);
}
