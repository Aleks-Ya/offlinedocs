package ru.yaal.offlinedocs.impl.execution.operation.copy;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.api.artifact.storage.ArtifactStorage;
import ru.yaal.offlinedocs.api.artifact.storage.FileNameStrategy;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOperation;

import java.io.File;
import java.io.InputStream;

/**
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class CopyArtifactOperation
        extends AbstractOperation<CopyArtifactInitParams, EmptyExecuteParams, EmptyResult> {

    private final Logger LOG = LoggerFactory.getLogger(CopyArtifactOperation.class);
    @Autowired
    private ArtifactStorage storage;
    @Autowired
    private FileNameStrategy fileNameStrategy;

    public CopyArtifactOperation(CopyArtifactInitParams initParams) {
        super(initParams);
    }

    @Override
    @SneakyThrows
    public EmptyResult execute(EmptyExecuteParams executeParams) {
        Artifact artifact = getInitParams().getArtifact();
        File destDir = getInitParams().getDestinationDir();
        File destFile = fileNameStrategy.artifactToFile(destDir, artifact);
        LOG.debug("Coping {} to {}", artifact, destFile.getAbsolutePath());

        ArtifactData data = storage.read(artifact);
        InputStream is = data.getData();
        FileUtils.copyInputStreamToFile(is, destFile);
        LOG.debug("Copied: {} to {}", artifact, destFile.getAbsolutePath());

        return EmptyResult.instance;
    }
}
