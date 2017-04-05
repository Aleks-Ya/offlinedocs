package ru.yaal.offlinedocs.api.artifact;

import ru.yaal.offlinedocs.api.artifact.type.ArtifactType;

/**
 * TODO shorter: "artifact" -> "art"
 *
 * @author Yablokov Aleksey
 */
public interface Artifact {
    String getCategory();

    String getName();

    String getVersion();

    ArtifactType getType();
}
