package ru.yaal.offlinedocs.impl.execution;

import ru.yaal.offlinedocs.api.execution.InitParams;

/**
 * @author Yablokov Aleksey
 */
public class EmptyInitParams implements InitParams {
    public static EmptyInitParams instance = new EmptyInitParams();

    private EmptyInitParams() {
    }
}
