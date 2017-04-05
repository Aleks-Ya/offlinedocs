package ru.yaal.offlinedocs.api.artifact.type;

/**
 * TODO convenient methods for getting standard ArtifactType (e.g. getPdfArtifactType).
 *
 * @author Yablokov Aleksey
 */
public interface ArtifactTypeFactory {
    ArtifactType getTypeById(String artifactTypeId);
}
