package ru.yaal.offlinedocs.impl.execution.operation;

import ru.yaal.offlinedocs.api.execution.ExecuteParams;
import ru.yaal.offlinedocs.api.execution.InitParams;
import ru.yaal.offlinedocs.api.execution.Result;
import ru.yaal.offlinedocs.api.execution.operation.Operation;

/**
 * @author Yablokov Aleksey
 */
public abstract class AbstractOperation<
        IP extends InitParams,
        EP extends ExecuteParams,
        R extends Result>
        implements Operation<IP, EP, R> {

    private final IP initParameters;

    protected AbstractOperation(IP initParameters) {
        this.initParameters = initParameters;
    }

    public IP getInitParameters() {
        return initParameters;
    }
}
