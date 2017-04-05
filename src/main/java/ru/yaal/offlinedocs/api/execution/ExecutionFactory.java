package ru.yaal.offlinedocs.api.execution;

import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.impl.execution.job.AbstractJob;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOp;

import java.util.List;

/**
 * TODO rename "execution" to "exec"
 *
 * @author Yablokov Aleksey
 */
public interface ExecutionFactory {
    <IP extends InitParams, EP extends ExecuteParams, R extends Result, O extends Operation<IP, EP, R>>
    O getNewOperation(Class<? extends AbstractOp<IP, EP, R>> operationClass, IP initParams);

    <IP extends InitParams, EP extends ExecuteParams, R extends Result, J extends Job<IP, EP, R>>
    J getNewJob(Class<? extends AbstractJob<IP, EP, R>> jobClass, IP initParams);

    List<Job<? extends InitParams, ? extends ExecuteParams, ? extends Result>> getAllJobs();
}
