package ru.yaal.offlinedocs.impl.execution.job;

import lombok.Getter;
import org.codehaus.plexus.components.io.fileselectors.FileSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.api.execution.job.JobId;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOpResult;
import ru.yaal.offlinedocs.impl.execution.operation.download.DownloadToStorageOp;
import ru.yaal.offlinedocs.impl.execution.operation.unpack.UnpackTarGzOp;
import ru.yaal.offlinedocs.impl.execution.param.EmptyExecParams;

import java.io.File;

/**
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
class DownloadUnTarGzJob extends AbstractJob<DownloadUnTarGzJob.InitParams, EmptyExecParams, EmptyResult> {
    private final Logger LOG = LoggerFactory.getLogger(DownloadUnTarGzJob.class);

    public DownloadUnTarGzJob(JobId jobId, InitParams initParams) {
        super(jobId, initParams);
    }

    @Override
    public EmptyResult execute(EmptyExecParams execParams) {
        LOG.debug("Start");
        DownloadToStorageOp.InitParams params = getInitParams().getDownloadParams();
        boolean skipIfExists = params.getSkipIfExists();
        Artifact artifact = params.getArtifact();
        File destDir = outletStorage.getArtifactDir(artifact);
        if (skipIfExists && !outletStorage.isArtifactExists(artifact)) {
            ArtifactDataOpResult downloadResult =
                    execFactory.getNewOperation(getJobId(), DownloadToStorageOp.class, params).execute(EmptyExecParams.instance);
            ArtifactData artifactData = downloadResult.getArtifactData();

            FileSelector[] fileSelectors = getInitParams().getFileSelectors();
            UnpackTarGzOp.InitParams unTarGzParams = new UnpackTarGzOp.InitParams(artifactData.getFile(), destDir, fileSelectors);
            execFactory.getNewOperation(getJobId(), UnpackTarGzOp.class, unTarGzParams).execute(EmptyExecParams.instance);
        } else {
            LOG.debug("Artifact already exists: " + destDir);
        }
        return EmptyResult.instance;
    }

    @Getter
    public static class InitParams implements ru.yaal.offlinedocs.api.execution.param.InitParams {
        private final DownloadToStorageOp.InitParams downloadParams;
        private final FileSelector[] fileSelectors;

        public InitParams(DownloadToStorageOp.InitParams downloadParams, FileSelector[] fileSelectors) {
            this.downloadParams = downloadParams;
            this.fileSelectors = fileSelectors;
        }
    }
}
