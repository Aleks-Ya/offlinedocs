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
import ru.yaal.offlinedocs.impl.execution.job.downloadcopy.DownloadCopyInitParams;
import ru.yaal.offlinedocs.impl.execution.job.downloadcopy.DownloadCopyJob;
import ru.yaal.offlinedocs.impl.execution.job.operationlist.OpListInitParams;
import ru.yaal.offlinedocs.impl.execution.job.operationlist.OpListJob;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOpResult;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageInitParams;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageOp;

import java.net.MalformedURLException;
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
    public Job<OpListInitParams, EmptyExecuteParams, EmptyResult> hadoopJavadocJob() {
        DownloadToStorageInitParams initParams = new DownloadToStorageInitParams(
                "Hadoop", "HadoopJavadoc", "2.8.0",
                new URL("http://apache-mirror.rbc.ru/pub/apache/hadoop/common/hadoop-2.8.0/hadoop-2.8.0.tar.gz"),
                "tar.gz");
        Operation<DownloadToStorageInitParams, EmptyExecuteParams, ArtifactDataOpResult> downloadOperation =
                factory.getNewOperation(DownloadToStorageOp.class, initParams);
        OpListInitParams params = new OpListInitParams(Collections.singletonList(downloadOperation));
        return factory.getNewJob(OpListJob.class, params);
    }

    @Bean
    @SneakyThrows
    public Job<DownloadCopyInitParams, EmptyExecuteParams, EmptyResult> springReference437Pdf() {
        return makeDownloadCopyJob("Spring", "SpringReferencePdf", "4.3.7",
                "http://docs.spring.io/spring/docs/4.3.7.RELEASE/spring-framework-reference/pdf/spring-framework-reference.pdf",
                "pdf");
    }

    private Job<DownloadCopyInitParams, EmptyExecuteParams, EmptyResult> makeDownloadCopyJob(
            String artifactCategory,
            String artifactName,
            String artifactVersion,
            String artifactUrl,
            String artifactTypeId)
            throws MalformedURLException {

        DownloadToStorageInitParams opParams = new DownloadToStorageInitParams(
                artifactCategory, artifactName, artifactVersion, new URL(artifactUrl), artifactTypeId);
        DownloadCopyInitParams jobParams = new DownloadCopyInitParams(opParams);
        return factory.getNewJob(DownloadCopyJob.class, jobParams);
    }
}
