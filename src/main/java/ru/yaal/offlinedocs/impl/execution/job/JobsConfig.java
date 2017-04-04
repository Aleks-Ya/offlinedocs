package ru.yaal.offlinedocs.impl.execution.job;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.yaal.offlinedocs.api.execution.ExecutionFactory;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageOp;

import java.net.URL;

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
    public Job<DownloadUnTarGzJob.InitParams, EmptyExecuteParams, EmptyResult> hadoopJavadocJob() {
        return makeDownloadUnTarGzJob("Hadoop", "HadoopJavadoc", "2.8.0",
                "http://apache-mirror.rbc.ru/pub/apache/hadoop/common/hadoop-2.8.0/hadoop-2.8.0.tar.gz",
                "tar.gz");
    }

    @Bean
    public Job<DownloadUnzipJob.InitParams, EmptyExecuteParams, EmptyResult> jdk8Documentation() {
        return makeDownloadUnzipJob(
                "JavaSE",
                "JdkDocumentation",
                "8",
                "https://getfile.dokpub.com/yandex/get/https://yadi.sk/d/69Dngbhw3GfQyy",
                "zip");
    }

    @Bean
    public Job<DownloadUnzipJob.InitParams, EmptyExecuteParams, EmptyResult> hiveJavadoc() {
        return makeDownloadUnzipJob(
                "Hive",
                "HiveJavadoc",
                "2.2.1",
                "https://repository.apache.org/service/local/repositories/releases/content/org/apache/hive/hive-storage-api/2.2.1/hive-storage-api-2.2.1-javadoc.jar",
                "jar");
    }

    @SneakyThrows
    private Job<DownloadUnTarGzJob.InitParams, EmptyExecuteParams, EmptyResult> makeDownloadUnTarGzJob(
            String artifactCategory,
            String artifactName,
            String artifactVersion,
            String artifactUrl,
            String artifactTypeId) {

        DownloadToStorageOp.InitParams opParams = new DownloadToStorageOp.InitParams(
                artifactCategory, artifactName, artifactVersion,
                new URL(artifactUrl), artifactTypeId);
        DownloadUnTarGzJob.InitParams jobParams = new DownloadUnTarGzJob.InitParams(opParams);
        return factory.getNewJob(DownloadUnTarGzJob.class, jobParams);
    }

    @SneakyThrows
    private Job<DownloadUnzipJob.InitParams, EmptyExecuteParams, EmptyResult> makeDownloadUnzipJob(
            String artifactCategory,
            String artifactName,
            String artifactVersion,
            String artifactUrl,
            String artifactTypeId) {

        DownloadToStorageOp.InitParams opParams = new DownloadToStorageOp.InitParams(
                artifactCategory, artifactName, artifactVersion,
                new URL(artifactUrl), artifactTypeId);
        DownloadUnzipJob.InitParams jobParams = new DownloadUnzipJob.InitParams(opParams);
        return factory.getNewJob(DownloadUnzipJob.class, jobParams);
    }

    @Bean
    public Job<DownloadCopyJob.InitParams, EmptyExecuteParams, EmptyResult> springReference437Pdf() {
        return makeDownloadCopyJob("Spring", "SpringReferencePdf", "4.3.7",
                "http://docs.spring.io/spring/docs/4.3.7.RELEASE/spring-framework-reference/pdf/spring-framework-reference.pdf",
                "pdf");
    }

    @SneakyThrows
    private Job<DownloadCopyJob.InitParams, EmptyExecuteParams, EmptyResult> makeDownloadCopyJob(
            String artifactCategory,
            String artifactName,
            String artifactVersion,
            String artifactUrl,
            String artifactTypeId) {

        DownloadToStorageOp.InitParams opParams = new DownloadToStorageOp.InitParams(
                artifactCategory, artifactName, artifactVersion, new URL(artifactUrl), artifactTypeId);
        DownloadCopyJob.InitParams jobParams = new DownloadCopyJob.InitParams(opParams);
        return factory.getNewJob(DownloadCopyJob.class, jobParams);
    }
}
