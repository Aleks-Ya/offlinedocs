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
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOpResult;
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
    public Job<OpListJob.InitParams, EmptyExecuteParams, EmptyResult> hadoopJavadocJob() {
        DownloadToStorageOp.InitParams initParams = new DownloadToStorageOp.InitParams(
                "Hadoop", "HadoopJavadoc", "2.8.0",
                new URL("http://apache-mirror.rbc.ru/pub/apache/hadoop/common/hadoop-2.8.0/hadoop-2.8.0.tar.gz"),
                "tar.gz");
        Operation<DownloadToStorageOp.InitParams, EmptyExecuteParams, ArtifactDataOpResult> downloadOperation =
                factory.getNewOperation(DownloadToStorageOp.class, initParams);
        OpListJob.InitParams params = new OpListJob.InitParams(Collections.singletonList(downloadOperation));
        return factory.getNewJob(OpListJob.class, params);
    }

    @Bean
    @SneakyThrows
    public Job<DownloadUnzipJob.InitParams, EmptyExecuteParams, EmptyResult> jdk8Documentation() {
        DownloadToStorageOp.InitParams opParams = new DownloadToStorageOp.InitParams(
                "JavaSE", "JdkDocumentation", "8",
                new URL("https://getfile.dokpub.com/yandex/get/https://yadi.sk/d/69Dngbhw3GfQyy"), "zip");
        DownloadUnzipJob.InitParams jobParams = new DownloadUnzipJob.InitParams(opParams);
        return factory.getNewJob(DownloadUnzipJob.class, jobParams);
    }

    @Bean
    @SneakyThrows
    public Job<DownloadCopyJob.InitParams, EmptyExecuteParams, EmptyResult> springReference437Pdf() {
        return makeDownloadCopyJob("Spring", "SpringReferencePdf", "4.3.7",
                "http://docs.spring.io/spring/docs/4.3.7.RELEASE/spring-framework-reference/pdf/spring-framework-reference.pdf",
                "pdf");
    }

    private Job<DownloadCopyJob.InitParams, EmptyExecuteParams, EmptyResult> makeDownloadCopyJob(
            String artifactCategory,
            String artifactName,
            String artifactVersion,
            String artifactUrl,
            String artifactTypeId)
            throws MalformedURLException {

        DownloadToStorageOp.InitParams opParams = new DownloadToStorageOp.InitParams(
                artifactCategory, artifactName, artifactVersion, new URL(artifactUrl), artifactTypeId);
        DownloadCopyJob.InitParams jobParams = new DownloadCopyJob.InitParams(opParams);
        return factory.getNewJob(DownloadCopyJob.class, jobParams);
    }
}
