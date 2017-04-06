package ru.yaal.offlinedocs.impl.execution.param;

import ru.yaal.offlinedocs.api.execution.param.ExecParams;

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
