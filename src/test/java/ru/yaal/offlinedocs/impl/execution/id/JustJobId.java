package ru.yaal.offlinedocs.impl.execution.id;

import ru.yaal.offlinedocs.api.execution.job.JobId;

/**
 * @author Yablokov Aleksey
 */
public class JustJobId {
    public static final JobId jobId = new JobIdImpl("JustJobId");

    private JustJobId() {
    }
}
