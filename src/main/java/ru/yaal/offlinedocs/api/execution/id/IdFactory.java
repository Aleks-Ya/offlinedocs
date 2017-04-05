package ru.yaal.offlinedocs.api.execution.id;

import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.execution.job.JobId;

/**
 * @author Yablokov Aleksey
 */
public interface IdFactory {
    JobId getJobId(Class<? extends Job> jobClass);
}
