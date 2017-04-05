package ru.yaal.offlinedocs.impl.storage;

import org.junit.Test;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.execution.id.JustId;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.io.FileMatchers.anExistingDirectory;

/**
 * @author Yablokov Aleksey
 */
public class TempStorageImplTest extends TestBase {
    @Test
    public void getJobTempDir() {
        File dir = tempStorage.getJobTempDir(JustId.jobId);
        assertThat(dir, anExistingDirectory());
    }

    @Test
    public void getOperationTempDir() {
        File dir = tempStorage.getOpTempDir(JustId.opId);
        assertThat(dir, anExistingDirectory());
    }

}