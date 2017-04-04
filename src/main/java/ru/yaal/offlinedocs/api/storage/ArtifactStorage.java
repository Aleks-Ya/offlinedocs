package ru.yaal.offlinedocs.api.storage;

import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;

import java.io.File;
import java.io.InputStream;

/**
 * @author Yablokov Aleksey
 */
public interface ArtifactStorage {
    ArtifactData save(Artifact artifact, InputStream artifactIS);

    ArtifactData save(Artifact artifact, File artifactFile);

    boolean has(Artifact artifact);

    ArtifactData read(Artifact artifact);
}
