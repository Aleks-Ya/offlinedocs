package ru.yaal.offlinedocs.impl.execution.operation;

import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.api.execution.Result;

/**
 * @author Yablokov Aleksey
 */
public class ArtifactDataOpResult implements Result {
    private final ArtifactData data;

    public ArtifactDataOpResult(ArtifactData data) {
        this.data = data;
    }

    public ArtifactData getArtifactData() {
        return data;
    }
}
