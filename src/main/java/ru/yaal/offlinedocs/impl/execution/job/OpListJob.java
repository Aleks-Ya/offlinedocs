package ru.yaal.offlinedocs.impl.execution.job;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.impl.execution.EmptyExecParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;

import java.util.List;

/**
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
class OpListJob extends AbstractJob<OpListJob.InitParams, EmptyExecParams, EmptyResult> {
    private final Logger LOG = LoggerFactory.getLogger(OpListJob.class);

    public OpListJob(InitParams initParams) {
        super(initParams);
    }

    @Override
    public EmptyResult execute(EmptyExecParams execParams) {
        LOG.debug("Executing operations");
        getInitParams().getOps().forEach(operation -> operation.execute(EmptyExecParams.instance));
        return EmptyResult.instance;
    }

    @Getter
    public static class InitParams implements ru.yaal.offlinedocs.api.execution.InitParams {
        private final List<Operation> ops;

        public InitParams(List<Operation> ops) {
            this.ops = ops;
        }

    }
}
