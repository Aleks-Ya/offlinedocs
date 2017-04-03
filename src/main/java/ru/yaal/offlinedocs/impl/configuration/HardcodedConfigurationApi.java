package ru.yaal.offlinedocs.impl.configuration;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.configuration.ConfigurationApi;
import ru.yaal.offlinedocs.api.execution.ExecuteParams;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.impl.execution.job.JobsConfig;
import ru.yaal.offlinedocs.impl.execution.job.hadoop.javadoc.HadoopJavadocJob;
import ru.yaal.offlinedocs.impl.execution.job.hadoop.javadoc.HadoopJavadocJobExecuteParameters;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageExecuteParams;

import java.net.URL;
import java.util.List;

/**
 * @author Yablokov Aleksey
 */
@Component
@Import(JobsConfig.class)
public class HardcodedConfigurationApi implements ConfigurationApi {
//    @Autowired
    private List<Job> availableJobs;

    @Override
    @SneakyThrows
    public <P extends ExecuteParams> P getJobParameters(Class<? extends Job<?, P, ?>> jobClass) {
        if (HadoopJavadocJob.class.getName().equals(jobClass.getName())) {
            DownloadToStorageExecuteParams downloadToStorageOperationParameters = new DownloadToStorageExecuteParams(
                    "Hadoop Javadoc", "2.8.0",
                    new URL("http://apache-mirror.rbc.ru/pub/apache/hadoop/common/hadoop-2.8.0/hadoop-2.8.0.tar.gz"));
            return (P) new HadoopJavadocJobExecuteParameters(downloadToStorageOperationParameters);
        }
        throw new RuntimeException();
    }

    @Override
    public List<Job> getAvailableJobs() {
        return availableJobs;
    }
}
