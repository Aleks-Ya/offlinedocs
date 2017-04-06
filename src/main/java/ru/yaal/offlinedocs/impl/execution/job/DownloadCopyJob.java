package ru.yaal.offlinedocs.impl.execution.job;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.execution.job.JobId;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.operation.copy.CopyArtifactOp;
import ru.yaal.offlinedocs.impl.execution.operation.download.DownloadToStorageOp;
import ru.yaal.offlinedocs.impl.execution.param.EmptyExecParams;

import java.io.File;

/**
 * TODO make all operations thread safety and just autowire them in jobs
 * TODO send HEAD HTTP request to sure that artifact was changed
 *
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
class DownloadCopyJob extends AbstractJob<DownloadCopyJob.InitParams, EmptyExecParams, EmptyResult> {
    private final Logger LOG = LoggerFactory.getLogger(DownloadCopyJob.class);

    public DownloadCopyJob(JobId jobId, InitParams initParams) {
        super(jobId, initParams);
    }

    @Override
    public EmptyResult execute(EmptyExecParams execParams) {
        LOG.debug("Start");
        DownloadToStorageOp.InitParams params = getInitParams().getDownloadParams();
        boolean skipIfExists = params.getSkipIfExists();
        Artifact artifact = params.getArtifact();
        File destDir = outletStorage.getArtifactFile(artifact);
        if (skipIfExists && !outletStorage.isArtifactExists(artifact)) {
            execFactory.getNewOperation(getJobId(), DownloadToStorageOp.class, params).execute(EmptyExecParams.instance);

            CopyArtifactOp.InitParams copyParams = new CopyArtifactOp.InitParams(artifact, destDir);
            execFactory.getNewOperation(getJobId(), CopyArtifactOp.class, copyParams).execute(EmptyExecParams.instance);
        } else {
            LOG.debug("Artifact already exists: " + destDir);
        }
        return EmptyResult.instance;
    }

    @Getter
    public static class InitParams implements ru.yaal.offlinedocs.api.execution.param.InitParams {
        private final DownloadToStorageOp.InitParams downloadParams;

        public InitParams(DownloadToStorageOp.InitParams downloadParams) {
            this.downloadParams = downloadParams;
        }
    }
}
