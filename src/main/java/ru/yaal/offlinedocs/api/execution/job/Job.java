package ru.yaal.offlinedocs.api.execution.job;

import ru.yaal.offlinedocs.api.execution.ExecuteParams;
import ru.yaal.offlinedocs.api.execution.InitParams;
import ru.yaal.offlinedocs.api.execution.Result;

/**
 * TODO Job extends Callable
 *
 * @author Yablokov Aleksey
 */
public interface Job<IP extends InitParams, EP extends ExecuteParams, R extends Result> {
    R execute(EP executeParams);
}
