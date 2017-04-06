package ru.yaal.offlinedocs.impl.execution.run;

import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.execution.param.ExecParams;
import ru.yaal.offlinedocs.api.execution.param.InitParams;
import ru.yaal.offlinedocs.api.execution.Result;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.execution.run.JobRunner;

/**
 * @author Yablokov Aleksey
 */
@Component
public class ConsequentJobRunner implements JobRunner {
    @Override
    public <IP extends InitParams, EP extends ExecParams, R extends Result> R runJob(Job<IP, EP, R> job, EP execParams) {
        return job.execute(execParams);
    }

    @Override
    public Result runJob2(Job job, ExecParams execParams) {
        return job.execute(execParams);
    }
}
