package ru.yaal.offlinedocs.api.execution;

import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.execution.job.JobId;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.api.execution.param.ExecParams;
import ru.yaal.offlinedocs.api.execution.param.InitParams;
import ru.yaal.offlinedocs.impl.execution.job.AbstractJob;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOp;

import java.util.List;

/**
 * @author Yablokov Aleksey
 */
public interface ExecFactory {
    <IP extends InitParams, EP extends ExecParams, R extends Result, O extends Operation<IP, EP, R>>
    O getNewOperation(JobId jobId, Class<? extends AbstractOp<IP, EP, R>> operationClass, IP initParams);

    <IP extends InitParams, EP extends ExecParams, R extends Result, J extends Job<IP, EP, R>>
    J getNewJob(Class<? extends AbstractJob<IP, EP, R>> jobClass, IP initParams);

    List<Job<? extends InitParams, ? extends ExecParams, ? extends Result>> getAllJobs();
}
