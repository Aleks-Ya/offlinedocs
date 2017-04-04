package ru.yaal.offlinedocs.impl.execution.job.downloadcopy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.storage.FileNameStrategy;
import ru.yaal.offlinedocs.api.execution.ExecutionFactory;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.api.properties.DataAppProps;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.job.AbstractJob;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOpResult;
import ru.yaal.offlinedocs.impl.execution.operation.copy.CopyArtifactInitParams;
import ru.yaal.offlinedocs.impl.execution.operation.copy.CopyArtifactOp;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageInitParams;
import ru.yaal.offlinedocs.impl.execution.operation.download.storage.DownloadToStorageOp;

import java.io.File;

/**
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class DownloadCopyJob extends AbstractJob<DownloadCopyInitParams, EmptyExecuteParams, EmptyResult> {
    private final Logger LOG = LoggerFactory.getLogger(DownloadCopyJob.class);
    @Autowired
    private ExecutionFactory executionFactory;
    @Autowired
    private DataAppProps dataAppProps;
    @Autowired
    private FileNameStrategy fileNameStrategy;

    public DownloadCopyJob(DownloadCopyInitParams initParams) {
        super(initParams);
    }

    @Override
    public EmptyResult execute(EmptyExecuteParams executeParams) {
        LOG.debug("Start");
        DownloadToStorageInitParams params = getInitParams().getDownloadParams();
        Operation<DownloadToStorageInitParams, EmptyExecuteParams, ArtifactDataOpResult> downloadOp =
                executionFactory.getNewOperation(DownloadToStorageOp.class, params);
        ArtifactDataOpResult downloadResult = downloadOp.execute(EmptyExecuteParams.instance);
        Artifact artifact = downloadResult.getArtifactData().getArtifact();

        File destDir = fileNameStrategy.subDirInOutlet(dataAppProps.getOutletDir(), artifact);
        CopyArtifactInitParams copyParams = new CopyArtifactInitParams(artifact, destDir);
        Operation<CopyArtifactInitParams, EmptyExecuteParams, EmptyResult> copyOp =
                executionFactory.getNewOperation(CopyArtifactOp.class, copyParams);
        copyOp.execute(EmptyExecuteParams.instance);

        return EmptyResult.instance;
    }
}
