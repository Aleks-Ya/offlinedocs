package ru.yaal.offlinedocs.impl.execution.job;

import lombok.Getter;
import org.codehaus.plexus.components.io.fileselectors.FileSelector;
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
import ru.yaal.offlinedocs.impl.execution.operation.unpack.UnpackTarGzOp;

import java.io.File;

/**
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
class DownloadUnTarGzJob extends AbstractJob<DownloadUnTarGzJob.InitParams, EmptyExecParams, EmptyResult> {
    private final Logger LOG = LoggerFactory.getLogger(DownloadUnTarGzJob.class);

    public DownloadUnTarGzJob(InitParams initParams) {
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
        FileSelector[] fileSelectors = getInitParams().getFileSelectors();
        UnpackTarGzOp.InitParams unTarGzParams = new UnpackTarGzOp.InitParams(artifactData.getFile(), destDir, fileSelectors);
        Operation<UnpackTarGzOp.InitParams, EmptyExecParams, EmptyResult> unpackOp =
                execFactory.getNewOperation(UnpackTarGzOp.class, unTarGzParams);
        unpackOp.execute(EmptyExecParams.instance);

        return EmptyResult.instance;
    }

    @Getter
    public static class InitParams implements ru.yaal.offlinedocs.api.execution.InitParams {
        private final DownloadToStorageOp.InitParams downloadParams;
        private final FileSelector[] fileSelectors;

        public InitParams(DownloadToStorageOp.InitParams downloadParams, FileSelector[] fileSelectors) {
            this.downloadParams = downloadParams;
            this.fileSelectors = fileSelectors;
        }
    }
}
