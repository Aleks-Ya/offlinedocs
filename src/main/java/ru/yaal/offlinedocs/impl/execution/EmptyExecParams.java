package ru.yaal.offlinedocs.impl.execution;

import ru.yaal.offlinedocs.api.execution.ExecParams;

/**
 * TODO remove ExecParams
 *
 * @author Yablokov Aleksey
 */
public class EmptyExecParams implements ExecParams {
    public static EmptyExecParams instance = new EmptyExecParams();

    private EmptyExecParams() {
    }
}
