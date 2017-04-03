package ru.yaal.offlinedocs.impl.job;

import ru.yaal.offlinedocs.api.job.JobParameters;
import ru.yaal.offlinedocs.impl.operation.download.storage.DownloadToStorageOperationParameters;

/**
 * @author Yablokov Aleksey
 */
public class HadoopJavadocJobParameters implements JobParameters {
    private final DownloadToStorageOperationParameters downloadToStorageOperationParameters;

    public HadoopJavadocJobParameters(DownloadToStorageOperationParameters downloadToStorageOperationParameters) {
        this.downloadToStorageOperationParameters = downloadToStorageOperationParameters;
    }

    DownloadToStorageOperationParameters getDownloadToStorageOperationParameters() {
        return downloadToStorageOperationParameters;
    }
}
