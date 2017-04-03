package ru.yaal.offlinedocs.impl.artifact.data;

import lombok.Getter;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;

import java.io.InputStream;

/**
 * @author Yablokov Aleksey
 */
@Getter
public class InputStreamArtifactData implements ArtifactData {
    private final Artifact artifact;
    private final InputStream data;

    public InputStreamArtifactData(Artifact artifact, InputStream data) {
        this.artifact = artifact;
        this.data = data;
    }
}
