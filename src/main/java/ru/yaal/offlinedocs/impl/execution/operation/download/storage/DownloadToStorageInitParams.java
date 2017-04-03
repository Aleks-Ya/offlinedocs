package ru.yaal.offlinedocs.impl.execution.operation.download.storage;

import ru.yaal.offlinedocs.impl.execution.operation.download.bytes.DownloadToByteArrayInitParams;

import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
public class DownloadToStorageInitParams extends DownloadToByteArrayInitParams {
    private final String artifactName;
    private final String artifactVersion;

    public DownloadToStorageInitParams(String artifactName, String artifactVersion, URL artifactUrl) {
        super(artifactUrl);
        this.artifactName = artifactName;
        this.artifactVersion = artifactVersion;
    }

    public String getArtifactVersion() {
        return artifactVersion;
    }

    public String getArtifactName() {
        return artifactName;
    }
}
