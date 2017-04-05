package ru.yaal.offlinedocs.impl.execution.id;

import org.junit.Test;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.execution.job.JobId;
import ru.yaal.offlinedocs.impl.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Yablokov Aleksey
 */
public class IdFactoryImplTest extends TestBase {
    @Test
    public void getJobId() {
        JobId jobId1 = idFactory.getJobId(JobA.class);
        assertThat(jobId1.toString(), equalTo("JobA-0"));

        JobId jobId2 = idFactory.getJobId(JobA.class);
        assertThat(jobId2.toString(), equalTo("JobA-1"));

        JobId jobId3 = idFactory.getJobId(JobB.class);
        assertThat(jobId3.toString(), equalTo("JobB-0"));
    }

    private abstract class JobA implements Job {
    }

    private abstract class JobB implements Job {
    }

}