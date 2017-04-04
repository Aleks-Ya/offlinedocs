package ru.yaal.offlinedocs.impl.execution.operation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.yaal.offlinedocs.api.artifact.storage.ArtifactStorage;
import ru.yaal.offlinedocs.api.artifact.storage.FileNameStrategy;
import ru.yaal.offlinedocs.api.artifact.type.ArtifactTypeFactory;
import ru.yaal.offlinedocs.api.execution.ExecuteParams;
import ru.yaal.offlinedocs.api.execution.InitParams;
import ru.yaal.offlinedocs.api.execution.Result;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.api.system.NetApi;

/**
 * @author Yablokov Aleksey
 */
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public abstract class AbstractOp<
        IP extends InitParams,
        EP extends ExecuteParams,
        R extends Result>
        implements Operation<IP, EP, R> {

    private final IP initParams;

    @Autowired
    protected ArtifactStorage artifactStorage;
    @Autowired
    protected FileNameStrategy fileNameStrategy;
    @Autowired
    protected NetApi netApi;
    @Autowired
    protected ArtifactTypeFactory artifactTypeFactory;

    protected AbstractOp(IP initParams) {
        this.initParams = initParams;
    }

    public IP getInitParams() {
        return initParams;
    }
}
