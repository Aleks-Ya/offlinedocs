package ru.yaal.offlinedocs.impl.execution.job;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.impl.execution.EmptyExecParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOpResult;
import ru.yaal.offlinedocs.impl.execution.operation.download.DownloadToStorageOp;
import ru.yaal.offlinedocs.impl.execution.operation.unpack.UnpackZipOp;

import java.io.File;

/**
 * TODO unify jobs that use plexus-archiver
 *
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
class DownloadUnzipJob extends AbstractJob<DownloadUnzipJob.InitParams, EmptyExecParams, EmptyResult> {
    private final Logger LOG = LoggerFactory.getLogger(DownloadUnzipJob.class);

    public DownloadUnzipJob(InitParams initParams) {
        super(initParams);
    }

    @Override
    public EmptyResult execute(EmptyExecParams execParams) {
        LOG.debug("Start");
        DownloadToStorageOp.InitParams params = getInitParams().getDownloadParams();
        Operation<DownloadToStorageOp.InitParams, EmptyExecParams, ArtifactDataOpResult> downloadOp =
                execFactory.getNewOperation(DownloadToStorageOp.class, params);
        ArtifactDataOpResult downloadResult = downloadOp.execute(EmptyExecParams.instance);
        ArtifactData artifactData = downloadResult.getArtifactData();
        Artifact artifact = artifactData.getArtifact();

        File destDir = outletStorage.getArtifactDir(artifact);
        UnpackZipOp.InitParams copyParams = new UnpackZipOp.InitParams(artifactData.getFile(), destDir);
        Operation<UnpackZipOp.InitParams, EmptyExecParams, EmptyResult> copyOp =
                execFactory.getNewOperation(UnpackZipOp.class, copyParams);
        copyOp.execute(EmptyExecParams.instance);

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
