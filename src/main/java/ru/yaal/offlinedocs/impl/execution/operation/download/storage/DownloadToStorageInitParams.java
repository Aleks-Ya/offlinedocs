package ru.yaal.offlinedocs.impl.execution.operation.download.storage;

import lombok.Getter;
import ru.yaal.offlinedocs.impl.execution.operation.download.bytes.DownloadToByteArrayInitParams;

import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
@Getter
public class DownloadToStorageInitParams extends DownloadToByteArrayInitParams {
    private final String artifactCategory;
    private final String artifactName;
    private final String artifactVersion;
    private final String artifactTypeId;
    private final Boolean skipIfExists;

    public DownloadToStorageInitParams(
            String artifactCategory, String artifactName, String artifactVersion, URL artifactUrl, String artifactTypeId, Boolean skipIfExists) {
        super(artifactUrl);
        this.artifactCategory = artifactCategory;
        this.artifactName = artifactName;
        this.artifactVersion = artifactVersion;
        this.artifactTypeId = artifactTypeId;
        this.skipIfExists = skipIfExists;
    }

    public DownloadToStorageInitParams(
            String artifactCategory, String artifactName, String artifactVersion, URL artifactUrl, String artifactTypeId) {
        this(artifactCategory, artifactName, artifactVersion, artifactUrl, artifactTypeId, true);
    }
}
