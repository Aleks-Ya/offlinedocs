package ru.yaal.offlinedocs.impl.execution.operation.download.bytes;

import ru.yaal.offlinedocs.api.execution.InitParams;

import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
public class DownloadToByteArrayInitParams implements InitParams {
    private final URL artifactUrl;

    public DownloadToByteArrayInitParams(URL artifactUrl) {
        this.artifactUrl = artifactUrl;
    }

    public URL getURL() {
        return artifactUrl;
    }
}
