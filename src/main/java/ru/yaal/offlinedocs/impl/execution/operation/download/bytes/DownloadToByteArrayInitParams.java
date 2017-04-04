package ru.yaal.offlinedocs.impl.execution.operation.download.bytes;

import lombok.Getter;
import ru.yaal.offlinedocs.api.execution.InitParams;

import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
@Getter
public class DownloadToByteArrayInitParams implements InitParams {
    private final URL artifactUrl;
    private final int logEveryBytes;

    public DownloadToByteArrayInitParams(URL artifactUrl) {
        this.artifactUrl = artifactUrl;
        logEveryBytes = 1_000_000;
    }

    public DownloadToByteArrayInitParams(URL artifactUrl, int logEveryBytes) {
        this.artifactUrl = artifactUrl;
        this.logEveryBytes = logEveryBytes;
    }
}
