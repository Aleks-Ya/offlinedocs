package ru.yaal.offlinedocs.api.artifact;

/**
 * @author Yablokov Aleksey
 */
public interface Artifact {
    String getCategory();
    String getName();
    String getVersion();
    int getSize();
}
