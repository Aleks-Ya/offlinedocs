package ru.yaal.offlinedocs.impl.artifact;

import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.ArtifactData;

import java.io.InputStream;

/**
 * @author Yablokov Aleksey
 */
public class InputStreamArtifactData implements ArtifactData {
    private final Artifact artifact;
    private final InputStream is;

    public InputStreamArtifactData(Artifact artifact, InputStream is) {
        this.artifact = artifact;
        this.is = is;
    }

    @Override
    public Artifact getArtifact() {
        return artifact;
    }

    @Override
    public InputStream getData() {
        return is;
    }
}
