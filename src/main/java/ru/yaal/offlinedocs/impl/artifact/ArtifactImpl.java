package ru.yaal.offlinedocs.impl.artifact;

import ru.yaal.offlinedocs.api.artifact.Artifact;

/**
 * @author Yablokov Aleksey
 */
public class ArtifactImpl implements Artifact {
    private String name;
    private String version;
    private int size;

    public ArtifactImpl(String name, String version, int size) {
        this.name = name;
        this.version = version;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "ArtifactImpl{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", size=" + size +
                '}';
    }
}
