package ru.yaal.offlinedocs.api.artifact.storage;

import ru.yaal.offlinedocs.api.artifact.Artifact;

import java.io.File;

/**
 * @author Yablokov Aleksey
 */
public interface FileNameStrategy {
    File toFile(File rootDir, Artifact artifact);

    String artifactToFileName(Artifact artifact);

    File artifactToFile(File rootDir, Artifact artifact);

    File subDirInOutlet(File storageDir, Artifact artifact);
}
