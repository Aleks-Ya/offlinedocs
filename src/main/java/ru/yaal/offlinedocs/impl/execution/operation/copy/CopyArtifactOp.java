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
import ru.yaal.offlinedocs.api.execution.operation.OpId;
import ru.yaal.offlinedocs.impl.execution.param.EmptyExecParams;
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
        extends AbstractOp<CopyArtifactOp.InitParams, EmptyExecParams, EmptyResult> {

    private final Logger LOG = LoggerFactory.getLogger(CopyArtifactOp.class);

    public CopyArtifactOp(OpId opId, InitParams initParams) {
        super(opId, initParams);
    }

    @Override
    @SneakyThrows
    public EmptyResult execute(EmptyExecParams execParams) {
        Artifact artifact = getInitParams().getArtifact();
        File destFile = getInitParams().getDestFile();
        LOG.debug("Coping {} to {}", artifact, destFile.getAbsolutePath());

        ArtifactData data = artifactStorage.read(artifact);
        InputStream is = data.getInputStream();
        FileUtils.copyInputStreamToFile(is, destFile);
        LOG.debug("Copied: {} to {}", artifact, destFile.getAbsolutePath());

        return EmptyResult.instance;
    }

    @Getter
    public static class InitParams implements ru.yaal.offlinedocs.api.execution.param.InitParams {
        private final Artifact artifact;
        private final File destFile;

        public InitParams(Artifact artifact, File destFile) {
            this.artifact = artifact;
            this.destFile = destFile;
        }
    }
}
