package ru.yaal.offlinedocs.api.artifact.storage;

import ru.yaal.offlinedocs.api.artifact.Artifact;

import java.io.File;

/**
 * @author Yablokov Aleksey
 */
public interface FileNameStrategy {
    File toFile(File rootDir, Artifact artifact);
}
