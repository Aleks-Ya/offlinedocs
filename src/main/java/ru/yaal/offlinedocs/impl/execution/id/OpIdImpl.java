package ru.yaal.offlinedocs.impl.execution.id;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import ru.yaal.offlinedocs.api.execution.operation.OpId;

/**
 * @author Yablokov Aleksey
 */
@EqualsAndHashCode
@RequiredArgsConstructor
class OpIdImpl implements OpId {
    private final String id;

    @Override
    public String toString() {
        return id;
    }
}
