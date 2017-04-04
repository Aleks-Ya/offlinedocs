package ru.yaal.offlinedocs.impl.artifact;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.type.ArtifactType;

/**
 * @author Yablokov Aleksey
 */
@EqualsAndHashCode
@ToString
@Getter
public class ArtifactImpl implements Artifact {
    private String category;
    private String name;
    private String version;
    private ArtifactType type;

    public ArtifactImpl(String category, String name, String version, ArtifactType type) {
        this.category = category;
        this.name = name;
        this.version = version;
        this.type = type;
    }
}
