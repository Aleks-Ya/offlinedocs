package ru.yaal.offlinedocs.impl.operation;

import ru.yaal.offlinedocs.api.operation.OperationResult;

/**
 * @author Yablokov Aleksey
 */
public class ByteArrayOperationResult implements OperationResult {
    private final byte[] data;

    public ByteArrayOperationResult(byte[] data) {
        this.data = data;
    }

    byte[] getBytes() {
        return data;
    }
}
