package ru.yaal.offlinedocs.api.operation;

/**
 * @author Yablokov Aleksey
 */
public interface Operation<P extends OperationParameters, R extends OperationResult> {
    R execute(P parameters);
}
