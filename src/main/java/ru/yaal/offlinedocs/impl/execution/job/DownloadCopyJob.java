package ru.yaal.offlinedocs.impl.execution.job;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOpResult;
import ru.yaal.offlinedocs.impl.execution.operation.copy.CopyArtifactOp;
import ru.yaal.offlinedocs.impl.execution.operation.download.DownloadToStorageOp;

import java.io.File;

/**
 * TODO make all operations thread safety and just autowire them in jobs
 *
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
class DownloadCopyJob extends AbstractJob<DownloadCopyJob.InitParams, EmptyExecuteParams, EmptyResult> {
    private final Logger LOG = LoggerFactory.getLogger(DownloadCopyJob.class);

    public DownloadCopyJob(InitParams initParams) {
        super(initParams);
    }

    @Override
    public EmptyResult execute(EmptyExecuteParams executeParams) {
        LOG.debug("Start");
        DownloadToStorageOp.InitParams params = getInitParams().getDownloadParams();
        Operation<DownloadToStorageOp.InitParams, EmptyExecuteParams, ArtifactDataOpResult> downloadOp =
                executionFactory.getNewOperation(DownloadToStorageOp.class, params);
        ArtifactDataOpResult downloadResult = downloadOp.execute(EmptyExecuteParams.instance);
        Artifact artifact = downloadResult.getArtifactData().getArtifact();

        File destDir = outletStorage.getArtifactFile(artifact);
        CopyArtifactOp.InitParams copyParams = new CopyArtifactOp.InitParams(artifact, destDir);
        Operation<CopyArtifactOp.InitParams, EmptyExecuteParams, EmptyResult> copyOp =
                executionFactory.getNewOperation(CopyArtifactOp.class, copyParams);
        copyOp.execute(EmptyExecuteParams.instance);

        return EmptyResult.instance;
    }

    @Getter
    public static class InitParams implements ru.yaal.offlinedocs.api.execution.InitParams {
        private final DownloadToStorageOp.InitParams downloadParams;

        public InitParams(DownloadToStorageOp.InitParams downloadParams) {
            this.downloadParams = downloadParams;
        }
    }
}
