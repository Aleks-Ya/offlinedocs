package ru.yaal.offlinedocs.impl.artifact;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.yaal.offlinedocs.api.artifact.Artifact;

/**
 * @author Yablokov Aleksey
 */
@ToString
@EqualsAndHashCode
@Getter
public class ArtifactImpl implements Artifact {
    private String name;
    private String version;
    private int size;

    public ArtifactImpl(String name, String version, int size) {
        this.name = name;
        this.version = version;
        this.size = size;
    }
}
