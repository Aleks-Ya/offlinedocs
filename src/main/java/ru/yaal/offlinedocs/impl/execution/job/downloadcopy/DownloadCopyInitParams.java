package ru.yaal.offlinedocs.impl.execution.job.downloadcopy;

import lombok.Getter;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageOp;

/**
 * @author Yablokov Aleksey
 */
@Getter
public class DownloadCopyInitParams implements ru.yaal.offlinedocs.api.execution.InitParams {
    private final DownloadToStorageOp.InitParams downloadParams;

    public DownloadCopyInitParams(DownloadToStorageOp.InitParams downloadParams) {
        this.downloadParams = downloadParams;
    }
}
