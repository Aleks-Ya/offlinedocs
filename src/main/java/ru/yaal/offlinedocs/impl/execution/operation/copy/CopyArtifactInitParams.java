package ru.yaal.offlinedocs.impl.execution.operation.copy;

import lombok.Getter;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.execution.InitParams;

import java.io.File;

/**
 * @author Yablokov Aleksey
 */
@Getter
public class CopyArtifactInitParams implements InitParams {
    private final Artifact artifact;
    private final File destinationDir;

    public CopyArtifactInitParams(Artifact artifact, File destinationDir) {
        this.artifact = artifact;
        this.destinationDir = destinationDir;
    }
}
