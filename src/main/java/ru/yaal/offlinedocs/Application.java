package ru.yaal.offlinedocs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.yaal.offlinedocs.api.execution.ExecFactory;
import ru.yaal.offlinedocs.api.execution.run.JobRunner;
import ru.yaal.offlinedocs.impl.execution.EmptyExecParams;
import ru.yaal.offlinedocs.spring.Config;
import ru.yaal.offlinedocs.spring.Profiles;

/**
 * TODO Don't unpack archive if destination already contains files with the same size
 * TODO native packet for Windows, Linux, MacOS
 *
 * @author Yablokov Aleksey
 */
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        log.debug("Star");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles(Profiles.PROD);
        context.register(Config.class);
        context.refresh();
        context.start();
        log.info("Spring Context started");
        ExecFactory factory = context.getBean(ExecFactory.class);
        //TODO implement concurrent job execution with JobRunner
        //TODO exception in a job doesn't crashes whole application
        JobRunner runner = context.getBean(JobRunner.class);
        factory.getAllJobs().forEach(job -> runner.runJob2(job, EmptyExecParams.instance));
        log.info("Finish");
    }
}
