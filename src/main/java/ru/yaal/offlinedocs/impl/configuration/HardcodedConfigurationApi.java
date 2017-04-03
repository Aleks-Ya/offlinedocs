package ru.yaal.offlinedocs.impl.configuration;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.configuration.ConfigurationApi;
import ru.yaal.offlinedocs.api.execution.ExecuteParams;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.impl.execution.job.JobsConfig;

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
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Job> getAvailableJobs() {
        return availableJobs;
    }
}
