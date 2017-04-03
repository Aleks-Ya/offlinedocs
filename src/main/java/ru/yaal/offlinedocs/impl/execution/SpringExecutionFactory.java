package ru.yaal.offlinedocs.impl.execution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.execution.ExecuteParams;
import ru.yaal.offlinedocs.api.execution.ExecutionFactory;
import ru.yaal.offlinedocs.api.execution.InitParams;
import ru.yaal.offlinedocs.api.execution.Result;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.impl.execution.job.AbstractJob;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOperation;

/**
 * @author Yablokov Aleksey
 */
@Component
public class SpringExecutionFactory implements ExecutionFactory {
    private final ApplicationContext ctx;

    @Autowired
    public SpringExecutionFactory(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public <IP extends InitParams, EP extends ExecuteParams, R extends Result, O extends Operation<IP, EP, R>>
    O getNewOperation(Class<? extends AbstractOperation<IP, EP, R>> operationClass, IP initParams) {
        //noinspection unchecked
        return (O) ctx.getBean(operationClass, initParams);
    }

    @Override
    public <IP extends InitParams, EP extends ExecuteParams, R extends Result, J extends Job<IP, EP, R>>
    J getNewJob(Class<? extends AbstractJob<IP, EP, R>> jobClass, IP initParams) {
        //noinspection unchecked
        return (J) ctx.getBean(jobClass, initParams);
    }
}
