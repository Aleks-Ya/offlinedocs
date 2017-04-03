package ru.yaal.offlinedocs.impl.execution.job.operationlist;

import ru.yaal.offlinedocs.api.execution.InitParams;
import ru.yaal.offlinedocs.api.execution.operation.Operation;

import java.util.List;

/**
 * @author Yablokov Aleksey
 */
public class OperationListInitParams implements InitParams{
    private final List<Operation> operations;

    public OperationListInitParams(List<Operation> operations) {
        this.operations = operations;
    }

    List<Operation> getOperations() {
        return operations;
    }
}
