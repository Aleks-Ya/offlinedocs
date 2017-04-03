package ru.yaal.offlinedocs.impl.execution.operation;

import ru.yaal.offlinedocs.api.artifact.ArtifactData;
import ru.yaal.offlinedocs.api.execution.Result;

/**
 * @author Yablokov Aleksey
 */
public class ArtifactDataOperationResult implements Result {
    private final ArtifactData data;

    public ArtifactDataOperationResult(ArtifactData data) {
        this.data = data;
    }

    public ArtifactData getArtifactData() {
        return data;
    }
}
