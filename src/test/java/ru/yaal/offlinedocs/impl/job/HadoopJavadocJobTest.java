package ru.yaal.offlinedocs.impl.job;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Yablokov Aleksey
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class HadoopJavadocJobTest {
    @Autowired
    private HadoopJavadocJob job;

    @Test
    public void name() throws Exception {
        job.execute();
    }
}
