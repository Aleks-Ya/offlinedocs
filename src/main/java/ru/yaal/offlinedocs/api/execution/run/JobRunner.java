package ru.yaal.offlinedocs.api.execution.run;

import ru.yaal.offlinedocs.api.execution.ExecParams;
import ru.yaal.offlinedocs.api.execution.InitParams;
import ru.yaal.offlinedocs.api.execution.Result;
import ru.yaal.offlinedocs.api.execution.job.Job;

/**
 * @author Yablokov Aleksey
 */
public interface JobRunner {
    <IP extends InitParams, EP extends ExecParams, R extends Result>
    R runJob(Job<IP, EP, R> job, EP execParams);

    Result runJob2(Job job, ExecParams execParams);
}
