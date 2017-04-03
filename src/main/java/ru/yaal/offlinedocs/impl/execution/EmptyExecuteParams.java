package ru.yaal.offlinedocs.impl.execution;

import ru.yaal.offlinedocs.api.execution.ExecuteParams;

/**
 * @author Yablokov Aleksey
 */
public class EmptyExecuteParams implements ExecuteParams {
    public static EmptyExecuteParams instance = new EmptyExecuteParams();

    private EmptyExecuteParams() {
    }
}
