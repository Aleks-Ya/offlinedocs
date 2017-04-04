package ru.yaal.offlinedocs.impl.execution.job.operationlist;

import lombok.Getter;
import ru.yaal.offlinedocs.api.execution.InitParams;
import ru.yaal.offlinedocs.api.execution.operation.Operation;

import java.util.List;

/**
 * @author Yablokov Aleksey
 */
@Getter
public class OpListInitParams implements InitParams {
    private final List<Operation> ops;

    public OpListInitParams(List<Operation> ops) {
        this.ops = ops;
    }

}
