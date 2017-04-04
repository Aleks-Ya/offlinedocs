package ru.yaal.offlinedocs.impl.execution.job.operationlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.job.AbstractJob;

/**
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
public class OpListJob extends AbstractJob<OpListInitParams, EmptyExecuteParams, EmptyResult> {
    private final Logger LOG = LoggerFactory.getLogger(OpListJob.class);

    public OpListJob(OpListInitParams initParams) {
        super(initParams);
    }

    @Override
    public EmptyResult execute(EmptyExecuteParams executeParams) {
        LOG.debug("Executing operations");
        getInitParams().getOps().forEach(operation -> operation.execute(EmptyExecuteParams.instance));
        return EmptyResult.instance;
    }
}
