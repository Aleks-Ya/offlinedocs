package ru.yaal.offlinedocs.impl.execution.operation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.yaal.offlinedocs.api.artifact.type.ArtifactTypeFactory;
import ru.yaal.offlinedocs.api.execution.param.ExecParams;
import ru.yaal.offlinedocs.api.execution.param.InitParams;
import ru.yaal.offlinedocs.api.execution.Result;
import ru.yaal.offlinedocs.api.execution.operation.OpId;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.api.storage.ArtifactStorage;
import ru.yaal.offlinedocs.api.system.NetApi;

/**
 * @author Yablokov Aleksey
 */
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public abstract class AbstractOp<
        IP extends InitParams,
        EP extends ExecParams,
        R extends Result>
        implements Operation<IP, EP, R> {

    private final IP initParams;
    private final OpId opId;

    @Autowired
    protected ArtifactStorage artifactStorage;
    @Autowired
    protected NetApi netApi;
    @Autowired
    protected ArtifactTypeFactory artifactTypeFactory;

    protected AbstractOp(OpId opId, IP initParams) {
        this.opId = opId;
        this.initParams = initParams;
    }

    @Override
    public OpId getId() {
        return opId;
    }

    public IP getInitParams() {
        return initParams;
    }
}
