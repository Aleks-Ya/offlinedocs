package ru.yaal.offlinedocs.impl.execution.job;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yaal.offlinedocs.api.execution.Result;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.job.hadoop.javadoc.HadoopJavadocJob;

/**
 * @author Yablokov Aleksey
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class HadoopJavadocJobTest {
    @Autowired
    @Qualifier("hadoopJavadocJob")
    private Job job;

    @Test
    public void name() throws Exception {
        Result result = job.execute(EmptyExecuteParams.instance);
    }
}
