package ru.yaal.offlinedocs.impl.artifact.data;

import lombok.Getter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * TODO work with temp files/dirs via TempStorage
 *
 * @author Yablokov Aleksey
 */
public class ByteArrayArtifactData implements ArtifactData {
    public static final Logger LOG = LoggerFactory.getLogger(ByteArrayArtifactData.class);
    @Getter
    private final Artifact artifact;
    private final File artifactFile;

    @SneakyThrows
    public ByteArrayArtifactData(Artifact artifact, byte[] bytes) {
        this.artifact = artifact;
        artifactFile = File.createTempFile("ByteArrayArtifactData_", ".tmp");
        artifactFile.deleteOnExit();
        Files.write(artifactFile.toPath(), bytes);
        LOG.debug("Temp file created: " + artifactFile.getAbsolutePath());
    }

    @Override
    @SneakyThrows
    public InputStream getInputStream() {
        return new FileInputStream(artifactFile);
    }

    @Override
    public File getFile() {
        return artifactFile;
    }
}
