package ru.yaal.offlinedocs.impl.execution.param;

import ru.yaal.offlinedocs.api.execution.param.InitParams;

/**
 * @author Yablokov Aleksey
 */
public class EmptyInitParams implements InitParams {
    public static EmptyInitParams instance = new EmptyInitParams();

    private EmptyInitParams() {
    }
}
