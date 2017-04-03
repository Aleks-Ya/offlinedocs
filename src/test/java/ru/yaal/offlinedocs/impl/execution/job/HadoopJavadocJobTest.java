package ru.yaal.offlinedocs.impl.execution.job;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.yaal.offlinedocs.api.execution.Result;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;

/**
 * @author Yablokov Aleksey
 */
public class HadoopJavadocJobTest extends TestBase {
    @Autowired
    @Qualifier("hadoopJavadocJob")
    private Job job;

    @Test
    public void name() throws Exception {
        Result result = job.execute(EmptyExecuteParams.instance);
    }
}
