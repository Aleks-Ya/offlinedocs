package ru.yaal.offlinedocs.impl.execution.job.hadoop.javadoc;

import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageExecuteParams;

/**
 * @author Yablokov Aleksey
 */
@Deprecated
public class HadoopJavadocJobExecuteParameters {
    private final DownloadToStorageExecuteParams downloadToStorageOperationParameters;

    public HadoopJavadocJobExecuteParameters(DownloadToStorageExecuteParams downloadToStorageOperationParameters) {
        this.downloadToStorageOperationParameters = downloadToStorageOperationParameters;
    }

    DownloadToStorageExecuteParams getDownloadToStorageOperationParameters() {
        return downloadToStorageOperationParameters;
    }
}
