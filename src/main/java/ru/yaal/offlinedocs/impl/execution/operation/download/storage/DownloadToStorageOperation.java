package ru.yaal.offlinedocs.impl.execution.operation.download.storage;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.artifact.Artifact;
import ru.yaal.offlinedocs.api.artifact.data.ArtifactData;
import ru.yaal.offlinedocs.api.artifact.storage.ArtifactStorage;
import ru.yaal.offlinedocs.api.artifact.type.ArtifactTypeFactory;
import ru.yaal.offlinedocs.api.system.NetApi;
import ru.yaal.offlinedocs.impl.artifact.ArtifactImpl;
import ru.yaal.offlinedocs.impl.artifact.data.ByteArrayArtifactData;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOperation;
import ru.yaal.offlinedocs.impl.execution.operation.ArtifactDataOperationResult;
import ru.yaal.offlinedocs.impl.execution.operation.download.DownloadHelper;

import java.io.InputStream;
import java.net.URL;

/**
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class DownloadToStorageOperation
        extends AbstractOperation<DownloadToStorageInitParams, EmptyExecuteParams, ArtifactDataOperationResult> {

    private final Logger LOG = LoggerFactory.getLogger(DownloadToStorageOperation.class);
    @Autowired
    private NetApi netApi;
    @Autowired
    private ArtifactStorage storage;
    @Autowired
    private ArtifactTypeFactory typeFactory;

    public DownloadToStorageOperation(DownloadToStorageInitParams initParams) {
        super(initParams);
    }

    @Override
    @SneakyThrows
    public ArtifactDataOperationResult execute(EmptyExecuteParams executeParams) {
        DownloadToStorageInitParams initParams = getInitParams();
        URL url = initParams.getArtifactUrl();
        LOG.debug("Start downloading " + url);
        InputStream is = netApi.openUrl(url);
        byte[] bytes = DownloadHelper.inputStreamToByteArray(is, initParams.getLogEveryBytes());
        Artifact artifact = new ArtifactImpl(
                initParams.getArtifactCategory(),
                initParams.getArtifactName(),
                initParams.getArtifactVersion(),
                typeFactory.getTypeById(initParams.getArtifactTypeId()),
                bytes.length);
        ArtifactData data = new ByteArrayArtifactData(artifact, bytes);
        storage.save(data);
        LOG.debug("Artifact downloaded " + artifact);
        return new ArtifactDataOperationResult(data);
    }
}
