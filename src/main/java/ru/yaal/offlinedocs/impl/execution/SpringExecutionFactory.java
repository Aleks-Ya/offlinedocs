package ru.yaal.offlinedocs.impl.execution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.execution.ExecutionFactory;
import ru.yaal.offlinedocs.api.execution.InitParams;
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
    public <O extends Operation> O getNewOperation(Class<? extends AbstractOperation> operationClass, InitParams initParams) {
        return (O) ctx.getBean(operationClass, initParams);
    }

    @Override
    public <J extends Job> J getNewJob(Class<? extends AbstractJob> jobClass, InitParams initParams) {
        return (J) ctx.getBean(jobClass, initParams);
    }
}
