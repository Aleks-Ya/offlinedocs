package ru.yaal.offlinedocs.impl.execution.id;

import ru.yaal.offlinedocs.api.execution.job.JobId;
import ru.yaal.offlinedocs.api.execution.operation.OpId;

/**
 * @author Yablokov Aleksey
 */
public class JustId {
    public static final JobId jobId = new JobIdImpl("JustJobId");
    public static final OpId opId = new OpIdImpl(jobId, "JustOpId");

    private JustId() {
    }
}
