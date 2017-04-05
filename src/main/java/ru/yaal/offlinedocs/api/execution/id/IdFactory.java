package ru.yaal.offlinedocs.api.execution.id;

import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.execution.job.JobId;
import ru.yaal.offlinedocs.api.execution.operation.OpId;
import ru.yaal.offlinedocs.api.execution.operation.Operation;

/**
 * @author Yablokov Aleksey
 */
public interface IdFactory {
    JobId getJobId(Class<? extends Job> jobClass);

    OpId getOpId(JobId jobId, Class<? extends Operation> jobClass);
}
