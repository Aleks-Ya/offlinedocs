package ru.yaal.offlinedocs.api.artifact.data;

import ru.yaal.offlinedocs.api.artifact.Artifact;

import java.io.File;
import java.io.InputStream;

/**
 * @author Yablokov Aleksey
 */
public interface ArtifactData {
    Artifact getArtifact();
    InputStream getInputStream();
    File getFile();
}
