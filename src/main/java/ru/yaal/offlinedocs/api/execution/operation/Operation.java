package ru.yaal.offlinedocs.api.execution.operation;

import ru.yaal.offlinedocs.api.execution.ExecuteParams;
import ru.yaal.offlinedocs.api.execution.InitParams;
import ru.yaal.offlinedocs.api.execution.Result;

/**
 * @author Yablokov Aleksey
 */
public interface Operation<IP extends InitParams, EP extends ExecuteParams, R extends Result> {
    R execute(EP parameters);
}
