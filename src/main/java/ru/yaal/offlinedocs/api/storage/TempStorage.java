package ru.yaal.offlinedocs.api.storage;

import ru.yaal.offlinedocs.api.artifact.Artifact;

import java.io.File;

/**
 * @author Yablokov Aleksey
 */
public interface TempStorage {
    File getArtifactTempDir(Artifact artifact);
}