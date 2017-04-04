package ru.yaal.offlinedocs.impl.execution.job;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.yaal.offlinedocs.api.execution.ExecutionFactory;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.job.operationlist.OperationListInitParams;
import ru.yaal.offlinedocs.impl.execution.job.operationlist.OperationListJob;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOperationResult;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageInitParams;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageOperation;

import java.net.URL;
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
    @SneakyThrows
    public Job<OperationListInitParams, EmptyExecuteParams, EmptyResult> hadoopJavadocJob() {
        DownloadToStorageInitParams initParams = new DownloadToStorageInitParams(
                "Hadoop", "HadoopJavadoc", "2.8.0",
                new URL("http://apache-mirror.rbc.ru/pub/apache/hadoop/common/hadoop-2.8.0/hadoop-2.8.0.tar.gz"),
                "tar.gz");
        Operation<DownloadToStorageInitParams, EmptyExecuteParams, ArtifactDataOperationResult> downloadOperation =
                factory.getNewOperation(DownloadToStorageOperation.class, initParams);
        OperationListInitParams params = new OperationListInitParams(Collections.singletonList(downloadOperation));
        return factory.getNewJob(OperationListJob.class, params);
    }

    @Bean
    @SneakyThrows
    public Job<OperationListInitParams, EmptyExecuteParams, EmptyResult> springReference437Pdf() {
        DownloadToStorageInitParams initParams = new DownloadToStorageInitParams(
                "Spring", "SpringReferencePdf", "4.3.7",
                new URL("http://docs.spring.io/spring/docs/4.3.7.RELEASE/spring-framework-reference/pdf/spring-framework-reference.pdf"),
                "pdf");
        Operation<DownloadToStorageInitParams, EmptyExecuteParams, ArtifactDataOperationResult> downloadOperation =
                factory.getNewOperation(DownloadToStorageOperation.class, initParams);
        OperationListInitParams params = new OperationListInitParams(Collections.singletonList(downloadOperation));
        return factory.getNewJob(OperationListJob.class, params);
    }
}
