package ru.yaal.offlinedocs.api.artifact.type;

/**
 * @author Yablokov Aleksey
 */
public interface ArtifactTypeFactory {
    ArtifactType getTypeById(String artifactTypeId);
}
