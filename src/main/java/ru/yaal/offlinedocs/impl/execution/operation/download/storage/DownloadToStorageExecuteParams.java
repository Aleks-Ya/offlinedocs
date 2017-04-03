package ru.yaal.offlinedocs.impl.execution.operation.download.storage;

import ru.yaal.offlinedocs.impl.execution.operation.download.bytes.DownloadToByteArrayExecuteParams;

import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
public class DownloadToStorageExecuteParams extends DownloadToByteArrayExecuteParams {
    private final String artifactName;
    private final String artifactVersion;

    public DownloadToStorageExecuteParams(String artifactName, String artifactVersion, URL artifactUrl) {
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
