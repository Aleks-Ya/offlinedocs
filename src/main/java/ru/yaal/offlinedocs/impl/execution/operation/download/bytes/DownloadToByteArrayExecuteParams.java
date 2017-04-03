package ru.yaal.offlinedocs.impl.execution.operation.download.bytes;

import ru.yaal.offlinedocs.api.execution.ExecuteParams;

import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
public class DownloadToByteArrayExecuteParams implements ExecuteParams {
    private final URL artifactUrl;

    public DownloadToByteArrayExecuteParams(URL artifactUrl) {
        this.artifactUrl = artifactUrl;
    }

    public URL getURL() {
        return artifactUrl;
    }
}
