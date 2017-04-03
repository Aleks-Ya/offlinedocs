package ru.yaal.offlinedocs.impl.artifact;

import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.ArtifactData;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author Yablokov Aleksey
 */
public class ByteArrayArtifactData implements ArtifactData {
    private final Artifact artifact;
    private final byte[] bytes;

    public ByteArrayArtifactData(Artifact artifact, byte[] bytes) {
        this.artifact = artifact;
        this.bytes = bytes;
    }

    @Override
    public Artifact getArtifact() {
        return artifact;
    }

    @Override
    public InputStream getData() {
        return new ByteArrayInputStream(bytes);
    }
}
