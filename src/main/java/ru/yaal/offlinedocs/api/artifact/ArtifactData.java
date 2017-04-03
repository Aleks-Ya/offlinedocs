package ru.yaal.offlinedocs.api.artifact;

import java.io.InputStream;

/**
 * @author Yablokov Aleksey
 */
public interface ArtifactData {
    Artifact getArtifact();
    InputStream getData();
}
