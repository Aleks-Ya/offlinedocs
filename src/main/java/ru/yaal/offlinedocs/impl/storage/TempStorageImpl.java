package ru.yaal.offlinedocs.impl.storage;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.execution.job.JobId;
import ru.yaal.offlinedocs.api.execution.operation.OpId;
import ru.yaal.offlinedocs.api.properties.DataAppProps;
import ru.yaal.offlinedocs.api.storage.TempStorage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO make TempStorageImpl thread safety
 * TODO clean temp dir while application is stopping
 *
 * @author Yablokov Aleksey
 */
@Component
@SuppressWarnings("ResultOfMethodCallIgnored")
class TempStorageImpl implements TempStorage {
    private static final Logger LOG = LoggerFactory.getLogger(TempStorageImpl.class);
    private final File tempDir;
    private final File createdFileDir;
    private final Map<JobId, File> jobDirs = new HashMap<>();
    private final Map<OpId, File> opDirs = new HashMap<>();

    @Autowired
    public TempStorageImpl(DataAppProps props) {
        tempDir = props.getTempDir();
        tempDir.delete();
        tempDir.mkdirs();
        LOG.debug("Temp dir cleaned: " + tempDir.getAbsolutePath());

        createdFileDir = new File(tempDir, "created_files");
        createdFileDir.mkdirs();
        LOG.debug("Created files temp dir: " + createdFileDir);
    }

    @Override
    public File getJobTempDir(JobId jobId) {
        File dir = jobDirs.get(jobId);
        if (dir == null) {
            dir = new File(tempDir, jobId.toString());
            jobDirs.put(jobId, dir);
            dir.mkdirs();
            dir.deleteOnExit();
            LOG.debug("Temp job dir created: " + dir.getAbsolutePath());
        }
        return dir;
    }

    @Override
    public File getOpTempDir(OpId opId) {
        File dir = opDirs.get(opId);
        if (dir == null) {
            dir = new File(getJobTempDir(opId.getJobId()), opId.getJobId().toString());
            opDirs.put(opId, dir);
            dir.mkdirs();
            dir.deleteOnExit();
            LOG.debug("Temp op dir created: " + dir.getAbsolutePath());
        }
        return dir;
    }

    @Override
    @SneakyThrows
    public File createTempFile() {
        return File.createTempFile("CreatedFile_", ".tmp", createdFileDir);
    }
}
