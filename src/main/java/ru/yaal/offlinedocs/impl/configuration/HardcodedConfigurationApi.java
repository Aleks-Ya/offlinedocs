package ru.yaal.offlinedocs.impl.configuration;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.configuration.ConfigurationApi;
import ru.yaal.offlinedocs.api.job.Job;
import ru.yaal.offlinedocs.api.job.JobParameters;
import ru.yaal.offlinedocs.impl.job.HadoopJavadocJob;
import ru.yaal.offlinedocs.impl.job.HadoopJavadocJobParameters;
import ru.yaal.offlinedocs.impl.operation.download.storage.DownloadToStorageOperationParameters;

import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
@Component
public class HardcodedConfigurationApi implements ConfigurationApi {
    @Override
    @SneakyThrows
    public <P extends JobParameters> P getJobParameters(Class<? extends Job<P, ?>> jobClass) {
        if (HadoopJavadocJob.class.getName().equals(jobClass.getName())) {
            DownloadToStorageOperationParameters downloadToStorageOperationParameters = new DownloadToStorageOperationParameters(
                    "Hadoop Javadoc", "2.8.0",
                    new URL("http://apache-mirror.rbc.ru/pub/apache/hadoop/common/hadoop-2.8.0/hadoop-2.8.0.tar.gz"));
            return (P) new HadoopJavadocJobParameters(downloadToStorageOperationParameters);
        }
        throw new RuntimeException();
    }
}
