package ru.yaal.offlinedocs.impl.execution.operation.copy;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOp;

import java.io.File;
import java.io.InputStream;

/**
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
public class CopyArtifactOp
        extends AbstractOp<CopyArtifactOp.InitParams, EmptyExecuteParams, EmptyResult> {

    private final Logger LOG = LoggerFactory.getLogger(CopyArtifactOp.class);

    public CopyArtifactOp(InitParams initParams) {
        super(initParams);
    }

    @Override
    @SneakyThrows
    public EmptyResult execute(EmptyExecuteParams executeParams) {
        Artifact artifact = getInitParams().getArtifact();
        File destDir = getInitParams().getDestinationDir();
        File destFile = fileNameStrategy.artifactToFile(destDir, artifact);
        LOG.debug("Coping {} to {}", artifact, destFile.getAbsolutePath());

        ArtifactData data = artifactStorage.read(artifact);
        InputStream is = data.getData();
        FileUtils.copyInputStreamToFile(is, destFile);
        LOG.debug("Copied: {} to {}", artifact, destFile.getAbsolutePath());

        return EmptyResult.instance;
    }

    @Getter
    public static class InitParams implements ru.yaal.offlinedocs.api.execution.InitParams {
        private final Artifact artifact;
        private final File destinationDir;

        public InitParams(Artifact artifact, File destinationDir) {
            this.artifact = artifact;
            this.destinationDir = destinationDir;
        }
    }
}
