package ru.yaal.offlinedocs.api.configuration;

import ru.yaal.offlinedocs.api.job.Job;
import ru.yaal.offlinedocs.api.job.JobParameters;

/**
 * @author Yablokov Aleksey
 */
public interface ConfigurationApi {
    <P extends JobParameters> P getJobParameters(Class<? extends Job<P, ?>> jobClass);
}
