package ru.yaal.offlinedocs.impl.operation;

import ru.yaal.offlinedocs.api.artifact.ArtifactData;
import ru.yaal.offlinedocs.api.operation.OperationResult;

/**
 * @author Yablokov Aleksey
 */
public class ArtifactDataOperationResult implements OperationResult {
    private final ArtifactData data;

    public ArtifactDataOperationResult(ArtifactData data) {
        this.data = data;
    }

    ArtifactData getArtifactData() {
        return data;
    }
}
