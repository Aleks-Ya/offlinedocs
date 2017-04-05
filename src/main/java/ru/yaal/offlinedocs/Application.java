package ru.yaal.offlinedocs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.yaal.offlinedocs.api.execution.ExecuteParams;
import ru.yaal.offlinedocs.api.execution.ExecutionFactory;
import ru.yaal.offlinedocs.api.execution.InitParams;
import ru.yaal.offlinedocs.api.execution.Result;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.spring.Config;
import ru.yaal.offlinedocs.spring.Profiles;

import java.util.List;

/**
 * TODO Don't unpack archive if destination already contains files with the same size
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
        ExecutionFactory factory = context.getBean(ExecutionFactory.class);
        List<Job<? extends InitParams, ? extends ExecuteParams, ? extends Result>> jobs = factory.getAllJobs();
        for (Job job : jobs) {
            job.execute(EmptyExecuteParams.instance);
        }
        log.info("Finish");
    }
}
