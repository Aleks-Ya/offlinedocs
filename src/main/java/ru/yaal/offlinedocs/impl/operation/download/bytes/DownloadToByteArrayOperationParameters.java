package ru.yaal.offlinedocs.impl.operation.download.bytes;

import ru.yaal.offlinedocs.api.operation.OperationParameters;

import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
public class DownloadToByteArrayOperationParameters implements OperationParameters {
    private final URL artifactUrl;

    public DownloadToByteArrayOperationParameters(URL artifactUrl) {
        this.artifactUrl = artifactUrl;
    }

    public URL getURL() {
        return artifactUrl;
    }
}
