package ru.yaal.offlinedocs.api.storage;

import ru.yaal.offlinedocs.api.execution.job.JobId;
import ru.yaal.offlinedocs.api.execution.operation.OpId;

import java.io.File;

/**
 * @author Yablokov Aleksey
 */
public interface TempStorage {
    File getJobTempDir(JobId jobId);

    File getOpTempDir(OpId opId);
}
