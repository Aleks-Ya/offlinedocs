package ru.yaal.offlinedocs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.yaal.offlinedocs.api.execution.ExecFactory;
import ru.yaal.offlinedocs.api.execution.ExecParams;
import ru.yaal.offlinedocs.api.execution.InitParams;
import ru.yaal.offlinedocs.api.execution.Result;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.impl.execution.EmptyExecParams;
import ru.yaal.offlinedocs.spring.Config;
import ru.yaal.offlinedocs.spring.Profiles;

import java.util.List;

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
        List<Job<? extends InitParams, ? extends ExecParams, ? extends Result>> jobs = factory.getAllJobs();
        //TODO use JobRunner for executing jobs
        //TODO implement concurrent job execution with JobRunner
        //TODO exception in a job doesn't crashes whole application
        for (Job job : jobs) {
            job.execute(EmptyExecParams.instance);
        }
        log.info("Finish");
    }
}
