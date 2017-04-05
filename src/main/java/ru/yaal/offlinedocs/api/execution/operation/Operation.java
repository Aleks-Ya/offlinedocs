package ru.yaal.offlinedocs.api.execution.operation;

import ru.yaal.offlinedocs.api.execution.ExecParams;
import ru.yaal.offlinedocs.api.execution.InitParams;
import ru.yaal.offlinedocs.api.execution.Result;

/**
 * @author Yablokov Aleksey
 */
public interface Operation<IP extends InitParams, EP extends ExecParams, R extends Result> {
    R execute(EP parameters);
}
