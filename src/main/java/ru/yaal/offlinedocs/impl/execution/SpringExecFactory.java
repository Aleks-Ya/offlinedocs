package ru.yaal.offlinedocs.impl.execution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.execution.ExecFactory;
import ru.yaal.offlinedocs.api.execution.param.ExecParams;
import ru.yaal.offlinedocs.api.execution.param.InitParams;
import ru.yaal.offlinedocs.api.execution.Result;
import ru.yaal.offlinedocs.api.execution.id.IdFactory;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.execution.job.JobId;
import ru.yaal.offlinedocs.api.execution.operation.OpId;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.impl.execution.job.AbstractJob;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOp;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO can remove ExecFactory if make all operations and jobs thread safety and autowire them (?)
 *
 * @author Yablokov Aleksey
 */
@Component
@SuppressWarnings("unchecked")
public class SpringExecFactory implements ExecFactory {
    private final ApplicationContext ctx;
    private final IdFactory idFactory;
    private List<Job<? extends InitParams, ? extends ExecParams, ? extends Result>> allJobs = new ArrayList<>();

    @Autowired
    public SpringExecFactory(ApplicationContext ctx, IdFactory idFactory) {
        this.ctx = ctx;
        this.idFactory = idFactory;
    }

    @Override
    public <IP extends InitParams, EP extends ExecParams, R extends Result, O extends Operation<IP, EP, R>>
    O getNewOperation(JobId jobId, Class<? extends AbstractOp<IP, EP, R>> opClass, IP initParams) {
        OpId opId = idFactory.getOpId(jobId, opClass);
        return (O) ctx.getBean(opClass, opId, initParams);
    }

    @Override
    public <IP extends InitParams, EP extends ExecParams, R extends Result, J extends Job<IP, EP, R>>
    J getNewJob(Class<? extends AbstractJob<IP, EP, R>> jobClass, IP initParams) {
        JobId jobId = idFactory.getJobId(jobClass);
        J job = (J) ctx.getBean(jobClass, jobId, initParams);
        allJobs.add(job);
        return job;
    }

    @Override
    public List<Job<? extends InitParams, ? extends ExecParams, ? extends Result>> getAllJobs() {
        return allJobs;
    }
}
