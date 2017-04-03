package ru.yaal.offlinedocs.impl.execution.job.hadoop.javadoc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.configuration.ConfigurationApi;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOperationResult;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageOperation;

/**
 * @author Yablokov Aleksey
 */
//@Component
@Deprecated
public class HadoopJavadocJob
//        implements Job<HadoopJavadocJobExecuteParameters, HadoopJavadocResult>
{
//    private final Logger LOG = LoggerFactory.getLogger(HadoopJavadocJob.class);
//    private final ConfigurationApi configurationApi;
//    private final DownloadToStorageOperation downloadToStorageOperation;
//
//    @Autowired
//    public HadoopJavadocJob(ConfigurationApi configurationApi, DownloadToStorageOperation downloadToStorageOperation) {
//        this.configurationApi = configurationApi;
//        this.downloadToStorageOperation = downloadToStorageOperation;
//    }
//
//    @Override
//    public HadoopJavadocResult execute() {
//        LOG.debug("Job started " + getClass().getSimpleName());
//        HadoopJavadocJobExecuteParameters parameters = configurationApi.getJobParameters(HadoopJavadocJob.class);
//        ArtifactDataOperationResult result = downloadToStorageOperation.execute(parameters.getDownloadToStorageOperationParameters());
//        LOG.debug("Job finished " + getClass().getSimpleName());
//        return new HadoopJavadocResult();
//    }
}
