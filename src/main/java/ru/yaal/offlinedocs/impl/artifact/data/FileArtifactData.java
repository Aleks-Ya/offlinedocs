package ru.yaal.offlinedocs.impl.artifact.data;

import lombok.Getter;
import lombok.SneakyThrows;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Yablokov Aleksey
 */
public class FileArtifactData implements ArtifactData {
    @Getter
    private final Artifact artifact;
    private final File artifactFile;

    public FileArtifactData(Artifact artifact, File artifactFile) {
        this.artifact = artifact;
        this.artifactFile = artifactFile;
    }

    @SneakyThrows
    public InputStream getData() {
        return new FileInputStream(artifactFile);
    }
}
