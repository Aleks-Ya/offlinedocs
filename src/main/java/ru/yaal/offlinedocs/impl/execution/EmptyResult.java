package ru.yaal.offlinedocs.impl.execution;

import ru.yaal.offlinedocs.api.execution.Result;

/**
 * @author Yablokov Aleksey
 */
public class EmptyResult implements Result {
    public static final EmptyResult instance = new EmptyResult();

    private EmptyResult() {
    }
}
