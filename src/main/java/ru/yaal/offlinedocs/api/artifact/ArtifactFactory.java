package ru.yaal.offlinedocs.api.artifact;

/**
 * @author Yablokov Aleksey
 */
public interface ArtifactFactory {
    Artifact instantiate(String category, String name, String version, String typeId);
}
