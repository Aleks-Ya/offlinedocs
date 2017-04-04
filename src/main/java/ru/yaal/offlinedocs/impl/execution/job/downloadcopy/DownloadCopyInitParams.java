package ru.yaal.offlinedocs.impl.execution.job.downloadcopy;

import lombok.Getter;
import ru.yaal.offlinedocs.api.execution.InitParams;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageInitParams;

/**
 * @author Yablokov Aleksey
 */
@Getter
public class DownloadCopyInitParams implements InitParams{
    private final DownloadToStorageInitParams downloadParams;

    public DownloadCopyInitParams(DownloadToStorageInitParams downloadParams) {
        this.downloadParams = downloadParams;
    }
}
