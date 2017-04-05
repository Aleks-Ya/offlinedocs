package ru.yaal.offlinedocs.impl.execution.id;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import ru.yaal.offlinedocs.api.execution.job.JobId;

/**
 * @author Yablokov Aleksey
 */
@EqualsAndHashCode
@RequiredArgsConstructor
public class JobIdImpl implements JobId {
    private final String id;

    @Override
    public String toString() {
        return id;
    }
}
