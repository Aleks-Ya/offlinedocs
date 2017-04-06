package ru.yaal.offlinedocs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.yaal.offlinedocs.api.execution.ExecFactory;
import ru.yaal.offlinedocs.api.execution.run.JobRunner;
import ru.yaal.offlinedocs.impl.execution.param.EmptyExecParams;
import ru.yaal.offlinedocs.spring.Config;
import ru.yaal.offlinedocs.spring.Profiles;

/**
 * TODO Don't unpack archive if destination already contains files with the same size
 * TODO native packet for Windows, Linux, MacOS
 *
 * @author Yablokov Aleksey
 */
public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOG.debug("Star");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles(Profiles.PROD);
        context.register(Config.class);
        context.refresh();
        context.start();
        LOG.info("Spring Context started");
        ExecFactory factory = context.getBean(ExecFactory.class);
        //TODO implement concurrent job execution with JobRunner
        //TODO exception in a job doesn't crashes whole application
        JobRunner runner = context.getBean(JobRunner.class);
        factory.getAllJobs().forEach(job -> {
            try {
                runner.runJob2(job, EmptyExecParams.instance);
            } catch (Exception e) {
                LOG.error("Job failed: " + e.getMessage(), e);
            }
        });
        LOG.info("Finish");
    }
}
