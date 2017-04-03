package ru.yaal.offlinedocs.impl.execution.operation;

import ru.yaal.offlinedocs.api.execution.Result;

/**
 * @author Yablokov Aleksey
 */
public class ByteArrayOperationResult implements Result {
    private final byte[] data;

    public ByteArrayOperationResult(byte[] data) {
        this.data = data;
    }

    byte[] getBytes() {
        return data;
    }
}
