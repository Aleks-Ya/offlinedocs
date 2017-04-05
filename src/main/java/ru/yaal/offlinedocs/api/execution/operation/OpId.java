package ru.yaal.offlinedocs.api.execution.operation;

import ru.yaal.offlinedocs.api.execution.job.JobId;

/**
 * @author Yablokov Aleksey
 */
public interface OpId {
    JobId getJobId();
}
