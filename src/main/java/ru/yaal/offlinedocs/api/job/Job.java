package ru.yaal.offlinedocs.api.job;

/**
 * @author Yablokov Aleksey
 */
public interface Job<P extends JobParameters, R extends JobResult> {
    R execute();
}
