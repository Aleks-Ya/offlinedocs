package ru.yaal.offlinedocs.impl.execution.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.yaal.offlinedocs.api.execution.ExecutionFactory;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.EmptyInitParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.job.operationlist.OperationListInitParams;
import ru.yaal.offlinedocs.impl.execution.job.operationlist.OperationListJob;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOperationResult;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageExecuteParams;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageOperation;

import java.util.Collections;

/**
 * @author Yablokov Aleksey
 */
@Configuration
public class JobsConfig {
    private final ExecutionFactory factory;

    @Autowired
    public JobsConfig(ExecutionFactory factory) {
        this.factory = factory;
    }

    @Bean
    public Job<OperationListInitParams, EmptyExecuteParams, EmptyResult> hadoopJavadocJob() {
        Operation<EmptyInitParams, DownloadToStorageExecuteParams, ArtifactDataOperationResult> downloadOperation =
                factory.getNewOperation(DownloadToStorageOperation.class, EmptyInitParams.instance);
        OperationListInitParams params = new OperationListInitParams(Collections.singletonList(downloadOperation));
        return factory.getNewJob(OperationListJob.class, params);
    }

    @Bean
    public Job<OperationListInitParams, EmptyExecuteParams, EmptyResult> springReference437Pdf() {
        Operation<EmptyInitParams, DownloadToStorageExecuteParams, ArtifactDataOperationResult> downloadOperation =
                factory.getNewOperation(DownloadToStorageOperation.class, EmptyInitParams.instance);
        OperationListInitParams params = new OperationListInitParams(Collections.singletonList(downloadOperation));
        return factory.getNewJob(OperationListJob.class, params);
    }
}
