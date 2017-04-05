package ru.yaal.offlinedocs.impl.execution.id;

import org.junit.Test;
import ru.yaal.offlinedocs.api.execution.id.IdFactory;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.execution.job.JobId;
import ru.yaal.offlinedocs.api.execution.operation.OpId;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.impl.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Yablokov Aleksey
 */
public class IdFactoryImplTest extends TestBase {
    @Test
    public void getJobId() {
        IdFactory idFactory = new IdFactoryImpl();
        JobId jobId1 = idFactory.getJobId(JobA.class);
        assertThat(jobId1.toString(), equalTo("JobA-0"));

        JobId jobId2 = idFactory.getJobId(JobA.class);
        assertThat(jobId2.toString(), equalTo("JobA-1"));

        JobId jobId3 = idFactory.getJobId(JobB.class);
        assertThat(jobId3.toString(), equalTo("JobB-0"));
    }

    @Test
    public void getOpId() {
        IdFactory idFactory = new IdFactoryImpl();

        JobId jobIdA = idFactory.getJobId(JobA.class);
        OpId opId0 = idFactory.getOpId(jobIdA, OpA.class);
        assertThat(opId0.toString(), equalTo("JobA-0_OpA-0"));

        OpId opId1 = idFactory.getOpId(jobIdA, OpA.class);
        assertThat(opId1.toString(), equalTo("JobA-0_OpA-1"));

        OpId opId2 = idFactory.getOpId(jobIdA, OpB.class);
        assertThat(opId2.toString(), equalTo("JobA-0_OpB-0"));
    }

    private abstract class JobA implements Job {
    }

    private abstract class JobB implements Job {
    }

    private abstract class OpA implements Operation {
    }

    private abstract class OpB implements Operation {
    }

}