package ru.yaal.offlinedocs.impl.operation.download.storage;

import ru.yaal.offlinedocs.impl.operation.download.bytes.DownloadToByteArrayOperationParameters;

import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
public class DownloadToStorageOperationParameters extends DownloadToByteArrayOperationParameters {
    private final String artifactName;
    private final String artifactVersion;

    public DownloadToStorageOperationParameters(String artifactName, String artifactVersion, URL artifactUrl) {
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
