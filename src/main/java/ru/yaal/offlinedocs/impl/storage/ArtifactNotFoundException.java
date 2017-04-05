package ru.yaal.offlinedocs.impl.storage;

import ru.yaal.offlinedocs.api.artifact.Artifact;

/**
 * @author Yablokov Aleksey
 */
class ArtifactNotFoundException extends RuntimeException {
    ArtifactNotFoundException(Artifact artifact) {
        super(artifact.toString());
    }
}
