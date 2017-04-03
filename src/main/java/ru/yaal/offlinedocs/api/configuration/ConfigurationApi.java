package ru.yaal.offlinedocs.api.configuration;

import ru.yaal.offlinedocs.api.execution.ExecuteParams;
import ru.yaal.offlinedocs.api.execution.job.Job;

import java.util.List;

/**
 * @author Yablokov Aleksey
 */
public interface ConfigurationApi {
    <P extends ExecuteParams> P getJobParameters(Class<? extends Job<?, P, ?>> jobClass);

    List<Job> getAvailableJobs();
}
