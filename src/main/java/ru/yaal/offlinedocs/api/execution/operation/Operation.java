package ru.yaal.offlinedocs.api.execution.operation;

import ru.yaal.offlinedocs.api.execution.param.ExecParams;
import ru.yaal.offlinedocs.api.execution.param.InitParams;
import ru.yaal.offlinedocs.api.execution.Result;

/**
 * @author Yablokov Aleksey
 */
public interface Operation<IP extends InitParams, EP extends ExecParams, R extends Result> {
    R execute(EP parameters);

    OpId getId();
}
