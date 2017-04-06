package ru.yaal.offlinedocs.api.execution.job;

import ru.yaal.offlinedocs.api.execution.param.ExecParams;
import ru.yaal.offlinedocs.api.execution.param.InitParams;
import ru.yaal.offlinedocs.api.execution.Result;

/**
 * TODO Job extends Callable
 *
 * @author Yablokov Aleksey
 */
public interface Job<IP extends InitParams, EP extends ExecParams, R extends Result> {
    R execute(EP execParams);

    JobId getJobId();
}
