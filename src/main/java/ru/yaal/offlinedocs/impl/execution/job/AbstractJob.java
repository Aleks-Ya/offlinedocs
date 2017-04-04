package ru.yaal.offlinedocs.impl.execution.job;

import org.springframework.beans.factory.annotation.Autowired;
import ru.yaal.offlinedocs.api.execution.ExecuteParams;
import ru.yaal.offlinedocs.api.execution.ExecutionFactory;
import ru.yaal.offlinedocs.api.execution.InitParams;
import ru.yaal.offlinedocs.api.execution.Result;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.properties.DataAppProps;
import ru.yaal.offlinedocs.api.storage.OutletStorage;

/**
 * @author Yablokov Aleksey
 */
@SuppressWarnings({"SpringAutowiredFieldsWarningInspection", "WeakerAccess"})
public abstract class AbstractJob<
        IP extends InitParams,
        EP extends ExecuteParams,
        R extends Result>
        implements Job<IP, EP, R> {

    private final IP initParams;

    @Autowired
    protected ExecutionFactory executionFactory;
    @Autowired
    protected DataAppProps dataAppProps;
    @Autowired
    protected OutletStorage outletStorage;

    protected AbstractJob(IP initParams) {
        this.initParams = initParams;
    }

    public IP getInitParams() {
        return initParams;
    }
}
