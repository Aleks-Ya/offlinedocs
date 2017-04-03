package ru.yaal.offlinedocs.impl.execution.job;

import ru.yaal.offlinedocs.api.execution.ExecuteParams;
import ru.yaal.offlinedocs.api.execution.InitParams;
import ru.yaal.offlinedocs.api.execution.Result;
import ru.yaal.offlinedocs.api.execution.job.Job;

/**
 * @author Yablokov Aleksey
 */
public abstract class AbstractJob<
        IP extends InitParams,
        EP extends ExecuteParams,
        R extends Result>
        implements Job<IP, EP, R> {

    private final IP initParameters;

    protected AbstractJob(IP initParams) {
        this.initParameters = initParams;
    }

    public IP getInitParameters() {
        return initParameters;
    }
}
